package ru.cytogen.icbraindb.repository

import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository
import ru.cytogen.icbraindb.model.db.mutation.Gene
import java.util.*

@Repository
interface GeneRepository : BlockingFindByIDRepository<Gene, Long>, JpaSpecificationExecutor<Gene>, ManagedRepository<Gene> {
    fun findByChromosomeAndGeneName(chr: String, geneName: String): Optional<LongIdProjection>

    fun existsByChromosomeAndGeneName(chr: String, geneName: String): Boolean
}