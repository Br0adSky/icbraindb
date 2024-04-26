package ru.cytogen.icbraindb.model.dto.questionnaire.test

import com.fasterxml.jackson.annotation.JsonProperty

class ResponseDto(
    @JsonProperty
    val id: Int?,
    @JsonProperty
    val response: String,
    @JsonProperty
    val value: Int,
    @JsonProperty
    val position: Int,
    @JsonProperty
    val responseEN: String?
) {
}