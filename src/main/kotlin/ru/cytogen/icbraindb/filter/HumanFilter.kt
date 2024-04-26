package ru.cytogen.icbraindb.filter

import com.fasterxml.jackson.annotation.JsonInclude
import jakarta.validation.Valid
import ru.cytogen.icbraindb.config.validation.minmaxfilter.MinMaxValidation
import ru.cytogen.icbraindb.filter.types.*

@JsonInclude(JsonInclude.Include.NON_NULL)
class HumanFilter(
    @TableFilter(type = TableFilterType.STRING, "HUMAN_FILTER_ID")
    @field:Valid
    //TODO: для ID не нужен includeNull
    val id: StringFilter?,
    @TableFilter(type = TableFilterType.MIN_MAX, "HUMAN_FILTER_AGE")
    @field:Valid
    @field:MinMaxValidation
    val age: MinMaxFilter<Int>?,
    @TableFilter(type = TableFilterType.STRING, "HUMAN_FILTER_ETHNOS")
    @field:Valid
    val ethnos: StringFilter?,
    @TableFilter(type = TableFilterType.EXACT_LIST, "HUMAN_FILTER_NATIONALITIES")
    val nationalities: ListOfExactFilters?,
    @TableFilter(type = TableFilterType.STRING, "HUMAN_FILTER_CITY")
    @field:Valid
    val city: StringFilter?,
    @TableFilter(type = TableFilterType.STRING, "HUMAN_FILTER_DISTRICT")
    @field:Valid
    val district: StringFilter?,
    @TableFilter(type = TableFilterType.STRING, "HUMAN_FILTER_COUNTRY")
    @field:Valid
    val country: StringFilter?,
    @TableFilter(type = TableFilterType.BOOL, "HUMAN_FILTER_MALE")
    @field:Valid
    val sex: BooleanFilter?,
    @TableFilter(type = TableFilterType.BOOL, "HUMAN_FILTER_MIGRANT")
    @field:Valid
    val migrant: BooleanFilter?,
    @TableFilter(type = TableFilterType.EXACT_LIST, "HUMAN_FILTER_DISEASES")
    val diseases: ListOfExactFilters?,
    @TableFilter(type = TableFilterType.BOOL_EXISTS, "HUMAN_FILTER_HAS_MUTATIONS")
    @field:Valid
    val hasMutations: IsExistsFilter?,
    @TableFilter(type = TableFilterType.BOOL_EXISTS, "HUMAN_FILTER_HAS_EEG")
    @field:Valid
    val hasEEGFiles: IsExistsFilter?,
    @TableFilter(type = TableFilterType.BOOL_EXISTS, "HUMAN_FILTER_HAS_SUMMARIES")
    @field:Valid
    val hasSummaries: IsExistsFilter?
) : BaseTableFilter {
}
