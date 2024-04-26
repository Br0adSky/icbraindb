package ru.cytogen.icbraindb.model.dto.questionnaire

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import ru.cytogen.icbraindb.config.validation.Latin

data class QuestionnaireToSave(
    @JsonProperty
    @field:NotBlank
    val human: String?,
    @JsonProperty
    @field:NotBlank
    @field:Latin
    val alias: String?,
    @JsonProperty
    @field:NotNull
    val value: Double?
) {
}