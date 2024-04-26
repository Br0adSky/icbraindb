package ru.cytogen.icbraindb.repository

import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository
import ru.cytogen.icbraindb.model.db.human.HumanDeleteEntity

@Repository
interface HumanDeleteRepository : BlockingFindByIDRepository<HumanDeleteEntity, String>, JpaSpecificationExecutor<HumanDeleteEntity>,
    ManagedRepository<HumanDeleteEntity> {
}