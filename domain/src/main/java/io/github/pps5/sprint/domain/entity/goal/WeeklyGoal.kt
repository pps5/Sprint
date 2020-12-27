package io.github.pps5.sprint.domain.entity.goal

import io.github.pps5.sprint.domain.valueobject.GoalTitle
import io.github.pps5.sprint.domain.valueobject.Option
import io.github.pps5.sprint.domain.valueobject.Week
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter


data class WeeklyGoal(
    val week: Week,
    override val title: Option<GoalTitle>,
    override val completedDate: Option<LocalDate>,
) : Goal {

    companion object {
        private const val DATE_PATTERN = "yyyy/MM/dd"
        private const val DATE_STRING_FORMAT = "%s - %s"
    }

    override fun getDateString(): String {
        val formatter = DateTimeFormatter.ofPattern(DATE_PATTERN)
        return String.format(
            DATE_STRING_FORMAT,
            formatter.format(week.start),
            formatter.format(week.end),
        )
    }
}
