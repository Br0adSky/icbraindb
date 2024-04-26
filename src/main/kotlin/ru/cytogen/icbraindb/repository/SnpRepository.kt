package ru.cytogen.icbraindb.repository

import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository
import ru.cytogen.icbraindb.model.db.mutation.Snp
import java.util.*

@Repository
interface SnpRepository : BlockingFindByIDRepository<Snp, Long>, JpaSpecificationExecutor<Snp>, ManagedRepository<Snp> {
    fun findByReferenceNucleotideAndPosition(
        refNucl: String,
        position: Long
    ): Optional<LongIdProjection>

    fun existsByReferenceNucleotideAndPosition(
        refNucl: String,
        position: Long
    ): Boolean
}