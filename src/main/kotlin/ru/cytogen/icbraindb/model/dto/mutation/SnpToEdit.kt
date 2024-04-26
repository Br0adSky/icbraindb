package ru.cytogen.icbraindb.model.dto.mutation

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import ru.cytogen.icbraindb.config.validation.mutation.Nucleotide

data class SnpToEdit(
    @JsonProperty
    @field:Positive
    @field:NotNull
    val id: Long?,
    @JsonProperty
    @field:Positive
    @field:NotNull
    val position: Long?,
    @JsonProperty
    @field:Nucleotide
    val referenceNucleotide: String?,
) {
}