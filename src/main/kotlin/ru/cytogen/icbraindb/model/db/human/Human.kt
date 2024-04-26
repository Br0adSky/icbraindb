package ru.cytogen.icbraindb.model.db.human

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
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "human_nationalities",
        joinColumns = [JoinColumn(name = "human_id")],
        inverseJoinColumns = [JoinColumn(name = "nationality_id")]
    )
    @OrderBy("nationality")
    val nationalities: MutableSet<Nationality> = mutableSetOf()

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "human_diseases",
        joinColumns = [JoinColumn(name = "human_id")],
        inverseJoinColumns = [JoinColumn(name = "disease_id")]
    )
    val diseases: MutableSet<Disease> = mutableSetOf()
}
