package ru.cytogen.icbraindb.filter

import com.fasterxml.jackson.annotation.JsonInclude
import jakarta.validation.Valid
import ru.cytogen.icbraindb.filter.types.StringFilter

@JsonInclude(JsonInclude.Include.NON_NULL)
class EEGFilter(
    @TableFilter(type = TableFilterType.STRING, "EEG_FILTER_ID")
    @field:Valid
    val eegID: StringFilter?,
    @TableFilter(type = TableFilterType.STRING, "EEG_FILTER_FILE_NAME")
    @field:Valid
    val fileName: StringFilter?,
    @TableFilter(type = TableFilterType.STRING, "EEG_FILTER_DESCRIPTION")
    @field:Valid
    val description: StringFilter?,
    @TableFilter(type = TableFilterType.STRING, "EEG_FILTER_HUMAN")
    @field:Valid
    val human: StringFilter?
) : BaseTableFilter {
}