package ru.cytogen.icbraindb.config.validation.nationality

import jakarta.validation.ConstraintValidator
import ru.cytogen.icbraindb.model.dto.human.NationalityDto

interface NationalityValidator<A : Annotation> : ConstraintValidator<A, NationalityDto> {
}