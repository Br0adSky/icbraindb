package ru.cytogen.icbraindb.model.dto.questionnaire.test

import ru.cytogen.icbraindb.model.dto.questionnaire.SummaryDto

class DetailedTestDto(
    val test: TestDto,
    val questions: List<QuestionDto>,
    val responses: List<ResponseDto>,
    val summaries: List<SummaryDto>
) {
}