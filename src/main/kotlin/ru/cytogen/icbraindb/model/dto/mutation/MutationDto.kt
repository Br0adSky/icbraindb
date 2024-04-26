package ru.cytogen.icbraindb.model.dto.mutation

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import ru.cytogen.icbraindb.config.validation.mutation.Nucleotide
import ru.cytogen.icbraindb.model.db.mutation.MutationType

class MutationDto(
    @JsonProperty
    @field:Positive
    val id: Int?,
    @JsonProperty
    @field:NotBlank
    val human: String?,
    @JsonProperty
    @field:Valid
    @field:NotNull
    val snp: SnpDto?,
    @JsonProperty
    @field:Nucleotide
    val mutation: String?,
    @JsonProperty
    @field:NotNull
    val type: MutationType?
) {
}
