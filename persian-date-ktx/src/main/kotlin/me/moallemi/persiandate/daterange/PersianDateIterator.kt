package me.moallemi.persiandate.daterange

import me.moallemi.persiandate.PersianDate

class PersianDateIterator(
    startDate: PersianDate,
    private val endDate: PersianDate,
    private val stepDays: Long
) : Iterator<PersianDate> {

    private var currentDate = startDate

    override fun hasNext() = currentDate <= endDate

    override fun next(): PersianDate {
        val next = currentDate

        currentDate = currentDate.plusDays(stepDays)

        return next
    }
}
