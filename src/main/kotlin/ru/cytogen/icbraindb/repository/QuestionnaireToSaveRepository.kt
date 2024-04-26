package ru.cytogen.icbraindb.repository

import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository
import ru.cytogen.icbraindb.model.db.questionnaire.QuestionnaireToSave

@Repository
interface QuestionnaireToSaveRepository : BlockingFindByIDRepository<QuestionnaireToSave, Long>,
    JpaSpecificationExecutor<QuestionnaireToSave>, ManagedRepository<QuestionnaireToSave> {
}