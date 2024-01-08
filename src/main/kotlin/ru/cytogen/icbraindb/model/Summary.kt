package ru.cytogen.icbraindb.model

import jakarta.persistence.*

@Entity
@Table(name = "testsummary2test")
class Summary(
    @ManyToOne
    @JoinColumn(name = "test", nullable = false)
    val test: Test,
    @Column(name = "testsummaryalias", nullable = false)
    val alias: String,
    @Column(name = "description")
    val description: String?,
    @Column(name = "description_en")
    val descriptionEN: String?
) : AbstractIntEntity() {
}
