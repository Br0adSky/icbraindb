package ru.cytogen.icbraindb.dto.response

import com.fasterxml.jackson.annotation.JsonProperty
import ru.cytogen.icbraindb.annotation.TableFilterType

class FilterScheme(
    val filters: List<TableFilterDto>
) {
  class TableFilterDto(
      @JsonProperty
      val type: TableFilterType,
      @JsonProperty
      val name: String
  )
}
