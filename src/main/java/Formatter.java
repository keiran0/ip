import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Formatter {
    public static String formatDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    public static String formatDateTime(LocalDateTime datetime) {
        return datetime.format(DateTimeFormatter.ofPattern("MMM dd YYYY HH:mm"));
    }
}
