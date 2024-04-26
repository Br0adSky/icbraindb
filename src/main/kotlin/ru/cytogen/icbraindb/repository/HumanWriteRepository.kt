package ru.cytogen.icbraindb.repository

import org.springframework.stereotype.Repository
import ru.cytogen.icbraindb.model.db.human.HumanWriteEntity

@Repository
interface HumanWriteRepository : BlockingFindByIDRepository<HumanWriteEntity, String>, ManagedRepository<HumanWriteEntity> {
}