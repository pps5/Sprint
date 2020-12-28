package io.github.pps5.sprint.data.internal.entity.goal

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import io.github.pps5.sprint.domain.entity.goal.DailyGoal
import org.threeten.bp.LocalDate

@Entity(indices = [Index(value = ["date"])])
internal data class DailyGoalEntity(
    @PrimaryKey var id: Long,
    var date: LocalDate,
    var title: String,
    var completedDate: LocalDate?
) {
    companion object {
        fun from(domain: DailyGoal): DailyGoalEntity {
            return DailyGoalEntity(
                id = domain.date.toEpochDay(),
                date = domain.date,
                title = domain.title.get().value,
                completedDate = domain.completedDate.getOrNull(),
            )
        }
    }
}