package ru.cytogen.icbraindb.exception

import ru.cytogen.icbraindb.model.dto.human.NationalityDto

class NationalityExistsException(nationality: NationalityDto) : AlreadyExistsException(
    "Nationality: $nationality or already exists"
) {
}