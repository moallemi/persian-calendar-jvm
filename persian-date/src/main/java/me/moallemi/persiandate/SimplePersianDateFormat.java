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

    /* TODO implement format and date Format
    public String format(PersianDate date) {
        return date.toString();
    }

    public PersianDate parse(String source) {
        return null;
    }
     */
}
