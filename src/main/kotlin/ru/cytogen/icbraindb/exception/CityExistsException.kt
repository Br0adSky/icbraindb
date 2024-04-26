package ru.cytogen.icbraindb.exception

import ru.cytogen.icbraindb.model.dto.human.CityDto

class CityExistsException(city: CityDto) : AlreadyExistsException(
    "City name: ${city.city}, district: ${city.district}, country: ${city.country} already exists"
) {
}