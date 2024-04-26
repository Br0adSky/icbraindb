package ru.cytogen.icbraindb.service.test

import mu.KLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.cytogen.icbraindb.exception.ResponseNotFound
import ru.cytogen.icbraindb.model.dto.questionnaire.test.ResponseToEdit
import ru.cytogen.icbraindb.repository.ResponseRepository

@Service
class ResponseService(
    private val repo: ResponseRepository
) {
    companion object : KLogging()

    @Transactional
    fun saveEdited(from: ResponseToEdit) {
        repo.findById(from.id!!).map {
            it.response = from.response!!
            it.responseEN = from.responseEN
            logger.info { "Ответ с id: ${from.id} обновлен: $from" }
        }.orElseThrow { ResponseNotFound(from) }
    }
}