package ru.cytogen.icbraindb.model

import jakarta.persistence.*

@Entity
@Table(name = "mutation")
class Mutation(
    @Id
    @Column(name = "id", nullable = false, unique = true)
    val id: Long,
    @Column(name = "chromosome", nullable = false)
    val chromosome: String,
    @Column(name = "gene", nullable = false)
    val gene: String,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="human", nullable=false)
    val human: Human,
    @Column(name = "mutation", nullable = false)
    val mutation: String,
    @Column(name = "position", nullable = false)
    val position: Long,
    @Column(name = "ref_nucl", nullable = false)
    val referenceNucleotide: String,
    @Column(name = "type", nullable = false)
    val type: Int
) {
}