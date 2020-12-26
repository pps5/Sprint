package io.github.pps5.sprint.feature.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.pps5.sprint.domain.goal.DailyGoal
import io.github.pps5.sprint.domain.goal.MonthlyGoal
import io.github.pps5.sprint.domain.goal.WeeklyGoal
import io.github.pps5.sprint.domain.valueobject.Option
import io.github.pps5.sprint.domain.valueobject.Week
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import org.threeten.bp.LocalDate
import org.threeten.bp.YearMonth

class HomeViewModel : ViewModel() {

    private val _state = MutableSharedFlow<State>(replay = 1)
    val state: SharedFlow<State>
        get() = _state

    init {
        viewModelScope.launch {
            _state.emit(
                State(
                    dailyGoals = emptyList(),
                    weeklyGoal = WeeklyGoal(
                        Week(LocalDate.of(2020, 12, 28)),
                        Option.None(),
                        Option.None()
                    ),
                    monthlyGoal = MonthlyGoal(
                        YearMonth.of(2020, 12),
                        Option.None(),
                        Option.None()
                    )
                )
            )
            // todo: load from DB here
        }
    }

    data class State(
        val dailyGoals: List<DailyGoal>,
        val weeklyGoal: WeeklyGoal,
        val monthlyGoal: MonthlyGoal,
    )
}