package ru.cytogen.icbraindb.model.dto.questionnaire.test

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive
import ru.cytogen.icbraindb.config.validation.CyrillicSentence
import ru.cytogen.icbraindb.config.validation.LatinSentence
import ru.cytogen.icbraindb.model.dto.AbstractIdDto

data class TestDto(
    @JsonProperty
    @field:Positive
    override val id: Int?,
    @JsonProperty
    @field:NotBlank
    @field:CyrillicSentence
    val name: String?,
    @JsonProperty
    @field:LatinSentence
    val nameEN: String?,
    @JsonProperty
    @field:CyrillicSentence
    val description: String?,
    @JsonProperty
    @field:LatinSentence
    val descriptionEN: String?
) : AbstractIdDto<Int> {
}