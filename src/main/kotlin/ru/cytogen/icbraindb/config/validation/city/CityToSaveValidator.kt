package ru.cytogen.icbraindb.config.validation.city

import jakarta.validation.ConstraintValidatorContext
import ru.cytogen.icbraindb.model.dto.human.CityDto

class CityToSaveValidator : CityValidator<CityToSaveValidation> {
    override fun isValid(p0: CityDto, p1: ConstraintValidatorContext?): Boolean {
        return p0.country != null
    }
}
