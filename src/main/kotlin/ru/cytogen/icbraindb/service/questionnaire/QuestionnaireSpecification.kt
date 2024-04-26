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

object QuestionnaireSpecification {
    fun human(humans: ListOfExactFilters): Specification<Questionnaire> = Specification { root, _, builder ->
        val inPredicate = builder.`in`(root.get(Questionnaire_.human))
        humans.value.forEach { inPredicate.value(it.value) }
        inPredicate
    }

    fun testName(testName: StringFilter): Specification<Questionnaire> = Specification { root, _, builder ->
        CommonSpecification.likeOrEqual(
            builder,
            root.join(Questionnaire_.summary)
                .join(Summary_.test)
                .get(Test_.name),
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
