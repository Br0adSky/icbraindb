package ru.cytogen.icbraindb.service.questionnaire

import org.springframework.data.jpa.domain.Specification
import ru.cytogen.icbraindb.filter.types.ListOfExactFilters
import ru.cytogen.icbraindb.filter.types.MinMaxFilter
import ru.cytogen.icbraindb.filter.types.StringFilter
import ru.cytogen.icbraindb.model.db.questionnaire.Questionnaire
import ru.cytogen.icbraindb.model.db.questionnaire.Questionnaire_
import ru.cytogen.icbraindb.model.db.questionnaire.Summary_
import ru.cytogen.icbraindb.model.db.questionnaire.Test_
import ru.cytogen.icbraindb.service.CommonSpecification
import ru.cytogen.icbraindb.service.LocaleTypes

object QuestionnaireSpecification {
    fun human(humans: ListOfExactFilters): Specification<Questionnaire> = Specification { root, _, builder ->
        val inPredicate = builder.`in`(root.get(Questionnaire_.human))
        humans.value.forEach { inPredicate.value(it.value) }
        inPredicate
    }

    fun testName(testName: StringFilter, locale: LocaleTypes): Specification<Questionnaire> = Specification { root, _, builder ->
        val attribute = when(locale) {
            LocaleTypes.EN -> Test_.nameEN
            LocaleTypes.RU -> Test_.name
        }
        CommonSpecification.likeOrEqual(
            builder,
            root.join(Questionnaire_.summary)
                .join(Summary_.test)
                .get(attribute),
            testName
        )
    }

    fun testSummaryAlias(testSummaryAlias: StringFilter): Specification<Questionnaire> =
        Specification { root, _, builder ->
            CommonSpecification.likeOrEqual(
                builder,
                root.join(Questionnaire_.summary)
                    .get(Summary_.alias),
                testSummaryAlias
            )
        }

    fun value(value: MinMaxFilter<Double>): Specification<Questionnaire> = Specification { root, _, builder ->
        CommonSpecification.minMax(builder, root.get(Questionnaire_.value), value)
    }
}
