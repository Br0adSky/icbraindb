package ru.cytogen.icbraindb.repository

import org.springframework.stereotype.Repository
import ru.cytogen.icbraindb.model.db.human.Disease
import java.util.*

@Repository
interface DiseaseRepository : BlockingFindByIDRepository<Disease, Long>, ManagedRepository<Disease> {
    fun findByDisease(disease: String): Optional<LongIdProjection>

    fun existsByDisease(disease: String): Boolean
}