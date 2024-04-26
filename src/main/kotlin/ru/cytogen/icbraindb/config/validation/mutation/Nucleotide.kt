package ru.cytogen.icbraindb.config.validation.mutation

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern

@Target(
    AnnotationTarget.TYPE,
    AnnotationTarget.ANNOTATION_CLASS,
    AnnotationTarget.CLASS,
    AnnotationTarget.FIELD,
    AnnotationTarget.VALUE_PARAMETER
)
@Retention(AnnotationRetention.RUNTIME)
@NotBlank
@Pattern(regexp = "^[ACTGactg,]+\$")
annotation class Nucleotide()
