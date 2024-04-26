package ru.cytogen.icbraindb.config.validation.minmaxfilter

import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import ru.cytogen.icbraindb.filter.types.MinMaxFilter

class LongMinMaxValidator : ConstraintValidator<MinMaxValidation, MinMaxFilter<Long>> {
    private val delegate = MinMaxValidator<Long>()
    override fun isValid(p0: MinMaxFilter<Long>?, p1: ConstraintValidatorContext): Boolean {
        return delegate.isValid(p0, p1)
    }
}