package ru.cytogen.icbraindb.service.test

import mu.KLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.cytogen.icbraindb.exception.SummaryExistsException
import ru.cytogen.icbraindb.exception.SummaryNotFound
import ru.cytogen.icbraindb.model.db.questionnaire.Summary
import ru.cytogen.icbraindb.model.db.questionnaire.Test
import ru.cytogen.icbraindb.model.dto.questionnaire.SummaryDto
import ru.cytogen.icbraindb.repository.SummaryRepository

@Service
class SummaryService(
    private val repo: SummaryRepository
) {
    companion object : KLogging()

    @Transactional
    fun saveEdited(from: SummaryDto) {
        verifySummaryNotExists(from.alias!!)
        repo.findById(from.id!!).map {
            it.description = from.description
            it.descriptionEN = from.descriptionEN
            it.alias = from.alias
            logger.info { "Шкала теста с id: ${from.id} обновлена: $from" }
        }.orElseThrow { SummaryNotFound(from) }
    }

    fun findIdByAlias(alias: String): Int {
        return repo.findByAlias(alias)
            .map { it.getId() }
            .orElseThrow { SummaryExistsException(alias) }
    }

    fun verifySummaryNotExists(alias: String) {
        if (repo.existsByAlias(alias)) {
            throw SummaryExistsException(alias)
        }
    }

    fun saveSummary(test: Test, from: SummaryDto): Summary {
        verifySummaryNotExists(from.alias!!)

        return TestConverter.convertSummary(test, from)
    }
}