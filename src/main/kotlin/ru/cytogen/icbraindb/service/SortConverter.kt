package ru.cytogen.icbraindb.service

import org.springframework.data.domain.Sort
import org.springframework.data.domain.Sort.Order
import ru.cytogen.icbraindb.dto.common.SortColumn
import ru.cytogen.icbraindb.dto.common.SortType
import ru.cytogen.icbraindb.dto.common.Sort as SortDto

object SortConverter {
    fun <T : SortColumn> convert(sort: List<SortDto<T>>): Sort {
        return Sort.by(sort.map(::convert))
    }

    private fun <T : SortColumn> convert(sort: SortDto<T>): Order {
        val base = sort.column.getOrder()
        return base.with(convert(sort.type))
    }

    private fun convert(type: SortType): Sort.Direction {
        return when (type) {
            SortType.ASC -> Sort.Direction.ASC
            SortType.DESC -> Sort.Direction.DESC
        }
    }
}
