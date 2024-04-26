package ru.cytogen.icbraindb.config.validation.minmaxfilter

import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import ru.cytogen.icbraindb.filter.types.MinMaxFilter

class DoubleMinMaxValidator : ConstraintValidator<MinMaxValidation, MinMaxFilter<Double>> {
    private val delegate = MinMaxValidator<Double>();
    override fun isValid(p0: MinMaxFilter<Double>?, p1: ConstraintValidatorContext): Boolean {
        return delegate.isValid(p0, p1)
    }
}