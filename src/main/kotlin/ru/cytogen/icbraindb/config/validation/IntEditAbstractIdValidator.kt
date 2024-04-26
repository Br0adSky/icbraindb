package ru.cytogen.icbraindb.config.validation

import jakarta.validation.ConstraintValidator
import ru.cytogen.icbraindb.model.dto.AbstractIdDto

class IntEditAbstractIdValidator : EditAbstractIdValidator<Int>(),
    ConstraintValidator<EditAbstractIdValidation, AbstractIdDto<Int>>