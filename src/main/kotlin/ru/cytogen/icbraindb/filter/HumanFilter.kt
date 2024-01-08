package ru.cytogen.icbraindb.filter

import com.fasterxml.jackson.annotation.JsonInclude
import jakarta.validation.Valid
import ru.cytogen.icbraindb.annotation.TableFilter
import ru.cytogen.icbraindb.annotation.TableFilterType
import ru.cytogen.icbraindb.filter.types.*

@JsonInclude(JsonInclude.Include.NON_NULL)
class HumanFilter(
    @TableFilter(type = TableFilterType.STRING, "Идентификатор")
    @field:Valid
    //TODO: для ID не нужен includeNull
    val id: StringFilter?,
    @TableFilter(type = TableFilterType.MIN_MAX, "Возраст")
    @field:Valid
    val age: MinMaxFilter?,
    @TableFilter(type = TableFilterType.STRING, "Этнос")
    @field:Valid
    val ethnos: StringFilter?,
    @TableFilter(type = TableFilterType.EXACT_LIST, "Национальности")
    val nationalities: ListOfExactFilters?,
    @TableFilter(type = TableFilterType.STRING, "Город")
    @field:Valid
    val city: StringFilter?,
    @TableFilter(type = TableFilterType.STRING, "Район")
    @field:Valid
    val district: StringFilter?,
    @TableFilter(type = TableFilterType.STRING, "Страна")
    @field:Valid
    val country: StringFilter?,
    @TableFilter(type = TableFilterType.BOOL, "Мужской пол")
    @field:Valid
    val sex: BooleanFilter?,
    @TableFilter(type = TableFilterType.BOOL, "Мигрант")
    @field:Valid
    val migrant: BooleanFilter?,
    @TableFilter(type = TableFilterType.BOOL_EXISTS, "Имеет мутации")
    @field:Valid
    val hasMutations: IsExistsFilter?,
    @TableFilter(type = TableFilterType.BOOL_EXISTS, "Имеет ЭЭГ файлы")
    @field:Valid
    val hasEEGFiles: IsExistsFilter?,
    @TableFilter(type = TableFilterType.BOOL_EXISTS, "Имеет болезни")
    @field:Valid
    val hasDiseases: IsExistsFilter?,
    @TableFilter(type = TableFilterType.BOOL_EXISTS, "Имеет результаты опросников")
    @field:Valid
    val hasSummaries: IsExistsFilter?
) : BaseTableFilter {
}
