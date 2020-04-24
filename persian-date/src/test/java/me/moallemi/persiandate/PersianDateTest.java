package me.moallemi.persiandate;

import org.junit.Test;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

public class PersianDateTest {

    @Test
    public void testOnStaticFactoryMethodNow() {
        assertEquals(PersianDate.ofDate(new Date()), PersianDate.now());
    }

    @Test
    public void testOnStaticFactoryMethod1() {
        PersianDate pd = PersianDate.of(1400, 2, 17);
        assertEquals(1400, pd.getYear());
        assertEquals(2, pd.getMonthValue());
        assertEquals(PersianMonth.ORDIBEHESHT, pd.getMonth());
        assertEquals(17, pd.getDayOfMonth());
    }

    @Test
    public void testOnStaticFactoryMethod2() {
        PersianDate pd = PersianDate.of(1623, PersianMonth.AZAR, 10);
        assertEquals(1623, pd.getYear());
        assertEquals(9, pd.getMonthValue());
        assertEquals(PersianMonth.AZAR, pd.getMonth());
        assertEquals(10, pd.getDayOfMonth());
    }

    @Test(expected = PersianDateException.class)
    public void testOnPersianDateOfInvalidDateNotLeapYear() {
        PersianDate.of(1388, 12, 30);
    }

    @Test(expected = PersianDateException.class)
    public void testOnPersianDateOfInvalidDate1() {
        PersianDate.of(2000, 12, 2);
    }

    @Test(expected = PersianDateException.class)
    public void testOnPersianDatePlus() {
        PersianDate.of(1890, 6, 31).plusYears(120);
    }

    @Test(expected = PersianDateException.class)
    public void testOnPersianDateInvalidDate2() {
        PersianDate.of(1000, 4, 32);
    }

    @Test
    public void testOnGetDayOfYear() {
        PersianDate pd = PersianDate.of(1400, 8, 15);
        assertEquals(231, pd.getDayOfYear());
    }

    @Test
    public void testOnGetDayOfYearInLeapYear() {
        PersianDate pd = PersianDate.of(1387, 12, 30);
        assertEquals(366, pd.getDayOfYear());
    }

    @Test
    public void testOnToJulianDay() {
        assertEquals(2458054, PersianDate.of(1396, 8, 6).toJulianDay());
        assertEquals(2462580, PersianDate.of(1408, 12, 30).toJulianDay());
        assertEquals(1984844, PersianDate.of(101, 1, 1).toJulianDay());
    }

    @Test
    public void testOnOfJulianDay() {
        assertEquals(PersianDate.of(1393, 11, 13), PersianDate.ofJulianDays(2457055));
        assertEquals(PersianDate.of(1791, 6, 19), PersianDate.ofJulianDays(2602276));
        assertEquals(PersianDate.of(320, 5, 5), PersianDate.ofJulianDays(2064960));
        assertEquals(PersianDate.of(321, 12, 29), PersianDate.ofJulianDays(2065561));
        assertEquals(PersianDate.of(473, 1, 1), PersianDate.ofJulianDays(2120714));
        assertEquals(PersianDate.of(474, 1, 1), PersianDate.ofJulianDays(2121079));
        assertEquals(PersianDate.of(474, 12, 30), PersianDate.ofJulianDays(2121444));
        assertEquals(PersianDate.of(475, 1, 1), PersianDate.ofJulianDays(2121445));
        assertEquals(PersianDate.MIN, PersianDate.ofJulianDays(1948320));
        assertEquals(PersianDate.MAX, PersianDate.ofJulianDays(2678438));
    }

    @Test
    public void testOnPlusYears() {
        PersianDate actual = PersianDate.of(1400, 1, 1).plusYears(1);
        PersianDate expected = PersianDate.of(1401, 1, 1);
        assertEquals(expected, actual);
    }

    @Test
    public void testOnPlusYearsLeapYear() {
        PersianDate actual = PersianDate.of(1387, 12, 30).plusYears(1);
        PersianDate expected = PersianDate.of(1388, 12, 29);
        assertEquals(expected, actual);
    }

    @Test
    public void testOnPlusYearsLeapYear100Years() {
        PersianDate actual = PersianDate.of(1503, 12, 30).plusYears(100);
        PersianDate expected = PersianDate.of(1603, 12, 29);
        assertEquals(expected, actual);
    }

    @Test
    public void testOnPlusYearsNegativeLeapYear104Years() {
        PersianDate actual = PersianDate.of(1503, 12, 30).plusYears(-104);
        PersianDate expected = PersianDate.of(1399, 12, 30);
        assertEquals(expected, actual);
    }

    @Test
    public void testOnPlusMonthsZero() {
        assertEquals(PersianDate.MIN, PersianDate.MIN.plusMonths(0));
    }

    @Test
    public void testOnPlusMonths() {
        PersianDate actual = PersianDate.of(1388, 1, 1).plusMonths(1);
        PersianDate expected = PersianDate.of(1388, 2, 1);
        assertEquals(expected, actual);
    }

    @Test
    public void testOnPlusMonthsMoreThan12Months() {
        PersianDate actual = PersianDate.of(1353, 10, 25).plusMonths(16);
        PersianDate expected = PersianDate.of(1355, 2, 25);
        assertEquals(expected, actual);
    }

    @Test
    public void testOnPlusMonthShahrivarToMehr() {
        PersianDate actual = PersianDate.of(1400, 6, 31).plusMonths(1);
        PersianDate expected = PersianDate.of(1400, 7, 30);
        assertEquals(expected, actual);
    }

    @Test
    public void testOnPlusMonthEndOfMonthLeapYear() {
        PersianDate actual = PersianDate.of(1387, 3, 30).plusMonths(9);
        PersianDate expected = PersianDate.of(1387, 12, 30);
        assertEquals(expected, actual);
    }

    @Test
    public void testOnPlusMonthEndOfMonthNotLeapYear() {
        PersianDate actual = PersianDate.of(1396, 4, 30).plusMonths(8);
        PersianDate expected = PersianDate.of(1396, 12, 29);
        assertEquals(expected, actual);
    }

    @Test
    public void testOnPlusMonthNegative() {
        PersianDate actual = PersianDate.of(1396, 6, 31).plusMonths(-8);
        PersianDate expected = PersianDate.of(1395, 10, 30);
        assertEquals(expected, actual);
    }

    @Test
    public void testOnPlusMonthNegativeMoreThan12Months() {
        PersianDate actual = PersianDate.of(1396, 7, 30).plusMonths(-30);
        PersianDate expected = PersianDate.of(1394, 1, 30);
        assertEquals(expected, actual);
    }

    @Test
    public void testOnPlusMonthNegativeEndOfYearLeapYear() {
        PersianDate actual = PersianDate.of(1388, 1, 31).plusMonths(-1);
        PersianDate expected = PersianDate.of(1387, 12, 30);
        assertEquals(expected, actual);
    }

    @Test
    public void testOnPlusMonthNegativeEndOfYearNotLeapYear() {
        PersianDate actual = PersianDate.of(1389, 1, 31).plusMonths(-1);
        PersianDate expected = PersianDate.of(1388, 12, 29);
        assertEquals(expected, actual);
    }

    @Test
    public void testOnPlusDaysZero() {
        assertEquals(PersianDate.MIN, PersianDate.MIN.plusDays(0));
    }

    @Test
    public void testOnPlusDays() {
        PersianDate actual = PersianDate.of(1450, 6, 31).plusDays(1);
        PersianDate expected = PersianDate.of(1450, 7, 1);
        assertEquals(expected, actual);
    }

    @Test
    public void testOnPlusDaysMoreThan365Days() {
        PersianDate actual = PersianDate.of(1396, 8, 6).plusDays(24435);
        PersianDate expected = PersianDate.of(1463, 6, 31);
        assertEquals(expected, actual);
    }

    @Test
    public void testOnPlusDaysEndOfYearLeapYear() {
        PersianDate actual = PersianDate.of(1387, 12, 29).plusDays(1);
        PersianDate expected = PersianDate.of(1387, 12, 30);
        assertEquals(expected, actual);
    }

    @Test
    public void testOnPlusDaysEndOfNotYearLeapYear() {
        PersianDate actual = PersianDate.of(1388, 12, 29).plusDays(1);
        PersianDate expected = PersianDate.of(1389, 1, 1);
        assertEquals(expected, actual);
    }

    @Test
    public void testOnPlusDaysNegativeFirstOfYearLeapYear() {
        PersianDate actual = PersianDate.of(1392, 1, 1).plusDays(-1);
        PersianDate expected = PersianDate.of(1391, 12, 30);
        assertEquals(expected, actual);
    }

    @Test
    public void testOnPlusDaysNegativeFirstOfYearNotLeapYear() {
        PersianDate actual = PersianDate.of(1393, 1, 1).plusDays(-1);
        PersianDate expected = PersianDate.of(1392, 12, 29);
        assertEquals(expected, actual);
    }

    @Test
    public void testOnPlusDaysNegativeMoreThan365Days() {
        PersianDate actual = PersianDate.of(1396, 8, 6).plusDays(-35688);
        PersianDate expected = PersianDate.of(1298, 11, 22);
        assertEquals(expected, actual);
    }

    @Test
    public void testOnIsLeapYear() {
        PersianDate pd = PersianDate.of(1375, 6, 15);
        // 7 successive leap years
        for (int i = 0; i < 7; i++) {
            assertTrue(pd.isLeapYear());
            pd = pd.plusYears(4);
        }
        // each 33 years, leap year happens after 5 years
        assertTrue(pd.plusYears(1).isLeapYear());
    }

    @Test
    public void testOnToEpochDay() {
        assertEquals(17468, PersianDate.of(1396, 8, 7).toEpochDay());
        assertEquals(-66869, PersianDate.of(1165, 9, 11).toEpochDay());
        assertEquals(-492267, PersianDate.of(1, 1, 1).toEpochDay());
        assertEquals(237851, PersianDate.of(1999, 12, 29).toEpochDay());
    }

    @Test
    public void testOnGetLengthOfMonth() {
        // 1387 is a leap year
        assertEquals(31, PersianDate.of(1387, 1, 1).lengthOfMonth());
        assertEquals(30, PersianDate.of(1387, 12, 1).lengthOfMonth());

        // 1388 is a normal year
        assertEquals(31, PersianDate.of(1388, 1, 1).lengthOfMonth());
        assertEquals(29, PersianDate.of(1388, 12, 1).lengthOfMonth());
    }

    @Test
    public void testOnToGregorian() {
        PersianDate pdt = PersianDate.of(1396, 6, 20);
        LocalDate expected = LocalDate.of(2017, 9, 11);
        assertEquals(expected, pdt.toLocalDate());
    }

    @Test
    public void testOnToGregorianMinPersianDate() {
        LocalDate expected = LocalDate.of(622, 3, 22);
        assertEquals(expected, PersianDate.MIN.toLocalDate());
    }

    @Test
    public void testOnToGregorianMaxPersianDate() {
        LocalDate expected = LocalDate.of(2621, 3, 20);
        assertEquals(expected, PersianDate.MAX.toLocalDate());
    }

    @Test
    public void testOnToGregorianInPersianLeapYear() {
        PersianDate pdt = PersianDate.of(1399, 12, 30);
        LocalDate expected = LocalDate.of(2021, 3, 20);
        assertEquals(expected, pdt.toLocalDate());
    }

    @Test
    public void testOnToGregorianInGregorianLeapYear() {
        PersianDate pdt = PersianDate.of(1394, 12, 10);
        LocalDate expected = LocalDate.of(2016, 2, 29);
        assertEquals(expected, pdt.toLocalDate());
    }

    @Test
    public void testOnToGregorianOnFirstOfPersianYear() {
        PersianDate pdt = PersianDate.of(1407, 1, 1);
        LocalDate expected = LocalDate.of(2028, 3, 20);
        assertEquals(expected, pdt.toLocalDate());
    }

    @Test
    public void testOnToGregorianOnEndOfPersianYear() {
        PersianDate pdt = PersianDate.of(1376, 12, 29);
        LocalDate expected = LocalDate.of(1998, 3, 20);
        assertEquals(expected, pdt.toLocalDate());
    }

    @Test
    public void testOnToGregorianOnFirstOfGregorianYear() {
        PersianDate pdt = PersianDate.of(1385, 10, 11);
        LocalDate expected = LocalDate.of(2007, 1, 1);
        assertEquals(expected, pdt.toLocalDate());
    }

    @Test
    public void testOnToGregorianOnEndOfGregorianYear() {
        PersianDate pdt = PersianDate.of(1429, 10, 10);
        LocalDate expected = LocalDate.of(2050, 12, 31);
        assertEquals(expected, pdt.toLocalDate());
    }

    @Test
    public void testOnToPersian() {
        LocalDate ld = LocalDate.of(2046, 5, 10);
        PersianDate expected = PersianDate.of(1425, 2, 20);
        assertEquals(expected, PersianDate.ofLocalDate(ld));
    }

    @Test
    public void testOnToPersianInGregorianLeapYear() {
        LocalDate ldt = LocalDate.of(2012, 2, 29);
        PersianDate expected = PersianDate.of(1390, 12, 10);
        assertEquals(expected, PersianDate.ofLocalDate(ldt));
    }

    @Test
    public void testOnToPersianInPersianLeapYear() {
        LocalDate ldt = LocalDate.of(2034, 3, 20);
        PersianDate expected = PersianDate.of(1412, 12, 30);
        assertEquals(expected, PersianDate.ofLocalDate(ldt));
    }

    @Test
    public void testOnToPersianOnFirstOfGregorianYear() {
        LocalDate ldt = LocalDate.of(2008, 1, 1);
        PersianDate expected = PersianDate.of(1386, 10, 11);
        assertEquals(expected, PersianDate.ofLocalDate(ldt));
    }

    @Test
    public void testOnToPersianOnEndOfGregorianYear() {
        LocalDate ldt = LocalDate.of(2003, 3, 1);
        PersianDate expected = PersianDate.of(1381, 12, 10);
        assertEquals(expected, PersianDate.ofLocalDate(ldt));
    }

    @Test
    public void testOnToPersianOnFirstOfPersianYear() {
        LocalDate ldt = LocalDate.of(1986, 3, 21);
        PersianDate expected = PersianDate.of(1365, 1, 1);
        assertEquals(expected, PersianDate.ofLocalDate(ldt));
    }

    // region ofDate

    @Test
    public void testOfDateOnToGregorian() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2017, 8, 11);
        assertEquals(PersianDate.ofDate(calendar.getTime()), PersianDate.of(1396, 6, 20));
    }

    @Test
    public void testOfDateOnToGregorianMinPersianDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(622, 2, 22);
        assertEquals(PersianDate.ofDate(calendar.getTime()), PersianDate.MIN);
    }

    @Test
    public void testOfDateOnToGregorianMaxPersianDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2621, 2, 20);
        assertEquals(PersianDate.ofDate(calendar.getTime()), PersianDate.MAX);
    }

    @Test
    public void testOfDateOnToGregorianInPersianLeapYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2021, 2, 20);
        assertEquals(PersianDate.ofDate(calendar.getTime()), PersianDate.of(1399, 12, 30));
    }

    @Test
    public void testOfDateOnToGregorianInGregorianLeapYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2016, 1, 29);
        assertEquals(PersianDate.ofDate(calendar.getTime()), PersianDate.of(1394, 12, 10));
    }

    @Test
    public void testOfDateOnToGregorianOnFirstOfPersianYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2028, 2, 20);
        assertEquals(PersianDate.ofDate(calendar.getTime()), PersianDate.of(1407, 1, 1));
    }

    @Test
    public void testOfDateOnToGregorianOnEndOfPersianYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1998, 2, 20);
        assertEquals(PersianDate.ofDate(calendar.getTime()), PersianDate.of(1376, 12, 29));
    }

    @Test
    public void testOfDateOnToGregorianOnFirstOfGregorianYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2007, 0, 1);
        assertEquals(PersianDate.ofDate(calendar.getTime()), PersianDate.of(1385, 10, 11));
    }

    @Test
    public void testOfDateOnToGregorianOnEndOfGregorianYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2050, 11, 31);
        assertEquals(PersianDate.ofDate(calendar.getTime()), PersianDate.of(1429, 10, 10));
    }

    @Test
    public void testOfDateOnToPersian() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2046, 4, 10);
        assertEquals(PersianDate.ofDate(calendar.getTime()), PersianDate.of(1425, 2, 20));
    }

    @Test
    public void testOfDateOnToPersianInGregorianLeapYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2012, 1, 29);
        assertEquals(PersianDate.ofDate(calendar.getTime()), PersianDate.of(1390, 12, 10));
    }

    @Test
    public void testOfDateOnToPersianInPersianLeapYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2034, 2, 20);
        assertEquals(PersianDate.ofDate(calendar.getTime()), PersianDate.of(1412, 12, 30));
    }

    @Test
    public void testOfDateOnToPersianOnFirstOfGregorianYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2008, 0, 1);
        assertEquals(PersianDate.ofDate(calendar.getTime()), PersianDate.of(1386, 10, 11));
    }

    @Test
    public void testOfDateOnToPersianOnEndOfGregorianYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2003, 2, 1);
        assertEquals(PersianDate.ofDate(calendar.getTime()), PersianDate.of(1381, 12, 10));
    }

    @Test
    public void testOfDateOnToPersianOnFirstOfPersianYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1986, 2, 21);
        assertEquals(PersianDate.ofDate(calendar.getTime()), PersianDate.of(1365, 1, 1));
    }

    //endregion ofDate

    @Test
    public void testOnEqualsNull() {
        PersianDate a = PersianDate.of(1396, 3, 15);
        assertNotEquals(null, a);
    }

    @Test
    public void testOnEqualsReflexivity() {
        PersianDate a = PersianDate.of(1396, 3, 15);
        assertEquals(a, a);
    }

    @Test
    public void testOnEqualsSymmetricity() {
        PersianDate a = PersianDate.of(1396, 3, 15);
        PersianDate b = PersianDate.of(1396, 3, 15);
        assertEquals(a, b);
        assertEquals(b, a);
    }

    @Test
    public void testOnEqualsTransitivity() {
        PersianDate a = PersianDate.of(1396, 3, 15);
        PersianDate b = PersianDate.of(1396, 3, 15);
        PersianDate c = PersianDate.of(1396, 3, 15);
        assertEquals(a, b);
        assertEquals(b, c);
        assertEquals(a, c);
    }

    @Test
    public void testOnHashCodeForEqualObjects() {
        PersianDate a = PersianDate.of(1500, 12, 15);
        PersianDate b = PersianDate.of(1500, 12, 15);
        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test
    public void testOnToString() {
        PersianDate PersianDate1 = PersianDate.of(1391, 11, 6);
        assertEquals("1391/11/06", PersianDate1.toString());
        PersianDate PersianDate2 = PersianDate.of(31, 1, 12);
        assertEquals("0031/01/12", PersianDate2.toString());
    }

    @Test
    public void testToDate() {
        assertEquals(PersianDate.now().toDate(), new Date());
    }

    @Test
    public void testToLocalDate() {
        assertEquals(PersianDate.of(1399, 2, 3).toLocalDate(), LocalDate.of(2020, 4, 22));
    }
}