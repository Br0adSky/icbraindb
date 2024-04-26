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
@Pattern(regexp = "^[A-z]{1,3}\$")
annotation class District(
    val message: String = "Does not comply with the ISO 3166-2 standard",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
