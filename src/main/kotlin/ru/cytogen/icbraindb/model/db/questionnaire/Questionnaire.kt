package ru.cytogen.icbraindb.model.db.questionnaire

import jakarta.persistence.*
import ru.cytogen.icbraindb.model.db.AbstractIntEntity

@Entity
@Table(name = "testsummary")
class Questionnaire(
    @Column(name = "human", nullable = false)
    val human: String,
    @Column(name = "value", nullable = false)
    var value: Double,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "summary_id", nullable = false)
    val summary: Summary
) : AbstractIntEntity() {
}
