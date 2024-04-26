package ru.cytogen.icbraindb.model.dto.questionnaire.test

import com.fasterxml.jackson.annotation.JsonProperty

class QuestionDto(
    @JsonProperty
    val id: Int?,
    @JsonProperty
    val question: String,
    @JsonProperty
    val questionEN: String?,
    @JsonProperty
    val position: Int
) {
}