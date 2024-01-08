package ru.cytogen.icbraindb.model

import jakarta.persistence.*

@Entity
@Table(name = "cities")
class City(
    @Column(name = "r_city")
    val cityName: String?,

    @ManyToOne
    @JoinColumn(name = "r_district")
    val district: District
) : AbstractLongEntity() {
}
