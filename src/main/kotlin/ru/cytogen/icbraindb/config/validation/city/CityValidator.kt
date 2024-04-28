package ru.cytogen.icbraindb.config.validation.city

import jakarta.validation.ConstraintValidator
import ru.cytogen.icbraindb.model.dto.human.CityDto

interface CityValidator<A : Annotation> : ConstraintValidator<A, CityDto> {
}
