package ru.cytogen.icbraindb.filter.types

import jakarta.validation.Valid

class ListOfExactFilters(
    val value: List<@Valid ExactStringFilter>
) : BaseFilter() {
}
