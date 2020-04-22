package me.moallemi.persiandate;

import java.text.DateFormatSymbols;
import java.util.Locale;

public class SimplePersianDateFormat {

    private static String DEFAULT_FORMAT = "yyyy/MM/dd";

    private String pattern;

    private DateFormatSymbols formatData;

    private Locale locale;

    public SimplePersianDateFormat() {
        this(DEFAULT_FORMAT, Locale.getDefault());
    }

    public SimplePersianDateFormat(String pattern) {
        this(pattern, Locale.getDefault());
    }

    public SimplePersianDateFormat(String pattern, Locale locale) {
        if (pattern == null || locale == null) {
            throw new NullPointerException();
        }
        this.pattern = pattern;
        this.formatData = DateFormatSymbols.getInstance(locale);
        this.locale = locale;
    }

    /**
     * y  Year (e.g. 99 or 1399). Use either yy or yyyy.
     * M  Month in year. Number of M's determine length of format (e.g. MM, MMM or MMMMM)
     * d  Day in month. Number of d's determine length of format (e.g. d or dd)
     *
     * @param date
     * @return
     */
    public String format(PersianDate date) {
        char[] chars = pattern.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            switch (chars[i]) {
                case 'y':
                    int yearOccurrence = countOccurrence(chars, chars[i], i);
                    if (yearOccurrence != 2) {
                        yearOccurrence = 4;
                    }
                    int year = (int) (date.getYear() % Math.pow(10, yearOccurrence));
                    stringBuilder.append(padLeftZeros(year, yearOccurrence));

                    i += yearOccurrence - 1;
                    break;
                case 'M':
                    int monthOccurrence = countOccurrence(chars, chars[i], i);
                    if (monthOccurrence > 2) {
                        stringBuilder.append(date.getMonth());
                    } else {
                        int month = (int) (date.getMonthValue() % Math.pow(10, Math.max(monthOccurrence, String.valueOf(date.getMonth()).length())));
                        stringBuilder.append(padLeftZeros(month, monthOccurrence));
                    }

                    i += monthOccurrence - 1;
                    break;
                case 'd':
                    int dayOccurrence = countOccurrence(chars, chars[i], i);
                    int day = (int) (date.getDayOfMonth() % Math.pow(10, Math.max(dayOccurrence, String.valueOf(date.getDayOfMonth()).length())));
                    stringBuilder.append(padLeftZeros(day, dayOccurrence));

                    i += dayOccurrence - 1;
                    break;
                case '-':
                case '/':
                case ' ':
                    stringBuilder.append(chars[i]);
                    break;
                default:
                    throw new PersianDateException("Invalid character in pattern: " + chars[i]);
            }
        }
        return stringBuilder.toString();
    }

    private int countOccurrence(char[] chars, char character, int index) {
        int occurrence = 0;
        while (index < chars.length && chars[index] == character) {
            occurrence++;
            index++;
        }
        return occurrence;
    }

    public PersianDate parse(String source) throws PersianDateException {
        throw new PersianDateException("Not Implemented");
    }

    private String padLeftZeros(int input, int length) {
        String inputString = Integer.toString(input);
        if (inputString.length() >= length) {
            return inputString;
        }
        StringBuilder sb = new StringBuilder();
        while (sb.length() < length - inputString.length()) {
            sb.append('0');
        }
        sb.append(inputString);

        return sb.toString();
    }
}
