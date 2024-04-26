package ru.cytogen.icbraindb.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository
import ru.cytogen.icbraindb.model.db.mutation.SnpToSave

@Repository
interface SnpToSaveRepository : JpaRepository<SnpToSave, Long>, JpaSpecificationExecutor<SnpToSave>,
    ManagedRepository<SnpToSave> {
}