package ru.cytogen.icbraindb.repository

import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository
import ru.cytogen.icbraindb.model.db.questionnaire.Response

@Repository
interface ResponseRepository : BlockingFindByIDRepository<Response, Int>,
    JpaSpecificationExecutor<Response>, ManagedRepository<Response> {
}