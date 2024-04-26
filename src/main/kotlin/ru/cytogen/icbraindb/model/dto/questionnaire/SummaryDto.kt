package ru.cytogen.icbraindb.model.dto.questionnaire

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive
import ru.cytogen.icbraindb.config.validation.CyrillicSentence
import ru.cytogen.icbraindb.config.validation.Latin
import ru.cytogen.icbraindb.config.validation.LatinSentence
import ru.cytogen.icbraindb.model.dto.AbstractIdDto

data class SummaryDto(
    @JsonProperty
    @field:Positive
    override val id: Int?,
    @JsonProperty
    @field:CyrillicSentence
    val description: String?,
    @JsonProperty
    @field:LatinSentence
    val descriptionEN: String?,
    @JsonProperty
    @field:NotBlank
    @field:Latin
    val alias: String?
) : AbstractIdDto<Int> {
}