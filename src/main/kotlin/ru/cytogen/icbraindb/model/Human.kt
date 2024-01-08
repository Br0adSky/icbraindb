package ru.cytogen.icbraindb.model

import jakarta.persistence.*

@Entity
@Table(name = "human")
class Human(
    @Id
    @Column(name = "human", nullable = false, unique = true)
    val id: String,
    @Column(name = "age")
    val age: Int?,
    @Column(name = ("comment"))
    val comments: String?,
    @Column(name = ("ethnos"))
    val ethnos: String?,
    @ManyToOne
    @JoinColumn(name = "city")
    val city: City?,
    @Column(name = "is_male")
    val sex: Boolean?,
    @Column(name = "is_migrant")
    val isMigrant: Boolean?
) {
  @ManyToMany
  @JoinTable(
      name = "human_nationalities",
      joinColumns = [JoinColumn(name = "human_id")],
      inverseJoinColumns = [JoinColumn(name = "nationality_id")])
  @OrderBy("nationality")
  val nationalities: MutableList<Nationality> = mutableListOf()

  @OneToMany(mappedBy = "human")
  val diseases: MutableList<Disease> = mutableListOf()

  @ManyToMany
  @JoinTable(
      name = "human_mutations",
      joinColumns = [JoinColumn(name = "human")],
      inverseJoinColumns = [JoinColumn(name = "snp_id")])
  val mutations: MutableList<Mutation> = mutableListOf()

  @OneToMany(mappedBy = "human")
  val eegFiles: MutableList<EEGFile> = mutableListOf()

  @OneToMany(mappedBy = "human")
  val summaries: MutableList<HumanTestSummaries> = mutableListOf()
}
