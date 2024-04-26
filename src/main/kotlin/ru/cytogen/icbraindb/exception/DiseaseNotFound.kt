package ru.cytogen.icbraindb.exception

import ru.cytogen.icbraindb.model.dto.human.DiseaseDto

class DiseaseNotFound : NotFoundException {
    constructor(diseaseDto: DiseaseDto) : super("Disease $diseaseDto not found")

    constructor(id: Long) : super("Disease by id $id not found")
}