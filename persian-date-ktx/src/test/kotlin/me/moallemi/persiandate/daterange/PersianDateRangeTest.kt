package me.moallemi.persiandate.daterange

import junit.framework.TestCase.assertEquals
import me.moallemi.persiandate.PersianDate
import org.junit.Test

class PersianDateRangeTest {

    @Test
    fun testRange() {
        val expected = listOf(
            "1398-12-28",
            "1398-12-29",
            "1399-01-01",
            "1399-01-02",
            "1399-01-03"
        )
        val startDate = PersianDate.of(1398, 12, 28)
        val endDate = PersianDate.of(1399, 1, 3)

        val actual = (startDate..endDate).iterator().asSequence().toList().map { it.toString() }

        assertEquals(expected, actual)
    }

    @Test
    fun testLeapYearRange() {
        val expected = listOf(
            "1399-12-28",
            "1399-12-29",
            "1399-12-30",
            "1400-01-01",
            "1400-01-02"
        )
        val startDate = PersianDate.of(1399, 12, 28)
        val endDate = PersianDate.of(1400, 1, 2)

        val actual = (startDate..endDate).iterator().asSequence().toList().map { it.toString() }

        assertEquals(expected, actual)
    }

    @Test
    fun testStep() {
        val expected = listOf(
            "1399-01-01",
            "1399-01-03",
            "1399-01-05"
        )
        val startDate = PersianDate.of(1399, 1, 1)
        val endDate = PersianDate.of(1399, 1, 5)

        val actual = (startDate..endDate step 2).iterator().asSequence().toList().map { it.toString() }

        assertEquals(expected, actual)
    }

    @Test
    fun testLeapYearStep() {
        val expected = listOf(
            "1399-12-28",
            "1399-12-30",
            "1400-01-02"
        )
        val startDate = PersianDate.of(1399, 12, 28)
        val endDate = PersianDate.of(1400, 1, 2)

        val actual = (startDate..endDate step 2).iterator().asSequence().toList().map { it.toString() }

        assertEquals(expected, actual)
    }

    @Test
    fun testContains() {
        val startDate = PersianDate.of(1399, 1, 1)
        val endDate = PersianDate.of(1399, 1, 5)

        val actual = PersianDate.of(1399, 1, 2) in (startDate..endDate)

        assertEquals(true, actual)
    }

    @Test
    fun testLeapYearContains() {
        val startDate = PersianDate.of(1398, 12, 28)
        val endDate = PersianDate.of(1399, 1, 5)

        val actual = PersianDate.of(1399, 12, 30) in (startDate..endDate)

        assertEquals(false, actual)
    }
}
