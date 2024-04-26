package ru.cytogen.icbraindb.exception

import ru.cytogen.icbraindb.model.dto.questionnaire.test.QuestionToEdit

class QuestionNotFound(question: QuestionToEdit) :
    NotFoundException("Question: $question not found") {
}