package me.moallemi.persiandate;

import java.util.Locale;

public class SimplePersianDateFormat {

    private static String DEFAULT_FORMAT = "yyyy/MM/dd";

    private String pattern;

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
        this.locale = locale;
    }

    /**
     * y  Year (e.g. 99 or 1399). Use either yy or yyyy.
     * M  Month in year. Number of M's determine length of format (e.g. M, MM)
     * d  Day in month. Number of d's determine length of format (e.g. d or dd)
     * -, /, [space] as delimiter
     */
    public String format(PersianDate date) throws PersianDateException {
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

    /**
     * Year (e.g. 99 or 1399)
     * Month in year. Number of M's determine length of format (e.g. 3, 03)
     * Day in month. Number of d's determine length of format (e.g. 4 or 04)
     * -, /, [space] as delimiter
     */
    public PersianDate parse(String source) throws PersianDateException {
        int year = 0, month = 0, dayOfMonth = 0;

        char[] patternChars = pattern.toCharArray();
        char[] sourceChars = source.toCharArray();
        for (int i = 0; i < patternChars.length; i++) {
            switch (patternChars[i]) {
                case 'y':
                    int yearOccurrence = countOccurrence(patternChars, patternChars[i], i);
                    year = tryParseInt(source.substring(i, i + yearOccurrence));

                    if (year < 100) {
                        // Don't know if it is a good idea or not, may be change it to PersianDate.now().getYear() - PersianDate.now().getYear() % 100
                        year += 1300;
                    }

                    i += yearOccurrence - 1;
                    break;
                case 'M':
                    int monthOccurrence = countOccurrence(patternChars, patternChars[i], i);
                    month = tryParseInt(source.substring(i, i + monthOccurrence));

                    i += monthOccurrence - 1;
                    break;
                case 'd':
                    int dayOccurrence = countOccurrence(patternChars, patternChars[i], i);
                    dayOfMonth = tryParseInt(source.substring(i, i + dayOccurrence));

                    i += dayOccurrence - 1;
                    break;
                case '-':
                case '/':
                case ' ':
                    if (sourceChars[i] != patternChars[i]) {
                        throw new PersianDateException("pattern does not match given source");
                    }
                    break;
                default:
                    throw new PersianDateException("Invalid character in pattern: " + patternChars[i]);
            }
        }
        return PersianDate.of(year, month, dayOfMonth);
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

    public static Integer tryParseInt(String text) throws PersianDateException {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            throw new PersianDateException("pattern does not match given source");
        }
    }
}
