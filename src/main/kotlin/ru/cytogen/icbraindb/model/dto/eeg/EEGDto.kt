package ru.cytogen.icbraindb.model.dto.eeg

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive
import ru.cytogen.icbraindb.model.dto.AbstractIdDto

data class EEGDto(
    @JsonProperty
    @field:Positive
    override val id: Int?,
    @JsonProperty
    @field:NotBlank
    val fileName: String?,
    @JsonProperty
    @field:NotBlank
    val eegId: String?,
    @JsonProperty
    val human: String?,
    @JsonProperty
    val description: String?,
    @JsonProperty
    @field:NotBlank
    val path: String?
) : AbstractIdDto<Int> {
}