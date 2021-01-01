package io.github.pps5.sprint.domain.valueobject

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.threeten.bp.DayOfWeek
import org.threeten.bp.LocalDate

class WeekTest {

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun nonMondayDates() = listOf(
            Arguments.of(LocalDate.of(2020, 12, 22)), // Tue
            Arguments.of(LocalDate.of(2020, 12, 23)), // Wed
            Arguments.of(LocalDate.of(2020, 12, 24)), // Thu
            Arguments.of(LocalDate.of(2020, 12, 25)), // Fri
            Arguments.of(LocalDate.of(2020, 12, 26)), // Sat
            Arguments.of(LocalDate.of(2020, 12, 27)), // Sun
        )
    }

    @Test
    fun `Week must ends with next Sunday`() {
        val week = Week(LocalDate.of(2020, 12, 28))
        assertThat(week.start).isEqualTo(LocalDate.of(2020, 12, 28))
        assertThat(week.end).isEqualTo(LocalDate.of(2021, 1, 3))
        assertThat(week.end.dayOfWeek).isEqualTo(DayOfWeek.SUNDAY)
    }

    @ParameterizedTest
    @MethodSource("nonMondayDates")
    fun `Week throws IllegalArgumentException when start date is not Monday`(date: LocalDate) {
        assertThrows<IllegalArgumentException> { Week(date) }
    }

}