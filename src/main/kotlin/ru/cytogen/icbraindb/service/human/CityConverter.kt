package ru.cytogen.icbraindb.service.human

import ru.cytogen.icbraindb.model.db.human.City
import ru.cytogen.icbraindb.model.dto.human.CityDto

object CityConverter {
    fun convertCity(from: City): CityDto {
        return CityDto(from.id, from.cityName, from.districtName, from.countryCode)
    }

    fun convertCity(from: CityDto): City {
        return City(from.city, from.country!!, from.district)
    }
}
