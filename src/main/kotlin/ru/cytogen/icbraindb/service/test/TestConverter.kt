package ru.cytogen.icbraindb.service.test

import ru.cytogen.icbraindb.model.db.questionnaire.Question
import ru.cytogen.icbraindb.model.db.questionnaire.Response
import ru.cytogen.icbraindb.model.db.questionnaire.Summary
import ru.cytogen.icbraindb.model.db.questionnaire.Test
import ru.cytogen.icbraindb.model.dto.questionnaire.SummaryDto
import ru.cytogen.icbraindb.model.dto.questionnaire.test.*

object TestConverter {
    fun convertQuestion(from: Question): QuestionDto {
        return QuestionDto(from.id, from.question, from.questionEN, from.position)
    }

    fun convertResponse(from: Response): ResponseDto {
        return ResponseDto(from.id, from.response, from.value, from.position, from.responseEN)
    }

    fun convertTest(from: Test): TestDto {
        return TestDto(from.id, from.name, from.nameEN, from.description, from.descriptionEN)
    }

    fun convertSummary(from: Summary): SummaryDto {
        return SummaryDto(from.id, from.description, from.descriptionEN, from.alias)
    }

    fun convertTest(from: TestDto): Test {
        return Test(from.name!!, from.description, from.descriptionEN, from.nameEN)
    }

    fun convertQuestion(test: Test, from: QuestionToSave): Question {
        return Question(test, from.question!!, from.questionEN, from.position)
    }

    fun convertResponse(test: Test, from: ResponseToSave): Response {
        return Response(from.response!!, from.value!!, from.position!!, from.responseEN, test)
    }

    fun convertSummary(test: Test, from: SummaryDto): Summary {
        return Summary(test, from.alias!!, from.description, from.descriptionEN)
    }
}