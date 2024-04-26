package ru.cytogen.icbraindb.repository

import org.springframework.stereotype.Repository
import ru.cytogen.icbraindb.model.db.questionnaire.Summary
import java.util.*

@Repository
interface SummaryRepository : BlockingFindByIDRepository<Summary, Int>, ManagedRepository<Summary> {
    fun existsByAlias(alias: String): Boolean

    fun findByAlias(alias: String): Optional<IntIdProjection>
}
