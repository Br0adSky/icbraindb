package ru.cytogen.icbraindb.filter.types

class MinMaxFilter<T : Comparable<T>>(
    val min: T?,
    val max: T?
) : BaseFilter() {
}
