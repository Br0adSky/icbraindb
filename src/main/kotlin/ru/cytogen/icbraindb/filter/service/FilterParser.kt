package ru.cytogen.icbraindb.filter.service

import ru.cytogen.icbraindb.annotation.TableFilter
import ru.cytogen.icbraindb.dto.response.FilterScheme
import ru.cytogen.icbraindb.filter.BaseTableFilter
import kotlin.reflect.KClass
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.findAnnotation

object FilterParser {
  private val filterCache: MutableMap<KClass<out BaseTableFilter>, FilterScheme> = mutableMapOf()

  fun getFilterCache(kClass: KClass<out BaseTableFilter>): FilterScheme {
    return filterCache.computeIfAbsent(kClass, ::computeFilterScheme)
  }

  private fun computeFilterScheme(kClass: KClass<out BaseTableFilter>): FilterScheme {
    return FilterScheme(kClass.declaredMemberProperties.asSequence()
        .mapNotNull { it.findAnnotation<TableFilter>() }
        .map { FilterScheme.TableFilterDto(it.type, it.name) }
        .toList())
  }
}
