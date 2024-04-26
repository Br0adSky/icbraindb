package ru.cytogen.icbraindb.repository

import org.springframework.stereotype.Repository
import ru.cytogen.icbraindb.model.db.mutation.HumanMutationToSave

@Repository
interface MutationToSaveRepository : BlockingFindByIDRepository<HumanMutationToSave, Int>, ManagedRepository<HumanMutationToSave> {
}