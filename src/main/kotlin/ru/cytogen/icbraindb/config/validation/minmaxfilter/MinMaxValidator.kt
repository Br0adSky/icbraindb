package ru.cytogen.icbraindb.config.validation.minmaxfilter

import jakarta.validation.ConstraintValidatorContext
import ru.cytogen.icbraindb.filter.types.MinMaxFilter

class MinMaxValidator<T : Comparable<T>> {
    fun isValid(value: MinMaxFilter<T>?, context: ConstraintValidatorContext): Boolean {
        if (value == null) {
            return true
        }
        if (value.min == null && value.max == null) {
            context.disableDefaultConstraintViolation()
            context.buildConstraintViolationWithTemplate("min or max required").addConstraintViolation()
            return false
        }

        if ((value.max != null && value.min != null) && value.min > value.max) {
            context.disableDefaultConstraintViolation()
            context.buildConstraintViolationWithTemplate("min must be less than max").addConstraintViolation()
            return false
        }

        return true
    }
}