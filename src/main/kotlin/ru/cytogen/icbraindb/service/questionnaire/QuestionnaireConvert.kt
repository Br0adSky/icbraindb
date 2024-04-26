package ru.cytogen.icbraindb.service.questionnaire

import ru.cytogen.icbraindb.model.db.questionnaire.Questionnaire
import ru.cytogen.icbraindb.model.db.questionnaire.QuestionnaireToSave
import ru.cytogen.icbraindb.model.dto.questionnaire.QuestionnaireDto
import ru.cytogen.icbraindb.service.test.TestConverter.convertSummary
import ru.cytogen.icbraindb.service.test.TestConverter.convertTest
import ru.cytogen.icbraindb.model.dto.questionnaire.QuestionnaireToSave as QuestionnaireToSaveDto

object QuestionnaireConvert {
    fun convert(from: Questionnaire): QuestionnaireDto {
        return QuestionnaireDto(
            from.id,
            from.human,
            convertTest(from.summary.test),
            convertSummary(from.summary),
            from.value
        )
    }

    fun convert(from: QuestionnaireToSaveDto, summary: Int): QuestionnaireToSave {
        return QuestionnaireToSave(
            from.human!!,
            from.value!!,
            summary,
        )
    }
}