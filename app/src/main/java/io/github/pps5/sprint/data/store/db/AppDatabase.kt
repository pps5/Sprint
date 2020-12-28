package io.github.pps5.sprint.data.store.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import io.github.pps5.sprint.data.entity.converter.LocalDateConverter
import io.github.pps5.sprint.data.entity.goal.DailyGoalEntity
import io.github.pps5.sprint.data.entity.goal.MonthlyGoalEntity
import io.github.pps5.sprint.data.entity.goal.WeeklyGoalEntity
import io.github.pps5.sprint.data.store.db.dao.GoalDao

@Database(
    entities = [
        DailyGoalEntity::class,
        WeeklyGoalEntity::class,
        MonthlyGoalEntity::class,
    ],
    version = 1,
)
@TypeConverters(value = [LocalDateConverter::class])
abstract class AppDatabase : RoomDatabase() {
    abstract fun goalDao(): GoalDao
}
