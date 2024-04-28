package ru.cytogen.icbraindb.service.test

import mu.KLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.cytogen.icbraindb.exception.TestNotFoundException
import ru.cytogen.icbraindb.model.dto.questionnaire.test.DetailedTestDto
import ru.cytogen.icbraindb.model.dto.questionnaire.test.DetailedTestToSave
import ru.cytogen.icbraindb.model.dto.questionnaire.test.TestDto
import ru.cytogen.icbraindb.repository.TestRepository

@Service
class TestService(
    private val testRepo: TestRepository,
    private val summaryService: SummaryService
) {
    companion object : KLogging()

    @Transactional
    fun saveEdited(request: TestDto) {
        testRepo.findById(request.id!!)
            .map {
                it.description = request.description
                it.descriptionEN = request.descriptionEN
                it.name = request.name!!
                it.nameEN = request.nameEN
                logger.info { "Тест с id: ${request.id} обновлен: $request" }
            }.orElseThrow { TestNotFoundException(request) }
    }

    @Transactional
    fun saveNew(request: DetailedTestToSave) {
        val test = TestConverter.convertTest(request.test!!)
        test.summaries = request.summaries!!.asSequence().map { summaryService.saveSummary(test, it) }.toSet()
        test.questions = request.questions!!.asSequence().map { TestConverter.convertQuestion(test, it) }.toSet()
        test.responses = request.responses!!.asSequence().map { TestConverter.convertResponse(test, it) }.toSet()
        testRepo.persist(test)
        logger.info { "Сохранен новый тест: $request" }
    }

    @Transactional
    fun getDetailedTest(testID: Int): DetailedTestDto {
        val test = testRepo.findById(testID).orElseThrow { TestNotFoundException(testID) }
        return DetailedTestDto(
            TestConverter.convertTest(test),
            test.questions.map(TestConverter::convertQuestion),
            test.responses.map(TestConverter::convertResponse),
            test.summaries.map(TestConverter::convertSummary)
        )
    }

    @Transactional
    fun delete(id: Int) {
        testRepo.deleteById(id)
        logger.info { "Удален тест с id: $id" }
    }
}