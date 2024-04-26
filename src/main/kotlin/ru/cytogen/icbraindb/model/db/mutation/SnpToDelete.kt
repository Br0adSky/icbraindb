package ru.cytogen.icbraindb.model.db.mutation

import jakarta.persistence.*
import ru.cytogen.icbraindb.model.db.AbstractLongEntity

@Entity
@Table(name = "mutation_snp")
class SnpToDelete(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gene_id", nullable = false)
    val gene: Gene,
) : AbstractLongEntity() {
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "human_mutations", joinColumns = [JoinColumn(name = "snp_id")])
    @Column(name = "id")
    val mutations: Set<Int> = setOf()
}
