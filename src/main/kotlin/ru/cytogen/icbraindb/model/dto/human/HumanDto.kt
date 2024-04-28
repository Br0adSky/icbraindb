package ru.cytogen.icbraindb.model.dto.human

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive
import ru.cytogen.icbraindb.config.validation.CyrillicOrLatin
import ru.cytogen.icbraindb.config.validation.nationality.NationalityToSearchValidation

data class HumanDto(
    @JsonProperty
    @field:NotBlank
    val id: String?,
    @JsonProperty
    @field:Positive
    val age: Int?,
    @JsonProperty
    val comments: String?,
    @JsonProperty
    @field:CyrillicOrLatin
    val ethnos: String?,
    @JsonProperty
    @field:Valid
    val nationalities: List<@NationalityToSearchValidation NationalityDto>,
    @JsonProperty
    @field:Valid
    val city: CityDto?,
    @JsonProperty
    val male: Boolean?,
    @JsonProperty
    val isMigrant: Boolean?,
    @JsonProperty
    @field:Valid
    val diseases: List<DiseaseDto>
) {
}
