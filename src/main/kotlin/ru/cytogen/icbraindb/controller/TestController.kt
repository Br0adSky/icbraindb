package ru.cytogen.icbraindb.controller

import jakarta.validation.Valid
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import ru.cytogen.icbraindb.config.validation.EditAbstractIdValidation
import ru.cytogen.icbraindb.model.dto.questionnaire.SummaryDto
import ru.cytogen.icbraindb.model.dto.questionnaire.test.*
import ru.cytogen.icbraindb.service.test.QuestionService
import ru.cytogen.icbraindb.service.test.ResponseService
import ru.cytogen.icbraindb.service.test.SummaryService
import ru.cytogen.icbraindb.service.test.TestService

@Validated
@RestController
@RequestMapping("/api/test")
class TestController(
    private val service: TestService,
    private val questionService: QuestionService,
    private val responseService: ResponseService,
    private val summaryService: SummaryService
) {
    @GetMapping("/{id}")
    fun getById(@PathVariable @NotNull @Positive id: Int?): DetailedTestDto {
        return service.getDetailedTest(id!!)
    }

    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable @NotNull @Positive id: Int?) {
        return service.delete(id!!)
    }

    @PostMapping("/save")
    fun saveNew(@RequestBody @Valid request: DetailedTestToSave) {
        service.saveNew(request)
    }

    @PostMapping("/edit")
    fun saveEdited(@RequestBody @Valid @EditAbstractIdValidation request: TestDto) {
        service.saveEdited(request)
    }

    @PostMapping("/question/edit")
    fun saveEditQuestion(@RequestBody @Valid request: QuestionToEdit) {
        questionService.saveEdited(request)
    }

    @PostMapping("/response/edit")
    fun saveEditResponse(@RequestBody @Valid request: ResponseToEdit) {
        responseService.saveEdited(request)
    }

    @PostMapping("/summary/edit")
    fun saveEditSummary(@RequestBody @Valid @EditAbstractIdValidation request: SummaryDto) {
        summaryService.saveEdited(request)
    }
}