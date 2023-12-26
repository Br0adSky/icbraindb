package ru.cytogen.icbraindb.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository
import ru.cytogen.icbraindb.model.Mutation

@Repository
interface MutationRepository : JpaRepository<Mutation, Long>, JpaSpecificationExecutor<Mutation> {
}