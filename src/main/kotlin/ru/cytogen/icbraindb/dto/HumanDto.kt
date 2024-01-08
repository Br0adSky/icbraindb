package ru.cytogen.icbraindb.dto

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_EMPTY)
class HumanDto(
    @JsonProperty("human")
    val id: String,
    @JsonProperty("age")
    val age: Int?,
    @JsonProperty("comments")
    val comments: String?,
    @JsonProperty("ethnos")
    val ethos: String?,
    @JsonProperty("nationalities")
    val nationalities: List<String>?,
    @JsonProperty("city")
    val cityName: String?,
    @JsonProperty("country")
    val country: String?,
    @JsonProperty("district")
    val district: String?,
    @JsonProperty("male")
    val sex: Boolean?,
    @JsonProperty("migrant")
    val isMigrant: Boolean?
) {
}
