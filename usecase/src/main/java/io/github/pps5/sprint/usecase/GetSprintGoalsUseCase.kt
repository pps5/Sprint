package io.github.pps5.sprint.usecase

import io.github.pps5.sprint.domain.entity.goal.DailyGoal
import io.github.pps5.sprint.domain.entity.goal.MonthlyGoal
import io.github.pps5.sprint.domain.entity.goal.WeeklyGoal
import kotlinx.coroutines.flow.Flow
import org.threeten.bp.LocalDate

interface GetSprintGoalsUseCase {
    suspend operator fun invoke(day: LocalDate): Flow<Response>

    class Response(
        val dailyGoals: List<DailyGoal>,
        val weeklyGoal: WeeklyGoal,
        val monthlyGoal: MonthlyGoal,
    )
}
