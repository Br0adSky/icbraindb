package ru.cytogen.icbraindb.filter.service

import com.fasterxml.jackson.annotation.JsonProperty
import ru.cytogen.icbraindb.dto.response.SortScheme
import kotlin.reflect.KClass

object SortParser {
    fun getSortScheme(enumClass: KClass<out Enum<*>>): SortScheme {
        return SortScheme(enumClass.java.fields.map { it.getAnnotation(JsonProperty::class.java).value })
    }
}