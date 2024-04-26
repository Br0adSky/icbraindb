package ru.cytogen.icbraindb.model.dto.eeg

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.domain.Sort
import ru.cytogen.icbraindb.dto.common.SortColumn
import ru.cytogen.icbraindb.model.db.eeg.EEGFile_
import ru.cytogen.icbraindb.model.db.human.Human_

enum class EEGSortColumn(private val sort: Sort.Order) : SortColumn {
    @JsonProperty("human")
    TYPE(Sort.Order.by("${EEGFile_.HUMAN}.${Human_.ID}")),

    @JsonProperty("description")
    REF_NUCL(Sort.Order.by(EEGFile_.DESCRIPTION)),

    @JsonProperty("eegId")
    POSITION(Sort.Order.by(EEGFile_.EEG_ID)),

    @JsonProperty("fileName")
    MUTATION(Sort.Order.by(EEGFile_.FILENAME));

    override fun getOrder(): Sort.Order {
        return sort
    }
}
