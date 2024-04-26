package ru.cytogen.icbraindb.config.validation.minmaxfilter

import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import ru.cytogen.icbraindb.filter.types.MinMaxFilter

class IntMinMaxValidator : ConstraintValidator<MinMaxValidation, MinMaxFilter<Int>> {
    private val delegate = MinMaxValidator<Int>()
    override fun isValid(p0: MinMaxFilter<Int>?, p1: ConstraintValidatorContext): Boolean {
       return delegate.isValid(p0, p1)
    }
}