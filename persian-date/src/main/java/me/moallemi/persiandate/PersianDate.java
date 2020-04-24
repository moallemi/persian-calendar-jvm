package me.moallemi.persiandate;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 * This is an implementation of Solar Hijri calendar (also known as Jalali calendar, Persian calendar).
 */
public class PersianDate implements Comparable<PersianDate> {
    /**
     * The minimum supported persian date {@code 0001-01-01}.
     */
    public static final PersianDate MIN = PersianDate.of(1, 1, 1);

    /**
     * The maximum supported persian date {@code 1999-12-29}.
     */
    public static final PersianDate MAX = PersianDate.of(1999, 12, 29);

    /**
     * 1970-01-01 to julidan day.
     */
    private static final long JULIAN_DAY_TO_1970 = 2440587L;

    /**
     * The year.
     */
    private final int year;

    /**
     * The month-of-year.
     */
    private final int month;

    /**
     * The day-of-month.
     */
    private final int day;

    /**
     * @return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * @return the month-of-year field using the {@code Month} enum.
     * @see #getMonthValue()
     */
    public PersianMonth getMonth() {
        return PersianMonth.of(month);
    }

    /**
     * @return the month-of-year, from 1 to 12
     * @see #getMonth()
     */
    public int getMonthValue() {
        return month;
    }

    /**
     * @return day-of-month, from 1 to 31
     */
    public int getDayOfMonth() {
        return day;
    }

    /**
     * @return day-of-year, from 1 to 365 or 366 in a leap year
     */
    public int getDayOfYear() {
        return PersianMonth.of(month).daysToFirstOfMonth() + day;
    }

    /**
     * Obtains current Persian date from the system clock in the default time zone.
     *
     * @return current Persian date from the system clock in the default time zone
     */
    public static PersianDate now() {
        Calendar calendar = Calendar.getInstance();
        return ofJulianDays((long) PersianDateUtils.dateToJulian(calendar));
    }

    /**
     * Obtains an instance of {@code PersianDate} with year, month and day of month.
     * The value of month must be between {@code 1} and {@code 12}. Value {@code 1} would
     * be {@link PersianMonth#FARVARDIN} and value {@code 12} represents
     * {@link PersianMonth#ESFAND}.
     *
     * @param year       the year to represent, from 1 to MAX_YEAR
     * @param month      the value of month, from 1 to 12
     * @param dayOfMonth the dayOfMonth to represent, from 1 to 31
     * @return an instance of {@code PersianDate}
     * @throws PersianDateException if the passed parameters do not form a valid date or time.
     */
    public static PersianDate of(int year, int month, int dayOfMonth) {
        return new PersianDate(year, month, dayOfMonth);
    }

    /**
     * Obtains an instance of {@code PersianDate} with year, month and day of month.
     *
     * @param year       the year to represent, from 1 to MAX_YEAR
     * @param month      the month-of-year to represent, an instance of {@link PersianMonth}
     * @param dayOfMonth the dayOfMonth to represent, from 1 to 31
     * @return an instance of {@code PersianDate}
     * @throws PersianDateException if the passed parameters do not form a valid date or time.
     */
    public static PersianDate of(int year, PersianMonth month, int dayOfMonth) {
        Objects.requireNonNull(month, "month");
        return new PersianDate(year, month.getValue(), dayOfMonth);
    }

    /**
     * Constructor.
     *
     * @param year       the year to represent, from 1 to MAX_YEAR
     * @param month      the month-of-year to represent, not null, from {@link PersianMonth} enum
     * @param dayOfMonth the dayOfMonth-of-month to represent, from 1 to 31
     * @throws PersianDateException if the passed parameters do not form a valid date or time.
     */
    private PersianDate(int year, int month, int dayOfMonth) {
        PersianDateUtils.checkRange(year, month, dayOfMonth);
        boolean leapYear = isLeapYear(year);
        int maxDaysOfMonth = PersianMonth.of(month).length(leapYear);
        if (dayOfMonth > maxDaysOfMonth) {
            if (month == 12 && dayOfMonth == 30 && !leapYear) {
                throw new PersianDateException("Invalid date ESFAND 30, as " + year + " is not a leap year");
            }
            throw new PersianDateException("Invalid date " + PersianMonth.of(month).name() + " " + dayOfMonth);
        }
        this.year = year;
        this.month = month;
        this.day = dayOfMonth;
    }

    public static PersianDate ofDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return ofJulianDays((long) PersianDateUtils.dateToJulian(calendar));
    }

    public static PersianDate fromTimeInMillis(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return ofJulianDays((long) PersianDateUtils.dateToJulian(calendar));
    }

    public static PersianDate ofLocalDate(LocalDate localDate) {
        return ofEpochDay(localDate.toEpochDay());
    }

    /**
     * Returns an instance of {@link PersianDate}, based on number of epoch days,
     * which is from 1970-01-01. For example passing {@code 17468} as the parameter
     * results a persian date of 1396-08-07.
     *
     * @param epochDays epoch days
     * @return an instance of {@link PersianDate}
     */
    public static PersianDate ofEpochDay(long epochDays) {
        return ofJulianDays(epochDays + JULIAN_DAY_TO_1970);
    }

    public static PersianDate ofJulianDays(long julianDays) {
        PersianDateUtils.longRequirePositive(julianDays, "julianDays");
        long depoch = julianDays - 2121445L;
        long cycle = depoch / 1029983L;
        long cyear = depoch % 1029983L;
        long ycycle, aux1, aux2;
        if (cyear == 1029982L) {
            ycycle = 2820L;
        } else {
            aux1 = cyear / 366L;
            aux2 = cyear % 366L;
            ycycle = (((2134L * aux1) + (2816L * aux2) + 2815L) / (1028522L)) + aux1;
            ycycle = (ycycle >= 0) ? ycycle + 1L : ycycle;
        }
        // Check year '474'
        ycycle = !PersianDateUtils.isBetween(julianDays, 2121079, 2121444) ? ycycle : 0;
        long pYear = ycycle + (2820L * cycle) + 474L;
        int yday = (int) (julianDays - PersianDate.of((int) pYear, 1, 1).toJulianDay() + 1);
        int pMonth = (int) Math.ceil((yday <= 186) ? yday / 31.0 : (yday - 6) / 30.0);
        int pDay = (int) (julianDays - PersianDate.of((int) pYear, pMonth, 1).toJulianDay() + 1);
        return PersianDate.of((int) pYear, pMonth, pDay);
    }

    public long toJulianDay() {
        return toJulianDay(year, month, day);
    }

    /**
     * Returns number of corresponding julian days. For number of juliand days of
     * PersianDate.of(1396, 8, 6) is 2458054. This method is provided in order to
     * prevent creating unnecessary instances of {@code PersianDate} only to calculate
     * julian day.
     *
     * @return number of corresponding julian days
     * @see <a href="http://www.fourmilab.ch/documents/calendar/">calendar convertor</a>
     */
    private long toJulianDay(int year, int month, int dayOfMonth) {
        int epbase = year - 474;
        int epyear = 474 + (epbase % 2820);
        return dayOfMonth + PersianMonth.of(month).daysToFirstOfMonth() +
                (epyear * 682 - 110) / 2816 +
                (epyear - 1) * 365 +
                (epbase / 2820 * 1029983) +
                (1948320 - 1);
    }

    /**
     * Returns a copy of this {@code PersianDate} with the specified number of days added.
     * <p>
     * This method adds the specified amount to the days field incrementing the
     * month and year fields as necessary to ensure the result remains valid.
     * The result is only invalid if the maximum/minimum year is exceeded.
     * <p>
     * For example, 1396-12-29 plus one day would result in 1397-01-01.
     * <p>
     * This instance is immutable and unaffected by this method call.
     *
     * @param daysToAdd the days to add, may be negative
     * @return a {@code PersianDate} based on this date with the days added, not null
     * @throws PersianDateException if the result exceeds the supported date range
     */
    public PersianDate plusDays(long daysToAdd) {
        if (daysToAdd == 0) {
            return this;
        }
        return ofJulianDays(toJulianDay() + daysToAdd);
    }

    /**
     * Returns a copy of this {@code PersianDate} with the specified period in years added.
     * <p>
     * This method adds the specified amount to the years field in three steps:
     * <ol>
     * <li>Add the input years to the year field</li>
     * <li>Check if the resulting date would be invalid</li>
     * <li>Adjust the day-of-month to the last valid day if necessary</li>
     * </ol>
     * <p>
     * For example, 1387-12-30 (leap year) plus one year would result in the
     * invalid date 1388-12-30 (standard year). Instead of returning an invalid
     * result, the last valid day of the month, 1388-12-29, is selected instead.
     * <p>
     * This instance is immutable and unaffected by this method call.
     *
     * @param yearsToAdd the years to add, may be negative
     * @return a {@code PersianDate} based on this date with the years added, not null
     * @throws PersianDateException if the result exceeds the supported date range
     */
    public PersianDate plusYears(long yearsToAdd) {
        PersianDate newPersianDate = plusMonths(yearsToAdd * 12);
        PersianDateUtils.checkRange(newPersianDate.year, newPersianDate.month, newPersianDate.day);
        return newPersianDate;
    }

    /**
     * Returns a copy of this {@code PersianDate} with the specified period in months added.
     * <p>
     * This method adds the specified amount to the months field in three steps:
     * <ol>
     * <li>Add the input months to the month-of-year field</li>
     * <li>Check if the resulting date would be invalid</li>
     * <li>Adjust the day-of-month to the last valid day if necessary</li>
     * </ol>
     * <p>
     * For example, 1388-11-30 plus one month would result in the invalid date
     * 1388-12-30. Instead of returning an invalid result, the last valid day
     * of the month, 1388-12-29, is selected instead.
     * <p>
     * This instance is immutable and unaffected by this method call.
     *
     * @param monthsToAdd the months to add, may be negative
     * @return a {@code PersianDate} based on this date with the months added, not null
     * @throws PersianDateException if the result exceeds the supported date range
     */
    public PersianDate plusMonths(long monthsToAdd) {
        if (monthsToAdd == 0) {
            return this;
        }
        long monthCount = year * 12L + (month - 1);
        long calcMonths = monthCount + monthsToAdd;
        int newYear = (int) Math.floorDiv(calcMonths, 12L);
        int newMonth = (int) Math.floorMod(calcMonths, 12L) + 1;
        return resolvePreviousValid(newYear, newMonth, day);
    }

    /**
     * Resolves the date, resolving days past the end of month.
     *
     * @param year  the year to represent
     * @param month the month-of-year to represent, validated from 1 to 12
     * @param day   the day-of-month to represent, validated from 1 to 31
     * @return the resolved date, not null
     */
    private PersianDate resolvePreviousValid(int year, int month, int day) {
        boolean leapYear = isLeapYear(year);
        int maxDaysOfMonth = PersianMonth.of(month).length(leapYear);
        if (day > maxDaysOfMonth) {
            day = maxDaysOfMonth;
        }
        return PersianDate.of(year, month, day);
    }

    public long toEpochDay() {
        return toJulianDay() - JULIAN_DAY_TO_1970;
    }

    public Date toDate() {
        Calendar currentCalendar = Calendar.getInstance();
        Calendar convertedCalendar = Calendar.getInstance();
        convertedCalendar.setTime(new Date(toEpochDay() * 86400000L));
        convertedCalendar.set(Calendar.HOUR_OF_DAY, currentCalendar.get(Calendar.HOUR_OF_DAY));
        convertedCalendar.set(Calendar.MINUTE, currentCalendar.get(Calendar.MINUTE));
        convertedCalendar.set(Calendar.SECOND, currentCalendar.get(Calendar.SECOND));
        convertedCalendar.set(Calendar.MILLISECOND, currentCalendar.get(Calendar.MILLISECOND));
        return convertedCalendar.getTime();
    }

    public boolean isLeapYear() {
        return isLeapYear(year);
    }

    public static boolean isLeapYear(int year) {
        return PersianDateUtils.ceil((38D + (PersianDateUtils.ceil(year - 474L, 2820L) + 474L)) * 682D, 2816D) < 682L;
    }

    int lengthOfMonth() {
        PersianMonth pm = PersianMonth.of(month);
        return isLeapYear() ? pm.maxLength() : pm.minLength();
    }

    /**
     * Returns an equivalent Date and time as an instance of {@link Date}.
     * Calling this method has no effect on the object that calls this.
     *
     * @return the equivalent Gregorian date as an instance of {@link Date}
     */
    public LocalDate toLocalDate() {
        return LocalDate.ofEpochDay(toEpochDay());
    }

    public long getMillisOf() {
        return toDate().getTime();
    }

    @Override
    public int compareTo(PersianDate o) {
        long thisTime = getMillisOf();
        long anotherTime = o.getMillisOf();
        return (Long.compare(thisTime, anotherTime));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersianDate that = (PersianDate) o;
        return year == that.year &&
                month == that.month &&
                day == that.day;
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, month, day);
    }

    /**
     * Returns the string representation of this persian date. The string contains of ten
     * characters whose format is "YYYY/MM/dd", where YYYY is the year, MM is the
     * month-of-year and dd is day-of-month. (Each of the capital characters represents a
     * single decimal digit.)
     * <p>
     * If any of the three parts of this persian date is too small to fill up its field,
     * the field is padded with leading zeros.
     *
     * @return a suitable representation of this persian date
     */
    public String toString() {
        return String.format("%04d/%02d/%02d", year, month, day);
    }
}
