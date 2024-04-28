package ru.cytogen.icbraindb.config.validation.city

import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.TYPE, AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.CLASS, AnnotationTarget.FIELD,
    AnnotationTarget.VALUE_PARAMETER
)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(
    validatedBy = [CityToSearchValidator::class]
)
annotation class CityToSearchValidation(
    val message: String = "At least country or id must be present",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
