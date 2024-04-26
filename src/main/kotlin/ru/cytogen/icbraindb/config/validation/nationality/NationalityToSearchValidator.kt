package ru.cytogen.icbraindb.config.validation.nationality

import jakarta.validation.ConstraintValidatorContext
import ru.cytogen.icbraindb.model.dto.human.NationalityDto

class NationalityToSearchValidator : NationalityValidator<NationalityToSearchValidation> {
    override fun isValid(p0: NationalityDto, p1: ConstraintValidatorContext?): Boolean {
        return p0.id != null || p0.nationality != null || p0.nationalityEn != null
    }
}