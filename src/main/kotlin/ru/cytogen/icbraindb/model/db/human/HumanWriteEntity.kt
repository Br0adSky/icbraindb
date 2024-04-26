package ru.cytogen.icbraindb.model.db.human

import jakarta.persistence.*

@Entity
@Table(name = "human")
class HumanWriteEntity(
    @Id
    @Column(name = "human", nullable = false, unique = true)
    val id: String,
    @Column(name = "age")
    val age: Int?,
    @Column(name = ("comment"))
    val comments: String?,
    @Column(name = ("ethnos"))
    val ethnos: String?,
    @Column(name = "city")
    val city: Long?,
    @Column(name = "is_male")
    val sex: Boolean?,
    @Column(name = "is_migrant")
    val isMigrant: Boolean?,
    @ElementCollection
    @CollectionTable(name = "human_nationalities", joinColumns = [JoinColumn(name = "human_id")])
    @Column(name = "nationality_id")
    val nationalities: Set<Long>,
    @ElementCollection
    @CollectionTable(name = "human_diseases", joinColumns = [JoinColumn(name = "human_id")])
    @Column(name = "disease_id")
    val diseases: Set<Long>
) {
}