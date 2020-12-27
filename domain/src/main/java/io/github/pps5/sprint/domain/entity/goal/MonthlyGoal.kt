package io.github.pps5.sprint.domain.entity.goal

import io.github.pps5.sprint.domain.valueobject.GoalTitle
import io.github.pps5.sprint.domain.valueobject.Option
import org.threeten.bp.LocalDate
import org.threeten.bp.YearMonth
import org.threeten.bp.format.DateTimeFormatter

data class MonthlyGoal(
    val yearMonth: YearMonth,
    override val title: Option<GoalTitle>,
    override val completedDate: Option<LocalDate>,
) : Goal {

    companion object {
        private const val DATE_PATTERN = "yyyy/MM"
    }

    override fun getDateString(): String {
        return DateTimeFormatter.ofPattern(DATE_PATTERN).format(yearMonth)
    }
}
