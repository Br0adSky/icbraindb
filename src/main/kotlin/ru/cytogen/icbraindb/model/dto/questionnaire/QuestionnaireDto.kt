package ru.cytogen.icbraindb.model.dto.questionnaire

import com.fasterxml.jackson.annotation.JsonProperty
import ru.cytogen.icbraindb.model.dto.questionnaire.test.TestDto

class QuestionnaireDto(
    @JsonProperty
    val id: Int?,
    @JsonProperty
    val human: String,
    @JsonProperty
    val test: TestDto,
    @JsonProperty
    val summaryInfo: SummaryDto,
    @JsonProperty
    val value: Double
) {
}