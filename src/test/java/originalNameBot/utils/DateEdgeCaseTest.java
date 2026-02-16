package originalnamebot.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import originalnamebot.exceptions.IllegalCommandException;

/**
 * Edge case tests for Date parsing and formatting.
 */
public class DateEdgeCaseTest {

    /**
     * Tests parsing of a valid leap-year date.
     */
    @Test
    public void testLeapYearDateValid() throws IllegalCommandException {
        Date date = new Date("2024-02-29");
        assertEquals("Feb 29 2024", date.toString());
    }

    /**
     * Tests that non-leap-year Feb 29 is rejected.
     */
    @Test
    public void testNonLeapYearDateInvalid() {
        Exception exception = assertThrows(IllegalCommandException.class, () -> {
            new Date("2023-02-29");
        });
        String expected = "Provide valid date! Check valid day/month/year values";
        String actual = exception.getMessage();
        assertEquals(expected, actual);
    }

    /**
     * Tests parsing of midnight time (00:00).
     */
    @Test
    public void testMidnightTimeParsing() throws IllegalCommandException {
        Date date = new Date("2026-01-01 0000");
        assertEquals("Jan 01 2026 00:00", date.toString());
    }

    /**
     * Tests parsing of the maximum valid time (23:59).
     */
    @Test
    public void testMaxTimeParsing() throws IllegalCommandException {
        Date date = new Date("2026-12-31 2359");
        assertEquals("Dec 31 2026 23:59", date.toString());
    }

    /**
     * Tests that an invalid time value is rejected.
     */
    @Test
    public void testInvalidTimeThrows() {
        Exception exception = assertThrows(IllegalCommandException.class, () -> {
            new Date("2026-01-01 2460");
        });
        String expected = "Provide valid datetime format! Check valid day/month/year/time values";
        String actual = exception.getMessage();
        assertEquals(expected, actual);
    }

}
