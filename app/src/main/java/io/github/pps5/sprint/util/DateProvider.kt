package io.github.pps5.sprint.util

import org.threeten.bp.LocalDate

interface DateProvider {
    fun now(): LocalDate
    fun of(year: Int, month: Int, dayOfMonth: Int): LocalDate
}

class ThreeTenABPDateProvider : DateProvider {

    override fun now(): LocalDate {
        return LocalDate.now()
    }

    override fun of(year: Int, month: Int, dayOfMonth: Int): LocalDate {
        return LocalDate.of(year, month, dayOfMonth)
    }
}