package io.github.pps5.sprint.data.internal.entity.converter

import androidx.room.TypeConverter
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter

internal class LocalDateConverter {

    private val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    @TypeConverter
    fun fromDateString(dateString: String?): LocalDate? {
        dateString ?: return null
        return LocalDate.parse(dateString, dateFormatter)
    }

    @TypeConverter
    fun toDateString(date: LocalDate?): String? {
        return date?.format(dateFormatter)
    }

}