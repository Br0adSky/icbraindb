package ru.cytogen.icbraindb.filter.types

import jakarta.validation.constraints.NotBlank

class StringFilter(
    @field:NotBlank
    val value: String,
    val exactSearch: Boolean
): BaseFilter() {
}
