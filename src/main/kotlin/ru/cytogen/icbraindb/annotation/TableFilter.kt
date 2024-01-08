package ru.cytogen.icbraindb.annotation

import ru.cytogen.icbraindb.model.ModelEnum
import kotlin.reflect.KClass

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.PROPERTY, AnnotationTarget.FIELD)
annotation class TableFilter(
    val type: TableFilterType,
    val name: String,
    val cKlass: KClass<out ModelEnum> = ModelEnum::class
)
