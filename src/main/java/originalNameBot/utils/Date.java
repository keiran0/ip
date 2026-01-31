package originalnamebot.utils;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import originalnamebot.exceptions.IllegalCommandException;

/**
 * Date contains methods to parse date and datetime from strings. It supports both dates and
 * datetimes and will be displayed according to their type.
 */
public class Date {
    private boolean hasTime;
    private LocalDateTime datetime;
    private LocalDate date;

    private String dateRegex = "(\\d{4}-\\d{2}-\\d{2})";
    private String datetimeRegex = dateRegex + " *" + "(\\d{4})";

    /**
     * Creates a new Date.
     *
     * @param input String input of the date. Accepts the format YYYY-MM-DD or YYYY-MM-DD-HHMM.
     * @return Date object created from the input string.
     * @throws IllegalCommandException if format is wrong.
     */
    public Date(String input) throws IllegalCommandException {
        Pattern datePattern = Pattern.compile(dateRegex);
        Pattern dateTimePattern = Pattern.compile(datetimeRegex);
        Matcher dateTimeMatcher = dateTimePattern.matcher(input.strip());
        Matcher dateMatcher = datePattern.matcher(input.strip());
        if (dateTimeMatcher.find()) {
            try {
                String rawTime = dateTimeMatcher.group(2); // 1900
                String toParse = dateTimeMatcher.group(1) + "T" + rawTime.charAt(0)
                        + rawTime.charAt(1) + ":" + rawTime.charAt(2) + rawTime.charAt(3);
                LocalDateTime datetime = LocalDateTime.parse(toParse);
                LocalDate date = LocalDate.parse(dateTimeMatcher.group(1));
                this.hasTime = true;
                this.datetime = datetime;
                this.date = date;
            } catch (DateTimeException e) {
                throw new IllegalCommandException(
                        "Provide valid datetime format! Check valid day/month/year/time values");
            }
        } else if (dateMatcher.find()) {
            try {
                LocalDate date = LocalDate.parse(input);
                this.hasTime = false;
                this.date = date;
                this.datetime = null;
            } catch (DateTimeException e) {
                throw new IllegalCommandException(
                        "Provide valid date! Check valid day/month/year values");
            }
        } else {
            throw new IllegalCommandException("Provide valid date format!");
        }
    }

    @Override
    public String toString() {
        String ret = hasTime ? Formatter.formatDateTime(datetime) : Formatter.formatDate(date);
        return ret;
    }
}
