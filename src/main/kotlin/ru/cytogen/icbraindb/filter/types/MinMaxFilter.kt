package ru.cytogen.icbraindb.filter.types

import jakarta.validation.constraints.AssertTrue

class MinMaxFilter(
    val min: Long?,
    val max: Long?
) : BaseFilter() {
  @AssertTrue(message = "min or max required")
  fun isMinOrMax(): Boolean {
    return min != null || max != null
  }

  @AssertTrue(message = "min mast be less than max")
  fun isMinLessThanMax(): Boolean {
    if (max != null && min != null) {
      return max > min
    }

    return isMinOrMax()
  }
}
