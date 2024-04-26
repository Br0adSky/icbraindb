package ru.cytogen.icbraindb.repository

import jakarta.persistence.LockModeType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.repository.NoRepositoryBean
import java.util.*

@NoRepositoryBean
interface BlockingFindByIDRepository<T, ID> : JpaRepository<T, ID> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    override fun findById(id: ID): Optional<T>
}
