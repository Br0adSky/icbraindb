package ru.cytogen.icbraindb.model.dto.questionnaire

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive

data class QuestionnaireToEdit(
    @JsonProperty
    @field:Positive
    @field:NotNull
    val id: Int?,
    @JsonProperty
    @field:NotNull
    val value: Double?
) {
}