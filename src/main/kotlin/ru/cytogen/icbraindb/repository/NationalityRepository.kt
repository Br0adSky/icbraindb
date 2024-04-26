package ru.cytogen.icbraindb.repository

import org.springframework.stereotype.Repository
import ru.cytogen.icbraindb.model.db.human.Nationality
import java.util.*

@Repository
interface NationalityRepository : BlockingFindByIDRepository<Nationality, Long>, ManagedRepository<Nationality> {
    fun findByNationality(nationality: String): Optional<LongIdProjection>

    fun findByNationalityEN(nationalityEN: String): Optional<LongIdProjection>

    fun existsByNationality(nationality: String): Boolean

    fun existsByNationalityEN(nationalityEN: String): Boolean
}