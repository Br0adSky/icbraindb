package ru.cytogen.icbraindb.repository

import org.springframework.stereotype.Repository
import ru.cytogen.icbraindb.model.db.questionnaire.Test

@Repository
interface TestRepository : BlockingFindByIDRepository<Test, Int>, ManagedRepository<Test> {
}
