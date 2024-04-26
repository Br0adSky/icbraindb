package ru.cytogen.icbraindb.dto.request

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.Valid
import ru.cytogen.icbraindb.dto.common.Sort
import ru.cytogen.icbraindb.dto.common.SortColumn
import ru.cytogen.icbraindb.filter.BaseTableFilter

class Request<T : BaseTableFilter, S : SortColumn>(
    @JsonProperty("filter")
    @field:Valid
    val filter: T?,
    @JsonProperty("page")
    val page: PageRequestDto = PageRequestDto(0, 10),
    @JsonProperty("sort")
    val sort: List<Sort<S>> = listOf()
) {
}
