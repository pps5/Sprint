package io.github.pps5.sprint.data.store.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.github.pps5.sprint.data.entity.goal.DailyGoalEntity
import io.github.pps5.sprint.data.entity.goal.MonthlyGoalEntity
import io.github.pps5.sprint.data.entity.goal.WeeklyGoalEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import org.threeten.bp.LocalDate

@Dao
abstract class GoalDao {

    @Query("select * from dailygoalentity where date in (:dates)")
    protected abstract fun getDailyGoals(dates: List<LocalDate>): Flow<List<DailyGoalEntity>>

    fun getDailyGoalsDistinctUntilChanged(dates: List<LocalDate>): Flow<List<DailyGoalEntity>> {
        return getDailyGoals(dates).distinctUntilChanged()
    }

    @Query("select * from weeklygoalentity where startDate in (:startDates)")
    protected abstract fun getWeeklyGoals(startDates: List<LocalDate>): Flow<List<WeeklyGoalEntity>>

    fun getWeeklyGoalsDistinctUntilChanged(
        startDates: List<LocalDate>
    ): Flow<List<WeeklyGoalEntity>> {
        return getWeeklyGoals(startDates).distinctUntilChanged()
    }

    @Query("select * from monthlygoalentity where startDate in (:startDates)")
    protected abstract fun getMonthlyGoals(startDates: List<LocalDate>): Flow<List<MonthlyGoalEntity>>

    fun getMonthlyGoalsDistinctUntilChanged(
        startDates: List<LocalDate>
    ): Flow<List<MonthlyGoalEntity>> {
        return getMonthlyGoals(startDates).distinctUntilChanged()
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun upsertDailyGoal(dailyGoalEntity: DailyGoalEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun upsertWeeklyGoal(weeklyGoalEntity: WeeklyGoalEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun upsertMonthlyGoal(monthlyGoalEntity: MonthlyGoalEntity)

    @Query("delete from dailygoalentity where date = (:startDate)")
    abstract fun deleteDailyGoalByDate(startDate: LocalDate)

    @Query("delete from weeklygoalentity where startDate = (:startDate)")
    abstract fun deleteWeeklyGoalByStartDate(startDate: LocalDate)

    @Query("delete from monthlygoalentity where startDate = (:startDate)")
    abstract fun deleteMonthlyGoalByStartDate(startDate: LocalDate)
}