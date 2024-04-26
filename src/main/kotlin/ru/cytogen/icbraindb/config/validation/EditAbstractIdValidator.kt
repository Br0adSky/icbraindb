package ru.cytogen.icbraindb.config.validation

import jakarta.validation.ConstraintValidatorContext
import ru.cytogen.icbraindb.model.dto.AbstractIdDto

open class EditAbstractIdValidator<T : Any> {
    fun isValid(p0: AbstractIdDto<T>, p1: ConstraintValidatorContext?): Boolean {
        return p0.id != null
    }
}