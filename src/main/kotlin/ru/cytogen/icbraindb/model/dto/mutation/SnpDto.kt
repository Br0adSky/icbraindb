package ru.cytogen.icbraindb.model.dto.mutation

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.Valid
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import ru.cytogen.icbraindb.config.validation.mutation.Nucleotide

data class SnpDto(
    @JsonProperty
    @field:Positive
    val id: Long?,
    @JsonProperty
    @field:Valid
    @field:NotNull
    val gene: GeneDto?,
    @JsonProperty
    @field:Positive
    @field:NotNull
    val position: Long?,
    @JsonProperty
    @field:Nucleotide
    val referenceNucleotide: String?,
) {
}