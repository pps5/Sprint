package io.github.pps5.sprint.data.internal.mapper

import io.github.pps5.sprint.data.internal.entity.goal.DailyGoalEntity
import io.github.pps5.sprint.domain.entity.goal.DailyGoal
import io.github.pps5.sprint.domain.valueobject.GoalTitle
import io.github.pps5.sprint.domain.valueobject.Option

internal fun List<DailyGoalEntity>.toDomain(): List<DailyGoal> {
    return this.map(DailyGoalEntity::toDomain)
}

private fun DailyGoalEntity.toDomain(): DailyGoal {
    return DailyGoal(
        date = this.date,
        title = Option.apply(GoalTitle.of(this.title)),
        completedDate = Option.apply(this.completedDate)
    )
}

