package ru.cytogen.icbraindb.service.human

import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.CriteriaUpdate
import jakarta.persistence.criteria.Predicate
import jakarta.persistence.metamodel.SetAttribute
import jakarta.persistence.metamodel.SingularAttribute
import org.springframework.data.jpa.domain.Specification
import ru.cytogen.icbraindb.filter.types.*
import ru.cytogen.icbraindb.model.db.eeg.EEGFile
import ru.cytogen.icbraindb.model.db.eeg.EEGFile_
import ru.cytogen.icbraindb.model.db.human.*
import ru.cytogen.icbraindb.model.db.mutation.HumanMutation
import ru.cytogen.icbraindb.model.db.mutation.HumanMutation_
import ru.cytogen.icbraindb.model.db.questionnaire.Questionnaire
import ru.cytogen.icbraindb.model.db.questionnaire.Questionnaire_
import ru.cytogen.icbraindb.service.CommonSpecification
import ru.cytogen.icbraindb.service.LocaleTypes

object HumanSpecification {
    fun deleteCity(cityId: Long): (CriteriaBuilder) -> CriteriaUpdate<Human> = {
        val query = it.createCriteriaUpdate(Human::class.java)
        val root = query.root
        query.set(Human_.CITY, null)
            .where(it.equal(root.get(Human_.city), cityId))
    }

    fun ethnos(ethnos: StringFilter): Specification<Human> = Specification { root, _, builder ->
        val ethonsField = root.get(Human_.ethnos)
        CommonSpecification.likeOrEqual(builder, ethonsField, ethnos)
    }

    fun id(id: StringFilter): Specification<Human> = Specification { root, _, builder ->
        val idField = root.get(Human_.id)
        CommonSpecification.likeOrEqual(builder, idField, id)
    }

    fun city(city: StringFilter): Specification<Human> = Specification { root, _, builder ->
        val cityField = root.join(Human_.city).get(City_.cityName)
        CommonSpecification.likeOrEqual(builder, cityField, city)
    }

    fun district(district: StringFilter): Specification<Human> = Specification { root, _, builder ->
        val districtField = root.join(Human_.city).get(City_.districtName)
        CommonSpecification.likeOrEqual(builder, districtField, district)
    }

    fun country(country: StringFilter): Specification<Human> = Specification { root, _, builder ->
        val countryField = root.join(Human_.city).get(City_.countryCode)
        CommonSpecification.likeOrEqual(builder, countryField, country)
    }

    fun age(age: MinMaxFilter<Int>): Specification<Human> = Specification { root, _, builder ->
        CommonSpecification.minMax(builder, root.get(Human_.age), age)
    }

    fun sex(isMale: BooleanFilter): Specification<Human> = Specification { root, _, builder ->
        val sexField = root.get(Human_.sex)
        CommonSpecification.booleanWithNullCheck(builder, sexField, isMale)
    }

    fun isMigrant(isMigrant: BooleanFilter): Specification<Human> = Specification { root, _, builder ->
        val isMigrantField = root.get(Human_.isMigrant)
        CommonSpecification.booleanWithNullCheck(builder, isMigrantField, isMigrant)
    }

    fun hasMutation(hasMutation: IsExistsFilter): Specification<Human> = Specification { root, query, builder ->
        val mutationsSubquery = query.subquery(HumanMutation::class.java)
        val mutationsRoot = mutationsSubquery.from(HumanMutation::class.java)
        mutationsSubquery.select(mutationsRoot)
            .where(builder.equal(mutationsRoot[HumanMutation_.human], root[Human_.id]))
        val predicate = builder.exists(mutationsSubquery)
        builder.isExistsFilter(predicate, hasMutation)
    }

    private fun CriteriaBuilder.isExistsFilter(predicate: Predicate, filter: IsExistsFilter): Predicate =
        if (filter.value) {
            predicate
        } else {
            this.not(predicate)
        }

    fun hasEEGFiles(hasEEGFiles: IsExistsFilter): Specification<Human> = Specification { root, query, builder ->
        val eegSQ = query.subquery(EEGFile::class.java)
        val eegSqRoot = query.from(EEGFile::class.java)
        eegSQ.select(eegSqRoot).where(builder.equal(eegSqRoot[EEGFile_.human], root[Human_.id]))
        val predicate = builder.exists(eegSQ)
        builder.isExistsFilter(predicate, hasEEGFiles)
    }

    fun hasSummaries(hasSummaries: IsExistsFilter): Specification<Human> = Specification { root, query, builder ->
        val summariesSubquery = query.subquery(Questionnaire::class.java)
        val summariesRoot = query.from(Questionnaire::class.java)
        summariesSubquery.select(summariesRoot)
            .where(builder.equal(summariesRoot[Questionnaire_.human], root[Human_.id]))
        val predicate = builder.exists(summariesSubquery)
        builder.isExistsFilter(predicate, hasSummaries)
    }

    fun nationalities(exactNationalities: ListOfExactFilters, locale: LocaleTypes): Specification<Human> {
        val attribute = when(locale) {
            LocaleTypes.RU -> Nationality_.nationality
            LocaleTypes.EN -> Nationality_.nationalityEN
        }
        return humanListOfExactFilters(
            exactNationalities,
            attribute,
            Human_.nationalities
        )
    }

    fun diseases(exactDiseases: ListOfExactFilters): Specification<Human> =
        humanListOfExactFilters(exactDiseases, Disease_.disease, Human_.diseases)

    private fun <T : Any> humanListOfExactFilters(
        filters: ListOfExactFilters,
        targetAttribute: SingularAttribute<T, String>,
        joinAttribute: SetAttribute<Human, T>
    ): Specification<Human> =
        Specification { root, query, builder ->
            /*
            select *
from human where human.human in (
    select hn.human_id
    from human_nationalities hn join nationalities n on hn.nationality_id = n.id
    where n.nationality = 'русский' or n.nationality = 'украинец'
    group by hn.human_id
    having count(*) = 2)
             */
            val subquery = query.subquery(String::class.java)
            val sqRoot = subquery.from(Human::class.java)
            val targetJoin = sqRoot.join(joinAttribute)
            val inPredicate = builder.`in`(targetJoin.get(targetAttribute))
            filters.value.forEach { inPredicate.value(it.value) }
            subquery.select(sqRoot.get(Human_.id))
                .where(inPredicate)
                .groupBy(sqRoot.get(Human_.id))
                .having(builder.equal(builder.count(sqRoot), filters.value.size))

            builder.`in`(root.get(Human_.id)).value(subquery)
        }
}
