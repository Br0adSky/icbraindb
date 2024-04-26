package ru.cytogen.icbraindb.repository

import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository
import ru.cytogen.icbraindb.model.db.eeg.EEGFile

@Repository
interface EEGRepository : BlockingFindByIDRepository<EEGFile, Int>,
    JpaSpecificationExecutor<EEGFile>, ManagedRepository<EEGFile> {
}