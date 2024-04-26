package ru.cytogen.icbraindb.filter.types

import jakarta.validation.Valid

class ListOfExactFilters(
    @field:Valid
    val value: List<ExactStringFilter>
) : BaseFilter() {
}
