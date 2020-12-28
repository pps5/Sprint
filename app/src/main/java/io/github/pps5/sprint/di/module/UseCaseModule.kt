package io.github.pps5.sprint.di.module

import io.github.pps5.sprint.usecase.getSprintGoalsUseCaseModule
import io.github.pps5.sprint.usecase.updateGoalUseCaseModule

val useCaseModules = listOf(
    getSprintGoalsUseCaseModule,
    updateGoalUseCaseModule,
)