package ru.cytogen.icbraindb.model.dto.questionnaire.test

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import ru.cytogen.icbraindb.config.validation.CyrillicSentence
import ru.cytogen.icbraindb.config.validation.LatinSentence

data class ResponseToSave(
    @JsonProperty
    @field:NotBlank
    @field:CyrillicSentence
    val response: String?,
    @JsonProperty
    @field:NotNull
    val value: Int?,
    @JsonProperty
    @field:NotNull
    @field:Positive
    val position: Int?,
    @JsonProperty
    @field:LatinSentence
    val responseEN: String?
) {
}