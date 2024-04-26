package ru.cytogen.icbraindb.repository

import org.springframework.stereotype.Repository
import ru.cytogen.icbraindb.model.db.human.City
import java.util.*

@Repository
interface CityRepository : BlockingFindByIDRepository<City, Long>, ManagedRepository<City> {
    fun findByCityNameAndDistrictNameAndCountryCode(
        city: String?,
        districtName: String?,
        country: String
    ): Optional<LongIdProjection>

    fun existsByCityNameAndDistrictNameAndCountryCode(
        city: String?,
        districtName: String?,
        country: String
    ): Boolean
}