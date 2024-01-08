package ru.cytogen.icbraindb.model

import jakarta.persistence.*

@MappedSuperclass
open class AbstractLongEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = true)
  val id: Long? = null
}
