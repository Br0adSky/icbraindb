package ru.cytogen.icbraindb.dto.response

import ru.cytogen.icbraindb.dto.common.Sort
import ru.cytogen.icbraindb.dto.common.SortColumn
import ru.cytogen.icbraindb.filter.BaseTableFilter

class Response<F : BaseTableFilter, S : SortColumn>(
    val data: List<Any>,
    val filter: F?,
    val page: PageResponseDto,
    val sort: Sort<S>,
    val filterScheme: FilterScheme
) {
}
