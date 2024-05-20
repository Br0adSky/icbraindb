package ru.cytogen.icbraindb.controller

import jakarta.validation.Valid
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import org.springframework.web.bind.annotation.*
import ru.cytogen.icbraindb.dto.request.QuestionnaireRequest
import ru.cytogen.icbraindb.dto.request.parsedQuestionnaireFilter
import ru.cytogen.icbraindb.dto.request.parsedQuestionnaireSort
import ru.cytogen.icbraindb.dto.response.MetadataResponse
import ru.cytogen.icbraindb.dto.response.Response
import ru.cytogen.icbraindb.model.dto.questionnaire.QuestionnaireDto
import ru.cytogen.icbraindb.model.dto.questionnaire.QuestionnaireToEdit
import ru.cytogen.icbraindb.model.dto.questionnaire.QuestionnaireToSave
import ru.cytogen.icbraindb.service.LocaleTypes
import ru.cytogen.icbraindb.service.questionnaire.QuestionnaireService

@RestController
@RequestMapping("/api/questionnaire")
class QuestionnaireController(
    private val service: QuestionnaireService
) {
    private val preparedMetadataResponse = MetadataResponse(parsedQuestionnaireFilter, parsedQuestionnaireSort)

    @GetMapping("/{id}")
    fun getById(@PathVariable @NotNull @Positive id: Int?): QuestionnaireDto {
        return service.getById(id!!)
    }

    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable @NotNull @Positive id: Int?) {
        return service.delete(id!!)
    }

    @GetMapping("/")
    fun getAll(): Iterable<QuestionnaireDto> {
        return service.getAll()
    }

    @PostMapping("/")
    fun getMutations(
        @RequestBody
        @Valid request: QuestionnaireRequest,
        @RequestParam(required = false)
        locale: LocaleTypes = LocaleTypes.RU
    ): Response {
        return service.getAll(request, locale)
    }

    @PostMapping("/save")
    fun saveNew(@RequestBody @Valid request: QuestionnaireToSave) {
        service.saveNew(request)
    }

    @PostMapping("/edit")
    fun saveEdited(@RequestBody @Valid request: QuestionnaireToEdit) {
        service.saveEdited(request)
    }

    @GetMapping("/metadata")
    fun getMetadata(): MetadataResponse {
        return preparedMetadataResponse
    }
}
