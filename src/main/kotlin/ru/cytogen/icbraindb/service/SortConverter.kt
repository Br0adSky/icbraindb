package ru.cytogen.icbraindb.service

import org.springframework.data.domain.Sort
import ru.cytogen.icbraindb.dto.common.SortColumn
import ru.cytogen.icbraindb.dto.common.SortType
import ru.cytogen.icbraindb.dto.common.Sort as SortDto

object SortConverter {
  fun <T : SortColumn> convert(sort: SortDto<T>): Sort {
    val base = sort.column.getSort()
    return when (sort.type) {
      SortType.ASC -> base.ascending()
      SortType.DESC -> base.descending()
    }
  }
}
