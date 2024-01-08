package ru.cytogen.icbraindb.service

import ru.cytogen.icbraindb.dto.HumanDto
import ru.cytogen.icbraindb.model.Human
import ru.cytogen.icbraindb.model.Nationality

object HumanConverter {
  fun convert(human: Human): HumanDto {
    return HumanDto(
        human.id,
        human.age,
        human.comments,
        human.ethnos,
        human.nationalities.map(Nationality::nationality),
        human.city?.cityName,
        human.city?.district?.countryCode,
        human.city?.district?.districtName,
        human.sex,
        human.isMigrant
    )
  }
}
