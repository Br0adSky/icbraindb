package ru.cytogen.icbraindb.model

import jakarta.persistence.*

@Entity
@Table(name = "test")
class Test(
    @Column(name = "name", nullable = false)
    val name: String,
    @Column(name = "description")
    val description: String?,
    @Column(name = "description_en")
    val descriptionEN: String?,
    @Column(name = "name_en")
    val nameEN: String?,
) {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "test", nullable = false, unique = true)
  val id: Int? = null

  @OneToMany(mappedBy = "test")
  val questions: MutableList<Question> = mutableListOf()

  @OneToMany(mappedBy = "test")
  val responses: MutableList<Response> = mutableListOf()

  @OneToMany(mappedBy = "test")
  val summaries: MutableList<Summary> = mutableListOf()
}
