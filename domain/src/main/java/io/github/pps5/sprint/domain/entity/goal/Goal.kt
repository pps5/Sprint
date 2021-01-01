package io.github.pps5.sprint.domain.entity.goal

import io.github.pps5.sprint.domain.valueobject.GoalTitle
import io.github.pps5.sprint.domain.valueobject.Option
import org.threeten.bp.LocalDate

interface Goal {
    var title: Option<GoalTitle>
    var completedDate: Option<LocalDate>

    fun isSet(): Boolean {
        return title is Option.Some
    }

    fun isCompleted(): Boolean {
        return completedDate is Option.Some
    }

    fun toggleComplete(date: LocalDate) {
        completedDate = if (completedDate is Option.Some) {
            Option.None()
        } else {
            Option.Some(date)
        }
    }

    fun getDateString(): String
}