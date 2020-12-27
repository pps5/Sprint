package io.github.pps5.sprint.domain.entity.goal

import io.github.pps5.sprint.domain.valueobject.GoalTitle
import io.github.pps5.sprint.domain.valueobject.Option
import org.threeten.bp.LocalDate

interface Goal {
    val title: Option<GoalTitle>
    val completedDate: Option<LocalDate>

    fun isSet(): Boolean {
        return title is Option.Some
    }

    fun getDateString(): String
}