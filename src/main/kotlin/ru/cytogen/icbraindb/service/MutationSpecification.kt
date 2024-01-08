package ru.cytogen.icbraindb.service

import org.springframework.data.jpa.domain.Specification
import ru.cytogen.icbraindb.filter.types.ExactEnumTypeFilter
import ru.cytogen.icbraindb.filter.types.ListOfExactFilters
import ru.cytogen.icbraindb.filter.types.MinMaxFilter
import ru.cytogen.icbraindb.filter.types.StringFilter
import ru.cytogen.icbraindb.model.*

object MutationSpecification {
  fun chromosome(chromosome: StringFilter): Specification<Mutation> = Specification { root, _, builder ->
    CommonSpecification.likeOrEqual(builder, root.join(Mutation_.gene).get(Gene_.chromosome), chromosome)
  }

  fun gene(gene: StringFilter): Specification<Mutation> = Specification { root, _, builder ->
    CommonSpecification.likeOrEqual(builder, root.join(Mutation_.gene).get(Gene_.geneName), gene)
  }

  fun mutation(mutation: StringFilter): Specification<Mutation> = Specification { root, _, builder ->
    CommonSpecification.likeOrEqual(builder, root.get(Mutation_.mutation), mutation)
  }

  fun refNucl(refNucl: StringFilter): Specification<Mutation> = Specification { root, _, builder ->
    CommonSpecification.likeOrEqual(builder, root.get(Mutation_.referenceNucleotide), refNucl)
  }

  fun type(types: ExactEnumTypeFilter<MutationType>): Specification<Mutation> = Specification { root, _, builder ->
    val typeIn = builder.`in`(root.get(Mutation_.type))
    types.value.forEach(typeIn::value)
    typeIn

  }

  fun position(position: MinMaxFilter): Specification<Mutation> = Specification { root, _, builder ->
    val positionField = root.get(Mutation_.position)
    val predicate = when {
      position.min == null -> builder.lessThan(positionField, position.max!!.toLong())
      position.max == null -> builder.greaterThan(positionField, position.min.toLong())
      else -> builder.between(positionField, position.min.toLong(), position.max.toLong())
    }

    CommonSpecification.includeNull(builder, positionField, position, predicate)
  }

  fun humans(exactHumanIds: ListOfExactFilters): Specification<Mutation> = Specification { root, query, builder ->
    val snpIdQuery = query.subquery(Long::class.java)
    val snpIdRoot = snpIdQuery.from(Mutation::class.java)

    val summaryJoin = snpIdRoot.join(Mutation_.humans)
    val inJoin = builder.`in`(summaryJoin.get(Human_.id))
    exactHumanIds.value.forEach { inJoin.value(it.value) }

    snpIdQuery.select(snpIdRoot.get(Mutation_.id))
        .where(inJoin)
        .groupBy(snpIdRoot.get(Mutation_.id))
        .having(builder.equal(builder.count(snpIdRoot), exactHumanIds.value.size))

    builder.`in`(root.get(Mutation_.id)).value(snpIdQuery)
  }
}
