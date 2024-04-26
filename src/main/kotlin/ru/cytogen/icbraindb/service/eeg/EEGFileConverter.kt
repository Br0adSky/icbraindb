package ru.cytogen.icbraindb.service.eeg

import ru.cytogen.icbraindb.model.db.eeg.EEGFile
import ru.cytogen.icbraindb.model.dto.eeg.EEGDto

object EEGFileConverter {
    fun convert(from: EEGFile): EEGDto {
        return EEGDto(
            from.id,
            from.filename,
            from.eegId,
            from.human,
            from.description,
            "${from.path}/${from.filename}"
        )
    }

    fun convert(from: EEGDto): EEGFile {
        return EEGFile(
            from.human,
            from.eegId!!,
            from.description,
            from.path!!,
            from.fileName!!
        )
    }
}