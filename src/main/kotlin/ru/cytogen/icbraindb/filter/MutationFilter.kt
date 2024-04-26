package ru.cytogen.icbraindb.filter

import com.fasterxml.jackson.annotation.JsonInclude
import jakarta.validation.Valid
import ru.cytogen.icbraindb.config.validation.minmaxfilter.MinMaxValidation
import ru.cytogen.icbraindb.filter.types.ExactEnumTypeFilter
import ru.cytogen.icbraindb.filter.types.ListOfExactFilters
import ru.cytogen.icbraindb.filter.types.MinMaxFilter
import ru.cytogen.icbraindb.filter.types.StringFilter
import ru.cytogen.icbraindb.model.db.mutation.MutationType

@JsonInclude(JsonInclude.Include.NON_NULL)
class MutationFilter(
    @TableFilter(type = TableFilterType.STRING, "MUTATION_FILTER_CHROMOSOME")
    @field:Valid
    val chromosome: StringFilter?,
    @TableFilter(type = TableFilterType.STRING, "MUTATION_FILTER_MUTATION")
    @field:Valid
    val mutation: StringFilter?,
    @TableFilter(type = TableFilterType.MIN_MAX, "MUTATION_FILTER_POSITION")
    @field:Valid
    @field:MinMaxValidation
    val position: MinMaxFilter<Long>?,
    @TableFilter(type = TableFilterType.STRING, "MUTATION_FILTER_GENE")
    @field:Valid
    val gene: StringFilter?,
    @TableFilter(type = TableFilterType.STRING, "MUTATION_FILTER_REF_NUCL")
    @field:Valid
    val refNucl: StringFilter?,
    @TableFilter(type = TableFilterType.SELECTION_LIST, "MUTATION_FILTER_TYPE")
    @field:Valid
    val types: ExactEnumTypeFilter<MutationType>?,
    @TableFilter(type = TableFilterType.EXACT_LIST, "MUTATION_FILTER_HUMANS")
    @field:Valid
    val humans: ListOfExactFilters?
) : BaseTableFilter {
}
