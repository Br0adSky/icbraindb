package ru.cytogen.icbraindb.config.validation.city

import jakarta.validation.ConstraintValidatorContext
import ru.cytogen.icbraindb.model.dto.human.CityDto

class CityToSearchValidator : CityValidator<CityToSearchValidation> {
    override fun isValid(p0: CityDto?, p1: ConstraintValidatorContext?): Boolean {
        return p0 == null || p0.id != null || p0.country != null
    }
}
