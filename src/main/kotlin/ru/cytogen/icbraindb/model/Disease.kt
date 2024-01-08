package ru.cytogen.icbraindb.model

import jakarta.persistence.*

@Entity
@Table(name = "human_diseases")
class Disease(
    @ManyToOne
    @JoinColumn(name = "human_id", nullable = false)
    val human: Human,
    @Column(name = "disease", nullable = false)
    val disease: String
) : AbstractLongEntity()
