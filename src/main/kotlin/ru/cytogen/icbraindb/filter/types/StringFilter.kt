package ru.cytogen.icbraindb.filter.types

import jakarta.validation.constraints.NotEmpty

class StringFilter(
    @field:NotEmpty
    val value: String,
    val exactSearch: Boolean
): BaseFilter() {
}
