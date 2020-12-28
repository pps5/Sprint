package io.github.pps5.sprint.usecase

import io.github.pps5.sprint.data.repository.GoalRepository
import io.github.pps5.sprint.domain.entity.goal.DailyGoal
import io.github.pps5.sprint.domain.entity.goal.MonthlyGoal
import io.github.pps5.sprint.domain.entity.goal.WeeklyGoal
import io.github.pps5.sprint.domain.valueobject.GoalTitle
import io.github.pps5.sprint.domain.valueobject.Option
import io.github.pps5.sprint.domain.valueobject.Week
import io.mockk.clearMocks
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.threeten.bp.LocalDate
import org.threeten.bp.YearMonth

class UpdateGoalInteractorTest {

    lateinit var goalRepository: GoalRepository
    lateinit var target: UpdateGoalInteractor

    @BeforeEach
    fun setUp() {
        goalRepository = mockk(relaxUnitFun = true)
        target = UpdateGoalInteractor(goalRepository)
    }

    @Test
    fun `delete goal when title is None`() = runBlocking {
        val dailyGoal = DailyGoal(LocalDate.of(2020, 12, 28), Option.None(), Option.None())
        target.invoke(dailyGoal)
        coVerify(exactly = 1) { goalRepository.deleteDailyGoal(dailyGoal) }
        confirmVerified(goalRepository)
        clearMocks(goalRepository, recordedCalls = true)

        val weeklyGoal = WeeklyGoal(Week(LocalDate.of(2020, 12, 28)), Option.None(), Option.None())
        target.invoke(weeklyGoal)
        coVerify(exactly = 1) { goalRepository.deleteWeeklyGoal(weeklyGoal) }
        confirmVerified(goalRepository)
        clearMocks(goalRepository, recordedCalls = true)

        val monthlyGoal = MonthlyGoal(YearMonth.of(2020, 12), Option.None(), Option.None())
        target.invoke(monthlyGoal)
        coVerify(exactly = 1) { goalRepository.deleteMonthlyGoal(monthlyGoal) }
        confirmVerified(goalRepository)
    }

    @Test
    fun `insert or update when title is Some `() = runBlocking {
        val goalTitle = Option.apply(GoalTitle.of("title"))

        val dailyGoal = DailyGoal(LocalDate.of(2020, 12, 28), goalTitle, Option.None())
        target.invoke(dailyGoal)
        coVerify(exactly = 1) { goalRepository.updateOrInsertDailyGoal(dailyGoal) }
        confirmVerified(goalRepository)
        clearMocks(goalRepository, recordedCalls = true)

        val weeklyGoal = WeeklyGoal(Week(LocalDate.of(2020, 12, 28)), goalTitle, Option.None())
        target.invoke(weeklyGoal)
        coVerify(exactly = 1) { goalRepository.updateOrInsertWeeklyGoal(weeklyGoal) }
        confirmVerified(goalRepository)
        clearMocks(goalRepository, recordedCalls = true)

        val monthlyGoal = MonthlyGoal(YearMonth.of(2020, 12), goalTitle, Option.None())
        target.invoke(monthlyGoal)
        coVerify(exactly = 1) { goalRepository.updateOrInsertMonthlyGoal(monthlyGoal) }
        confirmVerified(goalRepository)
    }
}