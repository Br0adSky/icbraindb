package ru.cytogen.icbraindb.repository

import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.CriteriaUpdate

interface ManagedRepository<T : Any> {
    fun persist(entity: T)

    fun merge(entity: T): T

    fun executeUpdate(updateQuery: (CriteriaBuilder) -> CriteriaUpdate<T>)
}