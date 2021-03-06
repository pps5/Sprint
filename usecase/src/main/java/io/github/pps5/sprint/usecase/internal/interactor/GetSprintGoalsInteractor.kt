package io.github.pps5.sprint.usecase.internal.interactor

import io.github.pps5.sprint.data.repository.GoalRepository
import io.github.pps5.sprint.domain.entity.goal.DailyGoal
import io.github.pps5.sprint.domain.entity.goal.MonthlyGoal
import io.github.pps5.sprint.domain.entity.goal.WeeklyGoal
import io.github.pps5.sprint.domain.valueobject.Option
import io.github.pps5.sprint.domain.valueobject.Week
import io.github.pps5.sprint.usecase.GetSprintGoalsUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import org.threeten.bp.DayOfWeek
import org.threeten.bp.LocalDate
import org.threeten.bp.YearMonth

internal class GetSprintGoalsInteractor(
    private val goalRepository: GoalRepository,
) : GetSprintGoalsUseCase {

    override suspend fun invoke(day: LocalDate): Flow<GetSprintGoalsUseCase.Response> {
        val yearMonth = YearMonth.from(day)
        val week = Week(day.with(DayOfWeek.MONDAY))
        val days = (0 until 7).map { day.plusDays(it.toLong()) }

        return combine(
            goalRepository.getDailyGoals(days),
            goalRepository.getWeeklyGoal(listOf(week)),
            goalRepository.getMonthlyGoal(listOf(yearMonth)),
        ) { daily, weekly, monthly ->
            val w = weekly.firstOrNull() ?: WeeklyGoal(week, Option.None(), Option.None())
            val m = monthly.firstOrNull() ?: MonthlyGoal(yearMonth, Option.None(), Option.None())
            GetSprintGoalsUseCase.Response(fillMissingDays(days, daily), w, m)
        }
    }

    private fun fillMissingDays(
        days: List<LocalDate>,
        dailyGoals: List<DailyGoal>
    ): List<DailyGoal> {
        return days.map { day ->
            dailyGoals.firstOrNull { it.date == day }
                ?: DailyGoal(day, Option.None(), Option.None())
        }
    }

}