package ru.cytogen.icbraindb.model.db.mutation

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Enumerated
import jakarta.persistence.Table
import ru.cytogen.icbraindb.model.db.AbstractIntEntity

@Entity
@Table(name = "human_mutations")
class HumanMutationToSave(
    @Column(name = "human", nullable = false)
    val human: String,
    @Column(name = "snp_id")
    val snp: Long,
    @Column(name = "type", nullable = false, columnDefinition = "integer")
    @Enumerated
    val type: MutationType,
    @Column(name = "mutation", nullable = false)
    val mutation: String
) : AbstractIntEntity() {
}