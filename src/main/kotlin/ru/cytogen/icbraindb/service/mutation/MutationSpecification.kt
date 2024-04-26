package ru.cytogen.icbraindb.service.mutation

import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.Predicate
import jakarta.persistence.criteria.Root
import org.springframework.data.jpa.domain.Specification
import ru.cytogen.icbraindb.filter.types.ExactEnumTypeFilter
import ru.cytogen.icbraindb.filter.types.ListOfExactFilters
import ru.cytogen.icbraindb.filter.types.MinMaxFilter
import ru.cytogen.icbraindb.filter.types.StringFilter
import ru.cytogen.icbraindb.model.db.mutation.*
import ru.cytogen.icbraindb.service.CommonSpecification

object MutationSpecification {
    fun chromosome(chromosome: StringFilter): Specification<HumanMutation> = Specification { root, _, builder ->
        CommonSpecification.likeOrEqual(
            builder,
            root.join(HumanMutation_.snp)
                .join(Snp_.gene)
                .get(Gene_.chromosome),
            chromosome
        )
    }

    fun gene(gene: StringFilter): Specification<HumanMutation> = Specification { root, _, builder ->
        CommonSpecification.likeOrEqual(
            builder,
            root.join(HumanMutation_.snp)
                .join(Snp_.gene)
                .get(Gene_.geneName),
            gene
        )
    }

    fun mutation(mutation: StringFilter): Specification<HumanMutation> = Specification { root, _, builder ->
        CommonSpecification.likeOrEqual(builder, root.get(HumanMutation_.mutation), mutation)
    }

    fun refNucl(refNucl: StringFilter): Specification<HumanMutation> = Specification { root, _, builder ->
        CommonSpecification.likeOrEqual(builder, root.join(HumanMutation_.snp).get(Snp_.referenceNucleotide), refNucl)
    }

    fun type(types: ExactEnumTypeFilter<MutationType>): Specification<HumanMutation> =
        Specification { root, _, builder ->
            types.value.asSequence().map {
                builder.equal(root.get(HumanMutation_.type), it)
            }.reduce { acc, predicate -> builder.or(acc, predicate) }
        }

    fun position(position: MinMaxFilter<Long>): Specification<HumanMutation> = Specification { root, _, builder ->
        CommonSpecification.minMax(builder, root.join(HumanMutation_.snp).get(Snp_.position), position)
    }

    fun humans(exactHumanIds: ListOfExactFilters): Specification<HumanMutation> =
        Specification { root, query, builder ->
            val subquery = query.subquery(Snp::class.java)
            val snpRoot = subquery.from(HumanMutation::class.java)
            subquery.select(snpRoot.get(HumanMutation_.snp))
                .where(getHumansPredicate(exactHumanIds, builder, snpRoot))
                .groupBy(snpRoot.get(HumanMutation_.snp))
                .having(builder.equal(builder.count(snpRoot), exactHumanIds.value.size))

            builder.and(builder.`in`(root.get(HumanMutation_.snp)).value(subquery), getHumansPredicate(exactHumanIds, builder, root))
        }

    private fun getHumansPredicate(
        exactHumanIds: ListOfExactFilters,
        builder: CriteriaBuilder,
        root: Root<HumanMutation>
    ): Predicate {
        return exactHumanIds.value.asSequence().map {
            builder.equal(root.get(HumanMutation_.human), it.value)
        }.reduce { acc, predicate -> builder.or(acc, predicate) }
    }
}
