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
@Pattern(regexp = "^[А-я,.\\- ?\":;!()\\[\\]/]+\$")
annotation class CyrillicSentence(
    val message: String = "Property must be Cyrillic",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
