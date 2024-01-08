package ru.cytogen.icbraindb.model

import jakarta.persistence.*

@Entity
@Table(name = "testquestion")
class Question(
    @ManyToOne
    @JoinColumn(name = "test", nullable = false)
    val test: Test,
    @Column(name = "question", nullable = false)
    val question: String,
    @Column(name = "question_en")
    val questionEN: String?,
    @Column(name = "position", nullable = false)
    val position: Int
) : AbstractIntEntity()
