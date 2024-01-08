package ru.cytogen.icbraindb.dto.response

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ru.cytogen.icbraindb.annotation.TableFilterType

class FilterScheme(
    val filters: List<TableFilterDto>
) {
  @JsonInclude(JsonInclude.Include.NON_NULL)
  class TableFilterDto(
      @JsonProperty
      val type: TableFilterType,
      @JsonProperty
      val name: String,
      @JsonProperty
      val options: List<String>? = null
  )
}
