package ru.cytogen.icbraindb.model.db.mutation

import jakarta.persistence.*
import ru.cytogen.icbraindb.model.db.AbstractLongEntity

@Entity
@Table(name = "mutation_snp")
class Snp(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gene_id", nullable = false)
    val gene: Gene,
    @Column(name = "position", nullable = false)
    var position: Long,
    @Column(name = "ref_nucl", nullable = false)
    var referenceNucleotide: String,
) : AbstractLongEntity() {

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "human_mutations", joinColumns = [JoinColumn(name = "snp_id")])
    @Column(name = "id")
    val mutations: Set<Int> = setOf()
}
