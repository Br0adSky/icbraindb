package ru.cytogen.icbraindb.model

import jakarta.persistence.*

@Entity
@Table(name = "genes")
class Gene(
    @Column(name = "chromosome", nullable = false)
    val chromosome: String,
    @Column(name = "gene_name", nullable = false)
    val geneName: String
) : AbstractLongEntity()
