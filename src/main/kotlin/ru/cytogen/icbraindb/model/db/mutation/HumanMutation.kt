package ru.cytogen.icbraindb.model.db.mutation

import jakarta.persistence.*
import ru.cytogen.icbraindb.model.db.AbstractIntEntity

@Entity
@Table(name = "human_mutations")
class HumanMutation(
    @Column(name = "human", nullable = false)
    val human: String,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "snp_id")
    val snp: Snp,
    @Column(name = "type", nullable = false, columnDefinition = "integer")
    @Enumerated
    var type: MutationType,
    @Column(name = "mutation", nullable = false)
    var mutation: String,
    //TODO: написать миграцию и поменять на bigint
) : AbstractIntEntity() {
}