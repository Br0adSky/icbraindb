package ru.cytogen.icbraindb.dto.response

import org.springframework.data.domain.Page
import ru.cytogen.icbraindb.dto.common.Sort
import ru.cytogen.icbraindb.dto.request.Request
import ru.cytogen.icbraindb.filter.BaseTableFilter

class Response(
    val data: List<Any>,
    val filter: BaseTableFilter?,
    val page: PageResponseDto,
    val sort: List<Sort<*>>
) {
    companion object {
        fun <F : Any, T : Any> from(request: Request<*, *>, data: Page<F>, convert: (F) -> T): Response {
            return Response(
                data.asSequence().map(convert).toList(),
                request.filter,
                PageResponseDto(data.pageable.pageNumber, data.pageable.pageSize, data.totalPages, data.totalElements),
                request.sort
            )
        }
    }
}
