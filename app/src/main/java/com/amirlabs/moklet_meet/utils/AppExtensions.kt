package com.amirlabs.moklet_meet.utils

import java.text.SimpleDateFormat
import java.util.*

fun String?.formatDate(newFormat: String, isTimeZone: Boolean = true, dayname: Boolean = false):String?{
    val inputPattern = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    val inputPatternTimezone = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    val outputPattern = if (newFormat.isEmpty()) {
        "dd MMMM yyyy HH:mm:ss"
    } else {
        newFormat
    }

    val inputFormat = if (isTimeZone) inputPatternTimezone
    else inputPattern
    var calendarday = 0
    val outputFormat = SimpleDateFormat(outputPattern, Locale("id", "ID"))
    var date: Date? = null
    var str: String? = null
    try {
        date = inputFormat.parse(this)

        val calendar = Calendar.getInstance()
        calendar.time = date
        calendarday = calendar.get(Calendar.DAY_OF_WEEK)

        str = outputFormat.format(calendar.time)
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return if (dayname) getDayNameCap(calendarday) + ", " + str
    else str
}

fun getDayNameCap(day: Int): String? {
    when (day) {
        Calendar.SUNDAY -> return "Minggu"
        Calendar.MONDAY -> return "Senin"
        Calendar.TUESDAY -> return "Selasa"
        Calendar.WEDNESDAY -> return "Rabu"
        Calendar.THURSDAY -> return "Kamis"
        Calendar.FRIDAY -> return "Jumat"
        Calendar.SATURDAY -> return "Sabtu"
    }
    return "Wrong Day"
}