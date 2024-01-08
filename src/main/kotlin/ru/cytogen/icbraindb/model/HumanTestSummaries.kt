package ru.cytogen.icbraindb.model

import jakarta.persistence.*

@Entity
@Table(name = "testsummary")
class HumanTestSummaries(
    @ManyToOne
    @JoinColumn(name = "human", nullable = false)
    val human: Human,
    @Column(name = "value", nullable = false)
    val value: Double,
    @ManyToOne
    @JoinColumn(name = "testsummary_id", nullable = false)
    val summary: Summary
) : AbstractIntEntity() {
}
