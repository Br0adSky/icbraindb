package ru.cytogen.icbraindb.exception

import ru.cytogen.icbraindb.model.dto.human.DiseaseDto

class DiseaseExists(diseaseDto: DiseaseDto) :
    AlreadyExistsException("Disease: $diseaseDto already exists") {
}