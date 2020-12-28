package io.github.pps5.sprint.usecase

import androidx.annotation.VisibleForTesting
import io.github.pps5.sprint.data.repository.GoalRepository
import io.github.pps5.sprint.domain.entity.goal.DailyGoal
import io.github.pps5.sprint.domain.entity.goal.Goal
import io.github.pps5.sprint.domain.entity.goal.MonthlyGoal
import io.github.pps5.sprint.domain.entity.goal.WeeklyGoal
import org.koin.dsl.module

val updateGoalUseCaseModule = module {
    factory<UpdateGoalUseCase> { UpdateGoalInteractor(get()) }
}

interface UpdateGoalUseCase {
    suspend operator fun invoke(goal: Goal)
}

@VisibleForTesting
class UpdateGoalInteractor(
    private val goalRepository: GoalRepository
) : UpdateGoalUseCase {
    override suspend fun invoke(goal: Goal) {
        if (goal.isSet()) {
            insertOrUpdateGoal(goal)
        } else {
            deleteGoal(goal)
        }
    }

    private suspend fun insertOrUpdateGoal(goal: Goal) {
        when (goal) {
            is MonthlyGoal -> goalRepository.updateOrInsertMonthlyGoal(goal)
            is WeeklyGoal -> goalRepository.updateOrInsertWeeklyGoal(goal)
            is DailyGoal -> goalRepository.updateOrInsertDailyGoal(goal)
        }
    }

    private suspend fun deleteGoal(goal: Goal) {
        when (goal) {
            is MonthlyGoal -> goalRepository.deleteMonthlyGoal(goal)
            is WeeklyGoal -> goalRepository.deleteWeeklyGoal(goal)
            is DailyGoal -> goalRepository.deleteDailyGoal(goal)
        }

    }
}

