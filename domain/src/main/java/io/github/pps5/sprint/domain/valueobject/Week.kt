package io.github.pps5.sprint.domain.valueobject

import org.threeten.bp.DayOfWeek
import org.threeten.bp.LocalDate

data class Week(val start: LocalDate) {

    init {
        require(start.dayOfWeek == DayOfWeek.MONDAY) {
            "Start date of week must be Monday, but was ${start.dayOfWeek}"
        }
    }

    val end: LocalDate by lazy { start.plusDays(6) }
}