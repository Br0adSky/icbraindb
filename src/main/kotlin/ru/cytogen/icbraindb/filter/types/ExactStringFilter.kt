package ru.cytogen.icbraindb.filter.types

import jakarta.validation.constraints.NotBlank

class ExactStringFilter(
    @field:NotBlank
    val value: String
) {
}
