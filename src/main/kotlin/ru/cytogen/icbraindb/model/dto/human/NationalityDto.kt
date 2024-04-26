package ru.cytogen.icbraindb.model.dto.human

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.Positive
import ru.cytogen.icbraindb.config.validation.Cyrillic
import ru.cytogen.icbraindb.config.validation.Latin
import ru.cytogen.icbraindb.model.dto.AbstractIdDto

data class NationalityDto(
    @JsonProperty
    @field:Positive
    override val id: Long?,
    @JsonProperty
    @field:Cyrillic
    val nationality: String?,
    @JsonProperty
    @field:Latin
    val nationalityEn: String?
) : AbstractIdDto<Long> {
}