package ru.cytogen.icbraindb.exception

import ru.cytogen.icbraindb.model.dto.questionnaire.test.ResponseToEdit

class ResponseNotFound(question: ResponseToEdit) :
    NotFoundException("Response: $question not found") {
}