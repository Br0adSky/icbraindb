package ru.cytogen.icbraindb.dto.common

class Sort<T : SortColumn>(
    val column: T,
    val type: SortType
) {
}
