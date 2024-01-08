package ru.cytogen.icbraindb.model

import jakarta.persistence.*

@Entity
@Table(name = "mutation_snp")
class Mutation(
    @ManyToOne
    val gene: Gene,
    @Column(name = "mutation", nullable = false)
    val mutation: String,
    @Column(name = "position", nullable = false)
    val position: Long,
    @Column(name = "ref_nucl", nullable = false)
    val referenceNucleotide: String,
    @Column(name = "type", nullable = false, columnDefinition = "integer")
    @Enumerated
    val type: MutationType
) : AbstractLongEntity() {
  @ManyToMany
  @JoinTable(
      name = "human_mutations",
      joinColumns = [JoinColumn(name = "snp_id")],
      inverseJoinColumns = [JoinColumn(name = "human")])
  val humans: MutableList<Human> = mutableListOf()
}
