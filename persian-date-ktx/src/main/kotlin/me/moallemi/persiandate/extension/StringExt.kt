package me.moallemi.persiandate.extension

import me.moallemi.persiandate.PersianDate
import me.moallemi.persiandate.SimplePersianDateFormat
import java.util.*

fun String.toPersianDate(pattern: String, locale: Locale = Locale.getDefault()): PersianDate =
    SimplePersianDateFormat(pattern, locale).parse(this)