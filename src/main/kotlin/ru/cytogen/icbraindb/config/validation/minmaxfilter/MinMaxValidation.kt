package ru.cytogen.icbraindb.config.validation.minmaxfilter

import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.TYPE, AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.CLASS, AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(
    validatedBy = [
        IntMinMaxValidator::class,
        DoubleMinMaxValidator::class,
        LongMinMaxValidator::class]
)
annotation class MinMaxValidation(
    val message: String = "",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
