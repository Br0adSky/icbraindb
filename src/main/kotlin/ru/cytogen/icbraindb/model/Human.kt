package ru.cytogen.icbraindb.model

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "human")
class Human(
    @Id
    @Column(name = "human", nullable = false, unique = true)
    val id: String,
    @Column(name = "age", nullable = false)
    val age: Int,
    @Column(name = ("comment"))
    val comments: String?,
    @Column(name = ("ethnos"))
    val ethos: String?,
    @Column(name = ("nationality"))
    val nationality: String?,
    @Column(name = ("nationality_en"))
    val nationalityEn: String?,
    @Column(name = ("r_city"))
    val city: String?,
    @Column(name = ("r_country"))
    val country: String?,
    @Column(name = ("r_district"))
    val district: String?,
    @Column(name = "sex", nullable = false)
    val sex: Int
) {

    @OneToMany(mappedBy = "human", orphanRemoval = true, cascade = [CascadeType.ALL], targetEntity = Mutation::class)
    val mutations: MutableList<Mutation> = mutableListOf()
}