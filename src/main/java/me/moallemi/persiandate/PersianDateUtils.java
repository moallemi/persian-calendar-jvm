package me.moallemi.persiandate;

import java.util.Calendar;

class PersianDateUtils {

    /**
     * The JDN of 1 Farvardin 1; Equivalent to March 19, 622 A.D.
     */
    private static final long PERSIAN_EPOCH = 1948321;

    /**
     * Returns true if and only if {@code val} is greater than or equal to {@code lowerLimit}
     * and is less than or equal to {@code upperLimit}.
     *
     * @param val        the value to be checked, an integer
     * @param lowerLimit lower boundary to be checked, an integer
     * @param upperLimit upper boundary to be checked, an integer
     * @return true if and only if {@code val} is between {@code lowerLimit} and
     * {@code upperLimit}
     */
    static boolean isBetween(int val, int lowerLimit, int upperLimit) {
        return val >= lowerLimit && val <= upperLimit;
    }

    /**
     * Returns true if and only if {@code val} is greater than or equal to {@code lowerLimit}
     * and is less than or equal to {@code upperLimit}.
     *
     * @param val        the value to be checked, a long
     * @param lowerLimit lower boundary to be checked, a long
     * @param upperLimit upper boundary to be checked, a long
     * @return true if and only if {@code val} is between {@code lowerLimit} and
     * {@code upperLimit}
     */
    static boolean isBetween(long val, long lowerLimit, long upperLimit) {
        return val >= lowerLimit && val <= upperLimit;
    }

    /**
     * Checks whether an integer is in a range or not. If {@code val} is less than
     * {@code lowerLimit} or greater than {@code upperLimit}, an IllegalArgumentException
     * will be thrown with a suitable message.
     *
     * @param val        value to check
     * @param lowerLimit lower limit of range
     * @param upperLimit upper limit of range
     * @param valName    the name of {@code val} that is printed in the exception message
     * @return {@code val}, if it is in the range
     */
    static int intRequireRange(int val, int lowerLimit, int upperLimit, String valName) {
        if (!isBetween(val, lowerLimit, upperLimit)) {
            throw new IllegalArgumentException(valName + " " + val +
                    " is out of valid range [" + lowerLimit + ", " + upperLimit + "]");
        }
        return val;
    }

    /**
     * Checks whether a long is greater than zero or not. If {@code val} is less than
     * or equal to zero, an IllegalArgumentException will be thrown with a suitable message.
     *
     * @param val     long value to check
     * @param valName name of {@code val} that is printed in the exception message
     * @return {@code val}, if it is positive
     */
    static long longRequirePositive(long val, String valName) {
        if (val <= 0) {
            throw new IllegalArgumentException(valName + " is not positive: " + val);
        }
        return val;
    }

    /**
     * Ceil function in original algorithm
     *
     * @param double1
     * @param double2
     * @return long
     */
    public static long ceil(double double1, double double2) {
        return (long) (double1 - double2 * Math.floor(double1 / double2));
    }

    /**
     * Converts a provided Persian (Shamsi) date to the Julian Day Number
     * (i.e. the number of days since January 1 in the year 4713 BC).
     * Since the Persian calendar is a highly regular calendar, converting to and from a Julian Day Number
     * is not as difficult as it looks. Basically it's a mather of dividing, rounding and multiplying.
     * This routine uses Julian Day Number 1948321 as focal point, since that Julian Day Number
     * corresponds with 1 Farvardin (1) 1.
     *
     * @param year  int persian year
     * @param month int persian month
     * @param day   int persian day
     * @return long
     */
    public static long persianToJulian(long year, int month, int day) {
        return 365L * ((ceil(year - 474L, 2820D) + 474L) - 1L)
                + ((long) Math.floor((682L * (ceil(year - 474L, 2820D) + 474L) - 110L) / 2816D))
                + (PERSIAN_EPOCH - 1L)
                + 1029983L * ((long) Math.floor((year - 474L) / 2820D))
                + (month < 7 ? 31 * month : 30 * month + 6) + day;
    }

    public static double dateToJulian(Calendar date) {
        int year = date.get(Calendar.YEAR);
        int month = date.get(Calendar.MONTH) + 1;
        int day = date.get(Calendar.DAY_OF_MONTH);
        int hour = date.get(Calendar.HOUR_OF_DAY);
        int minute = date.get(Calendar.MINUTE);
        int second = date.get(Calendar.SECOND);

        double extra = (100.0 * year) + month - 190002.5;
        return (367.0 * year) -
                (Math.floor(7.0 * (year + Math.floor((month + 9.0) / 12.0)) / 4.0)) +
                Math.floor((275.0 * month) / 9.0) +
                day + ((hour + ((minute + (second / 60.0)) / 60.0)) / 24.0) +
                1721013.5 - ((0.5 * extra) / Math.abs(extra)) + 0.5;
    }

    public static void checkRange(int year, int month, int dayOfMonth) {
        if (year > 1999 || year < 1 || month < 1 || month > 12 || dayOfMonth < 1 || dayOfMonth > 31) {
            throw new PersianDateException("result exceeds the supported date range");
        }
    }
}