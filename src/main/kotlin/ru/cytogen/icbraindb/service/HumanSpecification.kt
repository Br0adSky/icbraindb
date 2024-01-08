package ru.cytogen.icbraindb.service

import org.springframework.data.jpa.domain.Specification
import ru.cytogen.icbraindb.filter.types.*
import ru.cytogen.icbraindb.model.*

object HumanSpecification {
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
    val districtField = root.join(Human_.city).join(City_.district).get(District_.districtName)
    CommonSpecification.likeOrEqual(builder, districtField, district)
  }

  fun country(country: StringFilter): Specification<Human> = Specification { root, _, builder ->
    val countryField = root.join(Human_.city).join(City_.district).get(District_.countryCode)
    CommonSpecification.likeOrEqual(builder, countryField, country)
  }

  fun age(age: MinMaxFilter): Specification<Human> = Specification { root, _, builder ->
    val ageField = root.get(Human_.age)
    val predicate = when {
      age.min == null -> builder.lessThan(ageField, age.max!!.toInt())
      age.max == null -> builder.greaterThan(ageField, age.min.toInt())
      else -> builder.between(ageField, age.min.toInt(), age.max.toInt())
    }

    CommonSpecification.includeNull(builder, ageField, age, predicate)
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
    val mutationSQ = query.subquery(Mutation::class.java)
    val humanCorrelatedRoot = mutationSQ.correlate(root)
    val mutationJoin = humanCorrelatedRoot.join(Human_.mutations)
    mutationSQ.select(mutationJoin)

    CommonSpecification.exists(builder, mutationSQ, hasMutation)
  }

  fun hasEEGFiles(hasEEGFiles: IsExistsFilter): Specification<Human> = Specification { root, query, builder ->
    val eegSQ = query.subquery(EEGFile::class.java)
    val humanCorrelatedRoot = eegSQ.correlate(root)
    val eegJoin = humanCorrelatedRoot.join(Human_.eegFiles)
    eegSQ.select(eegJoin)

    CommonSpecification.exists(builder, eegSQ, hasEEGFiles)
  }

  fun hasDiseases(hasDiseases: IsExistsFilter): Specification<Human> = Specification { root, query, builder ->
    val diseaseSQ = query.subquery(Disease::class.java)
    val humanCorrelatedRoot = diseaseSQ.correlate(root)
    val diseaseJoin = humanCorrelatedRoot.join(Human_.diseases)
    diseaseSQ.select(diseaseJoin)

    CommonSpecification.exists(builder, diseaseSQ, hasDiseases)
  }

  fun hasSummaries(hasSummaries: IsExistsFilter): Specification<Human> = Specification { root, query, builder ->
    val summarySQ = query.subquery(HumanTestSummaries::class.java)
    val humanCorrelatedRoot = summarySQ.correlate(root)
    val summaryJoin = humanCorrelatedRoot.join(Human_.summaries)
    summarySQ.select(summaryJoin)

    CommonSpecification.exists(builder, summarySQ, hasSummaries)
  }

  fun nationalities(exactNationalities: ListOfExactFilters): Specification<Human> = Specification { root, query, builder ->
    val humanNationalitySQ = query.subquery(String::class.java)
    val humanNationalityRoot = humanNationalitySQ.from(Human::class.java)

    val nationalityQuery = humanNationalitySQ.subquery(Nationality::class.java)
    val nationalityRoot = nationalityQuery.from(Nationality::class.java)

    val nationalityPredicate = builder.`in`(nationalityRoot.get(Nationality_.nationality))
    exactNationalities.value.forEach { nationalityPredicate.value(it.value) }
    nationalityQuery.select(nationalityRoot).where(nationalityPredicate)

    val nationalityJoin = humanNationalityRoot.join(Human_.nationalities)
    val nationalityIn = builder.`in`(nationalityJoin)
    nationalityIn.value(nationalityQuery)
    CommonSpecification.includeNull(builder, nationalityJoin, exactNationalities, nationalityIn)
    humanNationalitySQ.select(humanNationalityRoot.get(Human_.id))
        .where(nationalityIn)
        .groupBy(humanNationalityRoot.get(Human_.id))
        .having(builder.equal(builder.count(humanNationalityRoot), exactNationalities.value.size))

    builder.`in`(root.get(Human_.id)).value(humanNationalitySQ)
  }
}
