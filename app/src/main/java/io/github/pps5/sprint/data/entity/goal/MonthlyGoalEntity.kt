package io.github.pps5.sprint.data.entity.goal

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import io.github.pps5.sprint.domain.entity.goal.MonthlyGoal
import org.threeten.bp.LocalDate

@Entity(indices = [Index(value = ["startDate"])])
data class MonthlyGoalEntity(
    @PrimaryKey var id: Long,
    var startDate: LocalDate,
    var title: String,
    var completedDate: LocalDate?
) {
    companion object {
        fun from(domain: MonthlyGoal): MonthlyGoalEntity {
            val startDate = domain.yearMonth.atDay(1)
            return MonthlyGoalEntity(
                id = startDate.toEpochDay(),
                startDate = startDate,
                title = domain.title.get().value,
                completedDate = domain.completedDate.getOrNull(),
            )
        }
    }
}
