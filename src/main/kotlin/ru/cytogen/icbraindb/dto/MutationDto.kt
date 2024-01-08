package ru.cytogen.icbraindb.dto

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
class MutationDto(
    @JsonProperty("chromosome")
    val chromosome: String,
    @JsonProperty("gene")
    val gene: String,
    @JsonProperty("mutation")
    val mutation: String,
    @JsonProperty("position")
    val position: Long,
    @JsonProperty("ref_nucl")
    val referenceNucleotide: String,
    @JsonProperty("type")
    val type: String
) {
}
