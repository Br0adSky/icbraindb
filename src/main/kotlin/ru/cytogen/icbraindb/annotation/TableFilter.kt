package ru.cytogen.icbraindb.annotation

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.PROPERTY, AnnotationTarget.FIELD)
annotation class TableFilter(
    val type: TableFilterType,
    val name: String
)
