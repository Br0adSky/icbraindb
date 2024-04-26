package ru.cytogen.icbraindb.repository

import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository
import ru.cytogen.icbraindb.model.db.mutation.HumanMutation

@Repository
interface MutationRepository : BlockingFindByIDRepository<HumanMutation, Int>, JpaSpecificationExecutor<HumanMutation> {
}