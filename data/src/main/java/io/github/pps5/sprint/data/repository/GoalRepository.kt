package io.github.pps5.sprint.data.repository

import io.github.pps5.sprint.domain.entity.goal.DailyGoal
import io.github.pps5.sprint.domain.entity.goal.MonthlyGoal
import io.github.pps5.sprint.domain.entity.goal.WeeklyGoal
import io.github.pps5.sprint.domain.valueobject.Week
import kotlinx.coroutines.flow.Flow
import org.threeten.bp.LocalDate
import org.threeten.bp.YearMonth

interface GoalRepository {
    fun getMonthlyGoal(yearMonths: List<YearMonth>): Flow<List<MonthlyGoal>>
    fun getWeeklyGoal(weeks: List<Week>): Flow<List<WeeklyGoal>>
    fun getDailyGoals(days: List<LocalDate>): Flow<List<DailyGoal>>

    suspend fun updateOrInsertMonthlyGoal(goal: MonthlyGoal)
    suspend fun updateOrInsertWeeklyGoal(goal: WeeklyGoal)
    suspend fun updateOrInsertDailyGoal(goal: DailyGoal)

    suspend fun deleteMonthlyGoal(goal: MonthlyGoal)
    suspend fun deleteWeeklyGoal(goal: WeeklyGoal)
    suspend fun deleteDailyGoal(goal: DailyGoal)
}

