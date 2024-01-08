package ru.cytogen.icbraindb.filter.types

class ExactEnumTypeFilter<E : Enum<E>>(
    val value: List<E>
) {
}
