package originalnamebot.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import originalnamebot.exceptions.IllegalCommandException;

public class DateTest {

    /**
     * Tests that incorrect date format throws IllegalCommandException.
     */
    @Test
    public void incorrectFormatThrowsIllegalCommandException() {
        Exception exception = assertThrows(IllegalCommandException.class, () -> {
            Date date = new Date("abc");
        });

        String expected = "Provide valid date format!";
        String actual = exception.getMessage();
        assertTrue(actual.equals(expected));
    }

    /**
     * Tests that invalid dates throw IllegalCommandException.
     */
    @Test
    public void invalidDatesThrowsIllegalCommandException() {
        Exception exception = assertThrows(IllegalCommandException.class, () -> {
            Date date = new Date("2026-01-33");
        });

        String expected = "Provide valid date! Check valid day/month/year values";
        String actual = exception.getMessage();
        assertTrue(actual.equals(expected));
    }

    /**
     * Tests that invalid datetime values throw IllegalCommandException.
     */
    @Test
    public void invalidDateTimeThrowsIllegalCommandException() {
        Exception exception = assertThrows(IllegalCommandException.class, () -> {
            Date date = new Date("2026-01-01 2400");
        });

        String expected = "Provide valid datetime format! Check valid day/month/year/time values";
        String actual = exception.getMessage();
        assertTrue(actual.equals(expected));
    }

    /**
     * Tests the string representation of a Date without time.
     */
    @Test
    public void testDateWithoutTimeToString() throws IllegalCommandException {
        Date date = new Date("2026-01-29");
        assertEquals(date.toString(), "Jan 29 2026");
    }

    /**
     * Tests the string representation of a Date with time.
     */
    @Test
    public void testDateWithTimeToString() throws IllegalCommandException {
        Date date = new Date("2026-01-29 1900");
        assertEquals(date.toString(), "Jan 29 2026 19:00");
    }

}
