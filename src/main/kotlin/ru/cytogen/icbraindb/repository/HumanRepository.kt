package ru.cytogen.icbraindb.repository

import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository
import ru.cytogen.icbraindb.model.db.human.Human

@Repository
interface HumanRepository : BlockingFindByIDRepository<Human, String>, JpaSpecificationExecutor<Human>, ManagedRepository<Human> {
}