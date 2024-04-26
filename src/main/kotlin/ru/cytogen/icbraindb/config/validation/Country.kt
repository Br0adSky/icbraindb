package ru.cytogen.icbraindb.config.validation

import jakarta.validation.Payload
import jakarta.validation.constraints.Pattern
import kotlin.reflect.KClass

@Target(
    AnnotationTarget.TYPE,
    AnnotationTarget.ANNOTATION_CLASS,
    AnnotationTarget.CLASS,
    AnnotationTarget.FIELD,
    AnnotationTarget.VALUE_PARAMETER
)
@Retention(AnnotationRetention.RUNTIME)
@Pattern(regexp = "^[A-z]{2,3}\$")
annotation class Country(
    val message: String = "Does not comply with ISO 3166-1 alpha-2/alpha-3 standard",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
