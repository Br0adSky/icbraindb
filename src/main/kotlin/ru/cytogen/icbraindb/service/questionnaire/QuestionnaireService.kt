package ru.cytogen.icbraindb.service.questionnaire

import mu.KLogging
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.cytogen.icbraindb.dto.request.QuestionnaireRequest
import ru.cytogen.icbraindb.dto.response.Response
import ru.cytogen.icbraindb.exception.QuestionnaireNotFound
import ru.cytogen.icbraindb.filter.QuestionnaireFilter
import ru.cytogen.icbraindb.model.db.questionnaire.Questionnaire
import ru.cytogen.icbraindb.model.dto.questionnaire.QuestionnaireDto
import ru.cytogen.icbraindb.model.dto.questionnaire.QuestionnaireToEdit
import ru.cytogen.icbraindb.model.dto.questionnaire.QuestionnaireToSave
import ru.cytogen.icbraindb.repository.QuestionnaireRepository
import ru.cytogen.icbraindb.repository.QuestionnaireToSaveRepository
import ru.cytogen.icbraindb.service.human.HumanService
import ru.cytogen.icbraindb.service.test.SummaryService
import ru.cytogen.icbraindb.service.utils.PageConverter

@Service
class QuestionnaireService(
    private val repo: QuestionnaireRepository,
    private val humanService: HumanService,
    private val summaryService: SummaryService,
    private val repoToSave: QuestionnaireToSaveRepository
) {
    companion object : KLogging()

    @Transactional
    fun saveNew(request: QuestionnaireToSave) {
        humanService.verifyHumanExists(request.human!!)

        repoToSave.persist(QuestionnaireConvert.convert(request, summaryService.findIdByAlias(request.alias!!)))
        logger.info { "Сохранен новый результат опросника: $request" }
    }

    @Transactional
    fun saveEdited(request: QuestionnaireToEdit) {
        repo.findById(request.id!!).map {
            it.value = request.value!!
            logger.info { "Результат опросника с id: ${request.id} обновлен: $request" }
        }.orElseThrow { QuestionnaireNotFound(request) }
    }

    @Transactional
    fun getAll(): Iterable<QuestionnaireDto> {
        return repo.findAll().asSequence().map { QuestionnaireConvert.convert(it) }.toList()
    }

    @Transactional
    fun getAll(request: QuestionnaireRequest): Response {
        val pagination = PageConverter.of(request)
        val data = request.filter?.let {
            repo.findAll(getSpecification(it), pagination)
        } ?: repo.findAll(pagination)

        return Response.from(request, data, QuestionnaireConvert::convert)
    }

    private fun getSpecification(filter: QuestionnaireFilter): Specification<Questionnaire> {
        return Specification.where(filter.humans?.let(QuestionnaireSpecification::human))
            .and(filter.testName?.let(QuestionnaireSpecification::testName))
            .and(filter.value?.let(QuestionnaireSpecification::value))
            .and(filter.testSummaryAlias?.let(QuestionnaireSpecification::testSummaryAlias))
    }

    @Transactional
    fun getById(id: Int): QuestionnaireDto =
        repo.findById(id)
            .map { QuestionnaireConvert.convert(it) }
            .orElseThrow { QuestionnaireNotFound(id) }

    @Transactional
    fun delete(id: Int) {
        repo.deleteById(id)
        logger.info { "Удален результат опросника с id: $id" }
    }
}
