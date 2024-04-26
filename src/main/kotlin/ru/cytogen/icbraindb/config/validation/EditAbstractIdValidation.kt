package ru.cytogen.icbraindb.config.validation

import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@Target(
    AnnotationTarget.TYPE,
    AnnotationTarget.ANNOTATION_CLASS,
    AnnotationTarget.CLASS,
    AnnotationTarget.FIELD,
    AnnotationTarget.VALUE_PARAMETER
)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(
    validatedBy = [LongEditAbstractIdValidator::class, IntEditAbstractIdValidator::class]
)
annotation class EditAbstractIdValidation(
    val message: String = "Property \"id\" must be present",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
