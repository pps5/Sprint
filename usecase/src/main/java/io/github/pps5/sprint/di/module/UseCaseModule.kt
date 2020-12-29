package io.github.pps5.sprint.di.module

import io.github.pps5.sprint.usecase.GetSprintGoalsUseCase
import io.github.pps5.sprint.usecase.UpdateGoalUseCase
import io.github.pps5.sprint.usecase.internal.interactor.GetSprintGoalsInteractor
import io.github.pps5.sprint.usecase.internal.interactor.UpdateGoalInteractor
import org.koin.dsl.module

val useCaseModule = module {
    factory<GetSprintGoalsUseCase> { GetSprintGoalsInteractor(get()) }
    factory<UpdateGoalUseCase> { UpdateGoalInteractor(get()) }
}
