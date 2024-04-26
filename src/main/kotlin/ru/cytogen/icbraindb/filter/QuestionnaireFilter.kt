package ru.cytogen.icbraindb.filter

import com.fasterxml.jackson.annotation.JsonInclude
import jakarta.validation.Valid
import ru.cytogen.icbraindb.config.validation.minmaxfilter.MinMaxValidation
import ru.cytogen.icbraindb.filter.types.ListOfExactFilters
import ru.cytogen.icbraindb.filter.types.MinMaxFilter
import ru.cytogen.icbraindb.filter.types.StringFilter

@JsonInclude(JsonInclude.Include.NON_NULL)
class QuestionnaireFilter(
    @TableFilter(type = TableFilterType.EXACT_LIST, "QUESTIONNAIRE_FILTER_HUMANS")
    @field:Valid
    val humans: ListOfExactFilters?,
    @TableFilter(type = TableFilterType.STRING, "QUESTIONNAIRE_FILTER_TEST_NAME")
    @field:Valid
    val testName: StringFilter?,
    @TableFilter(type = TableFilterType.STRING, "QUESTIONNAIRE_FILTER_SUMMARY_ALIAS")
    @field:Valid
    val testSummaryAlias: StringFilter?,
    @TableFilter(type = TableFilterType.MIN_MAX, "QUESTIONNAIRE_FILTER_VALUE")
    @field:Valid
    @field:MinMaxValidation
    val value: MinMaxFilter<Double>?
) : BaseTableFilter {
}