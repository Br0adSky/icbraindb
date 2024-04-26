package ru.cytogen.icbraindb.model.dto.mutation

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import ru.cytogen.icbraindb.config.validation.mutation.Nucleotide
import ru.cytogen.icbraindb.model.db.mutation.MutationType

class MutationToEdit(
    @JsonProperty
    @field:Positive
    @field:NotNull
    val id: Int?,
    @JsonProperty
    @field:Nucleotide
    val mutation: String?,
    @JsonProperty
    @field:NotNull
    val type: MutationType?
) {
}
