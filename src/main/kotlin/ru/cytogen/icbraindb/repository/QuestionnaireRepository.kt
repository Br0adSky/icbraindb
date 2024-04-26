package ru.cytogen.icbraindb.repository

import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository
import ru.cytogen.icbraindb.model.db.questionnaire.Questionnaire

@Repository
interface QuestionnaireRepository : BlockingFindByIDRepository<Questionnaire, Int>,
    JpaSpecificationExecutor<Questionnaire>, ManagedRepository<Questionnaire> {
}