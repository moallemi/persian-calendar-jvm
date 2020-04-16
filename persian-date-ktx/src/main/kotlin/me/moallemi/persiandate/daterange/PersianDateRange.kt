package me.moallemi.persiandate.daterange

import me.moallemi.persiandate.PersianDate

class PersianDateRange(
    override val start: PersianDate,
    override val endInclusive: PersianDate,
    private val stepDays: Long = 1L
) : Iterable<PersianDate>, ClosedRange<PersianDate> {

    override fun iterator(): Iterator<PersianDate> = PersianDateIterator(start, endInclusive, stepDays)

    infix fun step(days: Long) = PersianDateRange(start, endInclusive, days)
}
