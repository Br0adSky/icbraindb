package ru.cytogen.icbraindb.model

import jakarta.persistence.*

@Entity
@Table(name = "testresponsetype")
class Response(
    @Column(name = "text", nullable = false)
    val response: String,
    @Column(name = "value", nullable = false)
    val value: Int,
    @Column(name = "position", nullable = false)
    val position: Int,
    @Column(name = "text_en")
    val responseEN: String?,
    @ManyToOne
    @JoinColumn(name = "test", nullable = false)
    val test: Test
) : AbstractIntEntity() {}
