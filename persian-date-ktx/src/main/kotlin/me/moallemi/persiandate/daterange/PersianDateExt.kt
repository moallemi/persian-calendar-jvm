package me.moallemi.persiandate.daterange

import me.moallemi.persiandate.PersianDate

operator fun PersianDate.rangeTo(other: PersianDate) = PersianDateRange(this, other)
