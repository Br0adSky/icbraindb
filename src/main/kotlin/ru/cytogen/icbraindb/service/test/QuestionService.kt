package ru.cytogen.icbraindb.service.test

import mu.KLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.cytogen.icbraindb.exception.QuestionNotFound
import ru.cytogen.icbraindb.model.dto.questionnaire.test.QuestionToEdit
import ru.cytogen.icbraindb.repository.QuestionRepository

@Service
class QuestionService(
    private val repo: QuestionRepository
) {
    companion object : KLogging()
    @Transactional
    fun saveEdited(from: QuestionToEdit) {
        repo.findById(from.id!!).map {
            it.question = from.question!!
            it.questionEN = from.questionEN
            logger.info { "Вопрос с id: ${from.id} обновлен: $from" }
        }.orElseThrow { QuestionNotFound(from) }
    }
}