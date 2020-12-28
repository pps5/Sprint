package io.github.pps5.sprint.domain.entity.goal

import io.github.pps5.sprint.domain.valueobject.GoalTitle
import io.github.pps5.sprint.domain.valueobject.Option
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter

class DailyGoal(
    val date: LocalDate,
    override var title: Option<GoalTitle>,
    override var completedDate: Option<LocalDate>,
) : Goal {

    companion object {
        private const val DATE_PATTERN = "yyyy/MM/dd"
    }

    override fun getDateString(): String {
        return DateTimeFormatter.ofPattern(DATE_PATTERN).format(date)
    }

    override fun equals(other: Any?): Boolean {
        return this.date == (other as? DailyGoal)?.date
    }

    override fun hashCode(): Int {
        return date.hashCode()
    }

    override fun toString(): String {
        return "DailyGoal(date=$date, title=$title, completedDate=$completedDate)"
    }
}
