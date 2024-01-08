package ru.cytogen.icbraindb.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "districts")
class District(
    @Column(name = "r_country", nullable = false)
    val countryCode: String,

    @Column(name = "r_district")
    val districtName: String?
) : AbstractLongEntity()
