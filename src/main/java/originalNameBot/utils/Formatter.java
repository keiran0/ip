package originalNameBot.utils;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Formatter {
    /**
     * Returns a string representation of LocalDate into MMM dd yyyy format.
     * @param date LocalDate to format.
     * @return Formatted date.
     */
    public static String formatDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    /**
     * Returns a string representation of LocalDateTime in MMM dd YYYY HH:mm format.
     * @param datetime LocalDateTime to format.
     * @return Formatted datetime.
     */
    public static String formatDateTime(LocalDateTime datetime) {
        return datetime.format(DateTimeFormatter.ofPattern("MMM dd YYYY HH:mm"));
    }
}
