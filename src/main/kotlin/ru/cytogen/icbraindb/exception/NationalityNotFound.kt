package ru.cytogen.icbraindb.exception

import ru.cytogen.icbraindb.model.dto.human.NationalityDto

class NationalityNotFound : NotFoundException {
    constructor(nationalityDto: NationalityDto) : super("Nationality $nationalityDto not found")

    constructor(id: Long) : super("Nationality by id $id not found")
}