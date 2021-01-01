package io.github.pps5.sprint.usecase.internal.interactor

import io.github.pps5.sprint.data.repository.GoalRepository
import io.github.pps5.sprint.domain.entity.goal.DailyGoal
import io.github.pps5.sprint.domain.entity.goal.Goal
import io.github.pps5.sprint.domain.entity.goal.MonthlyGoal
import io.github.pps5.sprint.domain.entity.goal.WeeklyGoal
import io.github.pps5.sprint.usecase.UpdateGoalUseCase

internal class UpdateGoalInteractor(
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
            is MonthlyGoal -> goalRepository.insertOrUpdateMonthlyGoal(goal)
            is WeeklyGoal -> goalRepository.insertOrUpdateWeeklyGoal(goal)
            is DailyGoal -> goalRepository.insertOrUpdateDailyGoal(goal)
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