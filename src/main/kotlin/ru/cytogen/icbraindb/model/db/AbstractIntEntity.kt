package ru.cytogen.icbraindb.model.db

import jakarta.persistence.*

@MappedSuperclass
open class AbstractIntEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = true)
  var id: Int? = null
}
