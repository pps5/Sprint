package io.github.pps5.sprint.data.internal.mapper

import io.github.pps5.sprint.data.internal.entity.goal.WeeklyGoalEntity
import io.github.pps5.sprint.domain.entity.goal.WeeklyGoal
import io.github.pps5.sprint.domain.valueobject.GoalTitle
import io.github.pps5.sprint.domain.valueobject.Option
import io.github.pps5.sprint.domain.valueobject.Week

internal fun List<WeeklyGoalEntity>.toDomain(): List<WeeklyGoal> {
    return this.map(WeeklyGoalEntity::toDomain)
}

private fun WeeklyGoalEntity.toDomain(): WeeklyGoal {
    return WeeklyGoal(
        week = Week(this.startDate),
        title = Option.apply(GoalTitle.of(this.title)),
        completedDate = Option.apply(this.completedDate)
    )
}
