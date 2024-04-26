package ru.cytogen.icbraindb.model.dto.questionnaire.test

import jakarta.validation.Valid
import jakarta.validation.constraints.NotNull
import ru.cytogen.icbraindb.model.dto.questionnaire.SummaryDto

data class DetailedTestToSave(
    @field:NotNull
    @field:Valid
    val test: TestDto?,
    @field:Valid
    @field:NotNull
    val questions: List<QuestionToSave>?,
    @field:Valid
    @field:NotNull
    val responses: List<ResponseToSave>?,
    @field:Valid
    @field:NotNull
    val summaries: List<SummaryDto>?
) {
}