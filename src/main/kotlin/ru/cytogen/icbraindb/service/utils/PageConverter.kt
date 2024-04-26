package ru.cytogen.icbraindb.service.utils

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import ru.cytogen.icbraindb.dto.common.SortColumn
import ru.cytogen.icbraindb.dto.request.Request
import ru.cytogen.icbraindb.filter.BaseTableFilter
import ru.cytogen.icbraindb.service.SortConverter

object PageConverter {
    fun <T : BaseTableFilter, S : SortColumn> of(request: Request<T, S>): Pageable {
        val pageRequestDto = request.page
        val sort = request.sort
        return PageRequest.of(pageRequestDto.number, pageRequestDto.size)
            .withSort(SortConverter.convert(request.sort))
    }
}