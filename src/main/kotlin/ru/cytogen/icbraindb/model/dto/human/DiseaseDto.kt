package ru.cytogen.icbraindb.model.dto.human

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive
import ru.cytogen.icbraindb.config.validation.Cyrillic
import ru.cytogen.icbraindb.model.dto.AbstractIdDto

data class DiseaseDto(
    @JsonProperty
    @field:Positive
    override val id: Long?,
    @JsonProperty
    @field:NotBlank
    @field:Cyrillic
    val disease: String?
): AbstractIdDto<Long> {
}