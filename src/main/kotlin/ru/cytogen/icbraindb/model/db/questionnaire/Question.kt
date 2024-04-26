package ru.cytogen.icbraindb.model.db.questionnaire

import jakarta.persistence.*
import ru.cytogen.icbraindb.model.db.AbstractIntEntity

@Entity
@Table(name = "testquestion")
class Question(
    @ManyToOne
    @JoinColumn(name = "test", nullable = false)
    val test: Test,
    @Column(name = "question", nullable = false)
    var question: String,
    @Column(name = "question_en")
    var questionEN: String?,
    @Column(name = "position", nullable = false)
    val position: Int
) : AbstractIntEntity()
