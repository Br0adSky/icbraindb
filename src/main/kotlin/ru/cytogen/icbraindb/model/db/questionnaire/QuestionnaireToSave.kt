package ru.cytogen.icbraindb.model.db.questionnaire

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import ru.cytogen.icbraindb.model.db.AbstractIntEntity

@Entity
@Table(name = "testsummary")
class QuestionnaireToSave(
    @Column(name = "human", nullable = false)
    val human: String,
    @Column(name = "value", nullable = false)
    val value: Double,
    @Column(name = "testsummary_id", nullable = false)
    val summary: Int
) : AbstractIntEntity() {
}
