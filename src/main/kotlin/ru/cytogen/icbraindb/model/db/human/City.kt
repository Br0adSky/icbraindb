package ru.cytogen.icbraindb.model.db.human

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import ru.cytogen.icbraindb.model.db.AbstractLongEntity

@Entity
@Table(name = "cities")
class City(
    @Column(name = "r_city")
    var cityName: String?,

    @Column(name = "r_country", nullable = false)
    var countryCode: String,

    @Column(name = "r_district")
    var districtName: String?
) : AbstractLongEntity() {
}
