package ru.cytogen.icbraindb.dto

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_EMPTY)
class HumanDto(
    @JsonProperty("human")
    val id: String,
    @JsonProperty("age")
    val age: Int,
    @JsonProperty("comments")
    val comments: String?,
    @JsonProperty("ethnos")
    val ethos: String?,
    @JsonProperty("nationality")
    val nationality: String?,
    @JsonProperty("nationality_en")
    val nationalityEn: String?,
    @JsonProperty("r_city")
    val city: String?,
    @JsonProperty("r_country")
    val country: String?,
    @JsonProperty("r_district")
    val district: String?,
    @JsonProperty("sex")
    val sex: Int
) {
}