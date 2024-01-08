package ru.cytogen.icbraindb.model

import jakarta.persistence.*

@Entity
@Table(name = "nationalities")
class Nationality(
    @Column(name = "nationality", nullable = false)
    val nationality: String,
    @Column(name = "nationality_en", nullable = false)
    val nationalityEN: String
) : AbstractLongEntity()
