package ru.cytogen.icbraindb.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository
import ru.cytogen.icbraindb.model.Human

@Repository
interface HumanRepository : JpaRepository<Human, String>, JpaSpecificationExecutor<Human> {
}