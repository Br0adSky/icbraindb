package ru.cytogen.icbraindb.filter.types

import jakarta.validation.constraints.NotEmpty

class ExactStringFilter(
    @field:NotEmpty
    val value: String
) {
}
