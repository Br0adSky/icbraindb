package ru.cytogen.icbraindb.config.validation

import jakarta.validation.ConstraintValidator
import ru.cytogen.icbraindb.model.dto.AbstractIdDto

class LongEditAbstractIdValidator : EditAbstractIdValidator<Long>(),
    ConstraintValidator<EditAbstractIdValidation, AbstractIdDto<Long>>