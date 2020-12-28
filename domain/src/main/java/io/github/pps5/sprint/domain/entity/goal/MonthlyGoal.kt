package io.github.pps5.sprint.domain.entity.goal

import io.github.pps5.sprint.domain.valueobject.GoalTitle
import io.github.pps5.sprint.domain.valueobject.Option
import org.threeten.bp.LocalDate
import org.threeten.bp.YearMonth
import org.threeten.bp.format.DateTimeFormatter

class MonthlyGoal(
    val yearMonth: YearMonth,
    override var title: Option<GoalTitle>,
    override var completedDate: Option<LocalDate>,
) : Goal {

    companion object {
        private const val DATE_PATTERN = "yyyy/MM"
    }

    override fun getDateString(): String {
        return DateTimeFormatter.ofPattern(DATE_PATTERN).format(yearMonth)
    }

    override fun equals(other: Any?): Boolean {
        return this.yearMonth == (other as? MonthlyGoal)?.yearMonth
    }

    override fun hashCode(): Int {
        return yearMonth.hashCode()
    }

    override fun toString(): String {
        return "MonthlyGoal(yearMonth=$yearMonth, title=$title, completedDate=$completedDate)"
    }

}
