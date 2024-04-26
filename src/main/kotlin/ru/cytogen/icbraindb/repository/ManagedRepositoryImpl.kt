package ru.cytogen.icbraindb.repository

import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.CriteriaUpdate
import org.springframework.stereotype.Repository


@Repository
class ManagedRepositoryImpl<T : Any>(
    @PersistenceContext
    private val em: EntityManager
) : ManagedRepository<T> {

    override fun persist(entity: T) {
        em.persist(entity)
    }

    override fun merge(entity: T): T {
        return em.merge(entity)
    }

    override fun executeUpdate(updateQuery: (CriteriaBuilder) -> CriteriaUpdate<T>) {
        val query = updateQuery(em.criteriaBuilder)
        em.createQuery(query).executeUpdate()
    }
}