package io.github.pps5.sprint.data.internal.entity.goal

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import io.github.pps5.sprint.domain.entity.goal.WeeklyGoal
import org.threeten.bp.LocalDate

@Entity(indices = [Index(value = ["startDate"])])
internal data class WeeklyGoalEntity(
    @PrimaryKey var id: Long,
    var startDate: LocalDate,
    var title: String,
    var completedDate: LocalDate?
) {

    companion object {
        fun from(domain: WeeklyGoal): WeeklyGoalEntity {
            return WeeklyGoalEntity(
                id = domain.week.start.toEpochDay(),
                startDate = domain.week.start,
                title = domain.title.get().value,
                completedDate = domain.completedDate.getOrNull(),
            )
        }
    }
}
