package io.github.pps5.sprint.data.internal.repository

import io.github.pps5.sprint.data.internal.entity.goal.DailyGoalEntity
import io.github.pps5.sprint.data.internal.entity.goal.MonthlyGoalEntity
import io.github.pps5.sprint.data.internal.entity.goal.WeeklyGoalEntity
import io.github.pps5.sprint.data.internal.mapper.toDomain
import io.github.pps5.sprint.data.internal.store.db.AppDatabase
import io.github.pps5.sprint.data.repository.GoalRepository
import io.github.pps5.sprint.domain.entity.goal.DailyGoal
import io.github.pps5.sprint.domain.entity.goal.MonthlyGoal
import io.github.pps5.sprint.domain.entity.goal.WeeklyGoal
import io.github.pps5.sprint.domain.valueobject.Week
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.threeten.bp.LocalDate
import org.threeten.bp.YearMonth

internal class GoalRepositoryImpl(
    private val appDatabase: AppDatabase,
) : GoalRepository {

    override fun getMonthlyGoal(yearMonths: List<YearMonth>): Flow<List<MonthlyGoal>> {
        val startDays = yearMonths.map { it.atDay(1) }
        return appDatabase.goalDao()
            .getMonthlyGoalsDistinctUntilChanged(startDays)
            .map(List<MonthlyGoalEntity>::toDomain)
    }

    override fun getWeeklyGoal(weeks: List<Week>): Flow<List<WeeklyGoal>> {
        val startDays = weeks.map(Week::start)
        return appDatabase.goalDao()
            .getWeeklyGoalsDistinctUntilChanged(startDays)
            .map(List<WeeklyGoalEntity>::toDomain)
    }

    override fun getDailyGoals(days: List<LocalDate>): Flow<List<DailyGoal>> {
        return appDatabase.goalDao()
            .getDailyGoalsDistinctUntilChanged(days)
            .map(List<DailyGoalEntity>::toDomain)
    }

    override suspend fun updateOrInsertMonthlyGoal(goal: MonthlyGoal) {
        appDatabase.goalDao().upsertMonthlyGoal(MonthlyGoalEntity.from(goal))
    }

    override suspend fun updateOrInsertWeeklyGoal(goal: WeeklyGoal) {
        appDatabase.goalDao().upsertWeeklyGoal(WeeklyGoalEntity.from(goal))
    }

    override suspend fun updateOrInsertDailyGoal(goal: DailyGoal) {
        appDatabase.goalDao().upsertDailyGoal(DailyGoalEntity.from(goal))
    }

    override suspend fun deleteMonthlyGoal(goal: MonthlyGoal) {
        appDatabase.goalDao().deleteDailyGoalByDate(goal.yearMonth.atDay(1))
    }

    override suspend fun deleteWeeklyGoal(goal: WeeklyGoal) {
        appDatabase.goalDao().deleteWeeklyGoalByStartDate(goal.week.start)
    }

    override suspend fun deleteDailyGoal(goal: DailyGoal) {
        appDatabase.goalDao().deleteDailyGoalByDate(goal.date)
    }
}