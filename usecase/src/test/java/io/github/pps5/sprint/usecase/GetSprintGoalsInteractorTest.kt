package io.github.pps5.sprint.usecase

import com.google.common.truth.Truth.assertThat
import io.github.pps5.sprint.data.repository.GoalRepository
import io.github.pps5.sprint.domain.entity.goal.DailyGoal
import io.github.pps5.sprint.domain.entity.goal.MonthlyGoal
import io.github.pps5.sprint.domain.entity.goal.WeeklyGoal
import io.github.pps5.sprint.domain.valueobject.GoalTitle
import io.github.pps5.sprint.domain.valueobject.Option
import io.github.pps5.sprint.usecase.internal.interactor.GetSprintGoalsInteractor
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.threeten.bp.LocalDate

internal class GetSprintGoalsInteractorTest {

    lateinit var goalRepository: GoalRepository
    lateinit var target: GetSprintGoalsInteractor

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun dailyGoalsFewerThan7() = listOf(
            Arguments.of(emptyList<DailyGoal>()),
            Arguments.of(
                listOf(
                    DailyGoal(
                        LocalDate.of(2020, 1, 1),
                        Option.Some(GoalTitle.of("test")!!),
                        Option.None()
                    )
                )
            ),
        )
    }

    @BeforeEach
    fun setUp() {
        goalRepository = mockk()
        target = GetSprintGoalsInteractor(goalRepository)
    }

    private fun setUpResponses(
        dailyGoal: List<DailyGoal> = emptyList(),
        weeklyGoal: List<WeeklyGoal> = emptyList(),
        monthlyGoal: List<MonthlyGoal> = emptyList(),
    ) {
        every { goalRepository.getDailyGoals(any()) }.returns(flow { emit(dailyGoal) })
        every { goalRepository.getWeeklyGoal(any()) }.returns(flow { emit(weeklyGoal) })
        every { goalRepository.getMonthlyGoal(any()) }.returns(flow { emit(monthlyGoal) })
    }

    @ParameterizedTest
    @MethodSource("dailyGoalsFewerThan7")
    fun `response contains 7 daily goal items when data is fewer than 7`(
        goals: List<DailyGoal>
    ) = runBlocking {
        setUpResponses(dailyGoal = goals)

        val result = target.invoke(LocalDate.now()).first()
        assertThat(result.dailyGoals).hasSize(7)
        coVerify(exactly = 1) {
            goalRepository.getDailyGoals(coMatch { it.size == 7 })
        }
    }
}