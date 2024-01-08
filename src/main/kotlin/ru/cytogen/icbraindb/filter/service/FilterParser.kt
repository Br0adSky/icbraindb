package ru.cytogen.icbraindb.filter.service

import ru.cytogen.icbraindb.annotation.TableFilter
import ru.cytogen.icbraindb.annotation.TableFilterType
import ru.cytogen.icbraindb.dto.response.FilterScheme
import ru.cytogen.icbraindb.filter.BaseTableFilter
import ru.cytogen.icbraindb.model.ModelEnum
import ru.cytogen.icbraindb.model.MutationType
import kotlin.reflect.KClass
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.staticFunctions

object FilterParser {
  private val filterCache: MutableMap<KClass<out BaseTableFilter>, FilterScheme> = mutableMapOf()

  fun getFilterCache(kClass: KClass<out BaseTableFilter>): FilterScheme {
    return filterCache.computeIfAbsent(kClass, ::computeFilterScheme)
  }

  private fun computeFilterScheme(kClass: KClass<out BaseTableFilter>): FilterScheme {
    return FilterScheme(kClass.declaredMemberProperties.asSequence()
        .mapNotNull { it.findAnnotation<TableFilter>() }
        .map(::mapTableFilter)
        .toList())
  }

  private fun mapTableFilter(filter: TableFilter): FilterScheme.TableFilterDto {
    if (filter.type == TableFilterType.SELECTION_LIST) {
      val enumValues = MutationType::class.staticFunctions.find { it.name == "values" }!!.call() as Array<out ModelEnum>
      return FilterScheme.TableFilterDto(filter.type, filter.name, enumValues.map { it.getDescription() })
    }

    return FilterScheme.TableFilterDto(filter.type, filter.name)
  }
}
