package ru.cytogen.icbraindb.service.eeg

import org.springframework.data.jpa.domain.Specification
import ru.cytogen.icbraindb.filter.types.StringFilter
import ru.cytogen.icbraindb.model.db.eeg.EEGFile
import ru.cytogen.icbraindb.model.db.eeg.EEGFile_
import ru.cytogen.icbraindb.service.CommonSpecification

object EEGSpecification {
    fun eegID(eegIdFilter: StringFilter): Specification<EEGFile> = Specification { root, _, builder ->
        CommonSpecification.likeOrEqual(builder, root.get(EEGFile_.eegId), eegIdFilter)
    }

    fun fileName(fileName: StringFilter): Specification<EEGFile> = Specification { root, _, builder ->
        CommonSpecification.likeOrEqual(builder, root.get(EEGFile_.filename), fileName)
    }

    fun description(description: StringFilter): Specification<EEGFile> = Specification { root, _, builder ->
        CommonSpecification.likeOrEqual(builder, root.get(EEGFile_.description), description)
    }

    fun human(human: StringFilter): Specification<EEGFile> = Specification { root, _, builder ->
        CommonSpecification.likeOrEqual(builder, root.get(EEGFile_.human), human)
    }
}
