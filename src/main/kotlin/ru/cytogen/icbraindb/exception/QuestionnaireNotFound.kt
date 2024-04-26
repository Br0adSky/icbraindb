package ru.cytogen.icbraindb.exception

import ru.cytogen.icbraindb.model.dto.questionnaire.QuestionnaireToEdit

class QuestionnaireNotFound :
    NotFoundException {
    constructor(questionnaire: QuestionnaireToEdit) : super("Questionnaire: $questionnaire not found")
    constructor(id: Int) : super("Questionnaire by id $id not found")
}