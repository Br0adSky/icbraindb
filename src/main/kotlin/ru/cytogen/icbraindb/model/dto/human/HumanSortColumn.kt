package ru.cytogen.icbraindb.model.dto.human

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.domain.Sort
import ru.cytogen.icbraindb.dto.common.SortColumn
import ru.cytogen.icbraindb.model.db.human.City_
import ru.cytogen.icbraindb.model.db.human.Disease_
import ru.cytogen.icbraindb.model.db.human.Human_
import ru.cytogen.icbraindb.model.db.human.Nationality_

enum class HumanSortColumn(private val sort: Sort.Order) : SortColumn {
    @JsonProperty("age")
    AGE(Sort.Order.by(Human_.AGE)),

    @JsonProperty("id")
    ID(Sort.Order.by(Human_.ID)),

    @JsonProperty("comments")
    COMMENTS(Sort.Order.by(Human_.COMMENTS)),

    @JsonProperty("ethnos")
    ETHNOS(Sort.Order.by(Human_.ETHNOS)),

    @JsonProperty("cityName")
    CITY(Sort.Order.by("${Human_.CITY}.${City_.CITY_NAME}")),

    @JsonProperty("cityDistrict")
    DISTRICT(Sort.Order.by("${Human_.CITY}.${City_.DISTRICT_NAME}")),

    @JsonProperty("cityDistrictCountry")
    COUNTRY(Sort.Order.by("${Human_.CITY}.${City_.COUNTRY_CODE}")),

    @JsonProperty("sex")
    SEX(Sort.Order.by(Human_.SEX)),

    @JsonProperty("isMigrant")
    MIGRANT(Sort.Order.by(Human_.IS_MIGRANT)),

    @JsonProperty("nationalityName")
    NATIONALITY(Sort.Order.by("${Human_.NATIONALITIES}.${Nationality_.NATIONALITY}")),

    @JsonProperty("diseaseName")
    DISEASE(Sort.Order.by("${Human_.DISEASES}.${Disease_.DISEASE}"));

    override fun getOrder(): Sort.Order {
        return sort
    }
}
