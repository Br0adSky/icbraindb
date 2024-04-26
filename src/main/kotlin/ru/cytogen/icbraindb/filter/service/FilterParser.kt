package ru.cytogen.icbraindb.filter.service

import ru.cytogen.icbraindb.dto.response.FilterScheme
import ru.cytogen.icbraindb.filter.BaseTableFilter
import ru.cytogen.icbraindb.filter.TableFilter
import ru.cytogen.icbraindb.filter.TableFilterType
import kotlin.reflect.KClass
import kotlin.reflect.KProperty1
import kotlin.reflect.full.*

object FilterParser {

    fun getFilterCache(kClass: KClass<out BaseTableFilter>): FilterScheme {
        return computeFilterScheme(kClass)
    }

    private fun computeFilterScheme(kClass: KClass<out BaseTableFilter>): FilterScheme {
        return FilterScheme(kClass.declaredMemberProperties.asSequence()
            .filter { it.hasAnnotation<TableFilter>() }
            .map { mapTableFilter(it) }
            .toList())
    }

    private fun mapTableFilter(
        property: KProperty1<out BaseTableFilter, *>
    ): FilterScheme.TableFilterDto {
        val filter = property.findAnnotation<TableFilter>()!!
        if (filter.type == TableFilterType.SELECTION_LIST) {
            val enumValues =
                (property.returnType.arguments.find { it.type!!.isSubtypeOf(Enum::class.starProjectedType) }!!
                    .type!!
                    .classifier as KClass<Enum<*>>)
                    .staticFunctions
                    .find { it.name == "values" }!!
                    .call() as Array<out Enum<*>>
            return FilterScheme.TableFilterDto(
                filter.type,
                property.name,
                filter.name,
                enumValues.map { it.name })
        }

        return FilterScheme.TableFilterDto(filter.type, property.name, filter.name)
    }
}
