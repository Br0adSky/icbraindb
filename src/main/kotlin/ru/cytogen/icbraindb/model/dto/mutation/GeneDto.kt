package ru.cytogen.icbraindb.model.dto.mutation

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive
import ru.cytogen.icbraindb.config.validation.Gene
import ru.cytogen.icbraindb.model.dto.AbstractIdDto

data class GeneDto(
    @JsonProperty
    @field:Positive
    override val id: Long?,
    @JsonProperty
    @field:NotBlank
    @field:Gene
    val chromosome: String?,
    @JsonProperty
    @field:NotBlank
    @field:Gene
    val gene: String?
) : AbstractIdDto<Long> {
}