package ru.cytogen.icbraindb.service

import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.Expression
import jakarta.persistence.criteria.Predicate
import org.springframework.data.jpa.repository.query.EscapeCharacter
import ru.cytogen.icbraindb.filter.types.*

object CommonSpecification {
    fun likeOrEqual(
        builder: CriteriaBuilder,
        path: Expression<String>,
        value: StringFilter
    ): Predicate {
        val predicate = if (value.exactSearch) {
            builder.equal(path, value.value)
        } else {
            builder.like(
                builder.upper(path),
                "%${EscapeCharacter.DEFAULT.escape(value.value.uppercase())}%",
                EscapeCharacter.DEFAULT.escapeCharacter
            )
        }

        return includeNull(builder, path, value, predicate)
    }

    fun <T : Comparable<T>> minMax(
        builder: CriteriaBuilder,
        path: Expression<T>,
        value: MinMaxFilter<T>
    ): Predicate {
        val predicate = when {
            value.min == null -> builder.lessThan(path, value.max!!)
            value.max == null -> builder.greaterThan(path, value.min)
            else -> builder.between(path, value.min, value.max)
        }

        return includeNull(builder, path, value, predicate)
    }

    fun equal(
        builder: CriteriaBuilder,
        path: Expression<String>,
        value: ExactStringFilter
    ): Predicate {
        return builder.equal(path, value.value)
    }

    fun booleanWithNullCheck(
        builder: CriteriaBuilder,
        path: Expression<Boolean>,
        value: BooleanFilter
    ): Predicate {
        val predicate = if (value.value) {
            builder.isTrue(path)
        } else {
            builder.isFalse(path)
        }
        return includeNull(builder, path, value, predicate)
    }

    private fun <Y> includeNull(
        builder: CriteriaBuilder,
        path: Expression<Y>,
        value: BaseFilter,
        predicate: Predicate
    ): Predicate {
        if (!value.includeNull) {
            return predicate
        }

        val isNull = builder.isNull(path)
        return builder.or(isNull, predicate)
    }
}
