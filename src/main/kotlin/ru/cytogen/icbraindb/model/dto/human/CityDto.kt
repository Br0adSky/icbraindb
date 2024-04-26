package ru.cytogen.icbraindb.model.dto.human

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive
import ru.cytogen.icbraindb.config.validation.Country
import ru.cytogen.icbraindb.config.validation.Cyrillic
import ru.cytogen.icbraindb.config.validation.District
import ru.cytogen.icbraindb.model.dto.AbstractIdDto

data class CityDto(
    @JsonProperty
    @field:Positive
    override val id: Long?,
    @JsonProperty
    @field:Cyrillic
    val city: String?,
    @JsonProperty
    @field:District
    val district: String?,
    @JsonProperty
    @field:NotBlank
    @field:Country
    val country: String?
): AbstractIdDto<Long> {
}