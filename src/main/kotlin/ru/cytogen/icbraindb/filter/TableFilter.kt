package ru.cytogen.icbraindb.filter

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.PROPERTY, AnnotationTarget.FIELD)
annotation class TableFilter(
    val type: TableFilterType,
    val name: String
)
