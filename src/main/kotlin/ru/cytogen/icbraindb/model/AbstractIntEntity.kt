package ru.cytogen.icbraindb.model

import jakarta.persistence.*

@MappedSuperclass
open class AbstractIntEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = true)
  val id: Int? = null
}
