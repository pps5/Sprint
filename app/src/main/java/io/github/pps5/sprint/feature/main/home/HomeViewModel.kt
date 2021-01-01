package io.github.pps5.sprint.feature.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.pps5.sprint.common.DateProvider
import io.github.pps5.sprint.domain.entity.goal.DailyGoal
import io.github.pps5.sprint.domain.entity.goal.Goal
import io.github.pps5.sprint.domain.entity.goal.MonthlyGoal
import io.github.pps5.sprint.domain.entity.goal.WeeklyGoal
import io.github.pps5.sprint.domain.valueobject.GoalTitle
import io.github.pps5.sprint.domain.valueobject.Option
import io.github.pps5.sprint.usecase.GetSprintGoalsUseCase
import io.github.pps5.sprint.usecase.UpdateGoalUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel(
    private val dateProvider: DateProvider,
    private val getSprintGoalsUseCase: GetSprintGoalsUseCase,
    private val updateGoalUseCase: UpdateGoalUseCase,
) : ViewModel() {

    private val _state = MutableSharedFlow<State>(replay = 1)
    val state: SharedFlow<State>
        get() = _state

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getSprintGoalsUseCase(dateProvider.now())
                .collect {
                    val newState = State(
                        it.dailyGoals,
                        it.weeklyGoal,
                        it.monthlyGoal,
                    )
                    _state.emit(newState)
                }
        }
    }

    fun onToggleComplete(goal: Goal) {
        goal.toggleComplete(dateProvider.now())
        viewModelScope.launch(Dispatchers.IO) {
            updateGoalUseCase(goal)
        }
    }

    fun onGoalTitleUpdated(goal: Goal, newTitle: String) {
        goal.title = Option.apply(GoalTitle.of(newTitle))
        viewModelScope.launch(Dispatchers.IO) {
            updateGoalUseCase(goal)
        }
    }

    data class State(
        val dailyGoals: List<DailyGoal>,
        val weeklyGoal: WeeklyGoal,
        val monthlyGoal: MonthlyGoal,
    )
}