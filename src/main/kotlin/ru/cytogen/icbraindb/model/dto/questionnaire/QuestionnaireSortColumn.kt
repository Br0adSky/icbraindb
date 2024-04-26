package ru.cytogen.icbraindb.model.dto.questionnaire

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.domain.Sort
import ru.cytogen.icbraindb.dto.common.SortColumn
import ru.cytogen.icbraindb.model.db.questionnaire.Questionnaire_
import ru.cytogen.icbraindb.model.db.questionnaire.Summary_

enum class QuestionnaireSortColumn(private val order: Sort.Order) : SortColumn {
    @JsonProperty("human")
    HUMAN(Sort.Order.by(Questionnaire_.HUMAN)),

    @JsonProperty("testId")
    TEST(Sort.Order.by("${Questionnaire_.SUMMARY}.${Summary_.TEST}")),

    @JsonProperty("summaryAlias")
    SUMMARY_ALIAS(Sort.Order.by("${Questionnaire_.SUMMARY}.${Summary_.ALIAS}")),

    @JsonProperty("value")
    VALUE(Sort.Order.by(Questionnaire_.VALUE));

    override fun getOrder(): Sort.Order {
        return order;
    }
}