package ru.cytogen.icbraindb.service.human

import ru.cytogen.icbraindb.model.db.human.Nationality
import ru.cytogen.icbraindb.model.dto.human.NationalityDto

object NationalityConverter {
    fun convertNationality(from: Nationality): NationalityDto {
        return NationalityDto(from.id, from.nationality, from.nationalityEN)
    }

    fun convertNationality(from: NationalityDto): Nationality {
        return Nationality(from.nationality!!, from.nationalityEn!!)
    }
}
