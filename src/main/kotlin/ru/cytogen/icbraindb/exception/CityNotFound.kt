package ru.cytogen.icbraindb.exception

import ru.cytogen.icbraindb.model.dto.human.CityDto

class CityNotFound : NotFoundException {
    constructor(city: CityDto) : super("City $city not found")
    constructor(id: Long) : super("City by id $id not found")
}
