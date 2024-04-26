package ru.cytogen.icbraindb.repository

import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository
import ru.cytogen.icbraindb.model.db.questionnaire.Question

@Repository
interface QuestionRepository : BlockingFindByIDRepository<Question, Int>,
    JpaSpecificationExecutor<Question>, ManagedRepository<Question> {
}