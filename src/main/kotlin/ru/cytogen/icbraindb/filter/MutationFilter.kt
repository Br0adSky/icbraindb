package ru.cytogen.icbraindb.filter

import com.fasterxml.jackson.annotation.JsonInclude
import ru.cytogen.icbraindb.annotation.TableFilter
import ru.cytogen.icbraindb.annotation.TableFilterType
import ru.cytogen.icbraindb.filter.types.ExactEnumTypeFilter
import ru.cytogen.icbraindb.filter.types.ListOfExactFilters
import ru.cytogen.icbraindb.filter.types.MinMaxFilter
import ru.cytogen.icbraindb.filter.types.StringFilter
import ru.cytogen.icbraindb.model.MutationType

@JsonInclude(JsonInclude.Include.NON_NULL)
class MutationFilter(
    @TableFilter(type = TableFilterType.STRING, "Хромосома")
    val chromosome: StringFilter?,
    @TableFilter(type = TableFilterType.STRING, "Мутация")
    val mutation: StringFilter?,
    @TableFilter(type = TableFilterType.MIN_MAX, "Позиция")
    val position: MinMaxFilter?,
    @TableFilter(type = TableFilterType.STRING, "Ген")
    val gene: StringFilter?,
    @TableFilter(type = TableFilterType.STRING, "Референсный вариант")
    val refNucl: StringFilter?,
    @TableFilter(type = TableFilterType.SELECTION_LIST, "Тип мутации", MutationType::class)
    val types: ExactEnumTypeFilter<MutationType>?,
    @TableFilter(type = TableFilterType.EXACT_LIST, "Идентификаторы людей")
    val humans: ListOfExactFilters?
) : BaseTableFilter {
}
