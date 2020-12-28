package io.github.pps5.sprint.data.internal.mapper

import io.github.pps5.sprint.data.internal.entity.goal.MonthlyGoalEntity
import io.github.pps5.sprint.domain.entity.goal.MonthlyGoal
import io.github.pps5.sprint.domain.valueobject.GoalTitle
import io.github.pps5.sprint.domain.valueobject.Option
import org.threeten.bp.YearMonth

internal fun List<MonthlyGoalEntity>.toDomain(): List<MonthlyGoal> {
    return this.map(MonthlyGoalEntity::toDomain)
}

private fun MonthlyGoalEntity.toDomain(): MonthlyGoal {
    return MonthlyGoal(
        yearMonth = YearMonth.from(this.startDate),
        title = Option.apply(GoalTitle.of(this.title)),
        completedDate = Option.apply(this.completedDate)
    )
}
