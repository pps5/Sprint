package io.github.pps5.sprint.usecase

import io.github.pps5.sprint.domain.entity.goal.Goal

interface UpdateGoalUseCase {
    suspend operator fun invoke(goal: Goal)
}
