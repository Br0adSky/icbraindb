package ru.cytogen.icbraindb.model.dto.questionnaire.test

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import ru.cytogen.icbraindb.config.validation.CyrillicSentence
import ru.cytogen.icbraindb.config.validation.LatinSentence

data class QuestionToEdit(
    @JsonProperty
    @field:NotNull
    @field:Positive
    val id: Int?,
    @JsonProperty
    @field:NotBlank
    @field:CyrillicSentence
    val question: String?,
    @JsonProperty
    @field:LatinSentence
    val questionEN: String?
) {
}