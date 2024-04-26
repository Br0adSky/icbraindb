package ru.cytogen.icbraindb.model.db.questionnaire

import jakarta.persistence.*

@Entity
@Table(name = "test")
class Test(
    @Column(name = "name", nullable = false)
    var name: String,
    @Column(name = "description")
    var description: String?,
    @Column(name = "description_en")
    var descriptionEN: String?,
    @Column(name = "name_en")
    var nameEN: String?,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "test", nullable = false, unique = true)
    val id: Int? = null

    @OneToMany(mappedBy = "test", fetch = FetchType.LAZY, cascade = [CascadeType.PERSIST, CascadeType.REMOVE])
    var questions: Set<Question> = setOf()

    @OneToMany(mappedBy = "test", fetch = FetchType.LAZY, cascade = [CascadeType.PERSIST, CascadeType.REMOVE])
    var responses: Set<Response> = setOf()

    @OneToMany(mappedBy = "test", fetch = FetchType.LAZY, cascade = [CascadeType.PERSIST, CascadeType.REMOVE])
    var summaries: Set<Summary> = setOf()
}
