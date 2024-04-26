package ru.cytogen.icbraindb.model.db.questionnaire

import jakarta.persistence.*
import ru.cytogen.icbraindb.model.db.AbstractIntEntity

@Entity
@Table(name = "testresponsetype")
class Response(
    @Column(name = "text", nullable = false)
    var response: String,
    @Column(name = "value", nullable = false)
    val value: Int,
    @Column(name = "position", nullable = false)
    val position: Int,
    @Column(name = "text_en")
    var responseEN: String?,
    @ManyToOne
    @JoinColumn(name = "test", nullable = false)
    val test: Test
) : AbstractIntEntity() {}
