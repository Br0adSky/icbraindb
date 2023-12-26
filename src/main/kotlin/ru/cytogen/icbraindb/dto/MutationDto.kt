package ru.cytogen.icbraindb.dto

import com.fasterxml.jackson.annotation.JsonProperty

class MutationDto(
    @JsonProperty("chromosome")
    val chromosome: String,
    @JsonProperty("gene")
    val gene: String,
    @JsonProperty("human")
    val human: String,
    @JsonProperty("id")
    val id: Long,
    @JsonProperty("mutation")
    val mutation: String,
    @JsonProperty("position")
    val position: Long,
    @JsonProperty("ref_nucl")
    val referenceNucleotide: String,
    @JsonProperty("type")
    val type: Int
) {
}