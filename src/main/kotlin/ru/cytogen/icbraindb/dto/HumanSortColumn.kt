package ru.cytogen.icbraindb.dto

import org.springframework.data.domain.Sort
import ru.cytogen.icbraindb.dto.common.SortColumn
import ru.cytogen.icbraindb.model.City_
import ru.cytogen.icbraindb.model.District_
import ru.cytogen.icbraindb.model.Human_
import ru.cytogen.icbraindb.model.Nationality_

enum class HumanSortColumn(private val sort: Sort) : SortColumn {
  AGE(Sort.by(Human_.AGE)),
  ID(Sort.by(Human_.ID)),
  COMMENTS(Sort.by(Human_.COMMENTS)),
  ETHNOS(Sort.by(Human_.ETHNOS)),
  CITY(Sort.by("${Human_.CITY}.${City_.CITY_NAME}")),
  DISTRICT(Sort.by("${Human_.CITY}.${City_.DISTRICT}.${District_.DISTRICT_NAME}")),
  COUNTRY(Sort.by("${Human_.CITY}.${City_.DISTRICT}.${District_.COUNTRY_CODE}")),
  SEX(Sort.by(Human_.SEX)),
  MIGRANT(Sort.by(Human_.IS_MIGRANT)),
  NATIONALITY(Sort.by("${Human_.NATIONALITIES}.${Nationality_.NATIONALITY}"));

  override fun getSort(): Sort {
    return sort
  }
}
