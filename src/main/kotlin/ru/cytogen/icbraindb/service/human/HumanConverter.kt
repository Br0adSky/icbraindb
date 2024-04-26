package ru.cytogen.icbraindb.service.human

import ru.cytogen.icbraindb.model.db.human.Disease
import ru.cytogen.icbraindb.model.db.human.Human
import ru.cytogen.icbraindb.model.db.human.HumanWriteEntity
import ru.cytogen.icbraindb.model.dto.human.DiseaseDto
import ru.cytogen.icbraindb.model.dto.human.HumanDto

object HumanConverter {
    fun convert(human: Human): HumanDto {
        return HumanDto(
            human.id,
            human.age,
            human.comments,
            human.ethnos,
            human.nationalities.map(NationalityConverter::convertNationality),
            human.city?.let(CityConverter::convertCity),
            human.sex,
            human.isMigrant,
            human.diseases.map(::convertDisease)
        )
    }

    fun convert(from: HumanDto, city: Long?, nationalities: Set<Long>, diseases: Set<Long>): HumanWriteEntity {
        return HumanWriteEntity(
            from.id!!,
            from.age,
            from.comments,
            from.ethnos,
            city,
            from.male,
            from.isMigrant,
            nationalities,
            diseases
        )
    }

    fun convertDisease(from: Disease): DiseaseDto {
        return DiseaseDto(from.id, from.disease)
    }

    fun convertDisease(from: DiseaseDto): Disease {
        return Disease(from.disease!!)
    }
}
