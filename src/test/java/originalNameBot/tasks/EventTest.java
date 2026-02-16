package originalnamebot.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import originalnamebot.exceptions.IllegalCommandException;
import originalnamebot.utils.Date;

/**
 * Tests for the Event class.
 */
public class EventTest {

    private static String dateWithTimeString = "2026-01-28 1900";
    private static String dateWithoutTimeString = "2026-01-28";
    private static String description = "Submit assignment";

    /**
     * Tests the string representation of an Event with mixed date/time formats.
     */
    @Test
    public void testEventMixedTimesToString() throws IllegalCommandException {
        Date dateWithTime = new Date(EventTest.dateWithTimeString);
        Date dateWithoutTime = new Date(EventTest.dateWithoutTimeString);
        Event event = new Event("Submit assignment", dateWithTime, dateWithoutTime,
                "event Submit assignment /from " + dateWithTimeString + " /to "
                        + dateWithoutTimeString);
        String expected = "[E][ ] Submit assignment (from: " + dateWithTime.toString() + " to: "
                + dateWithoutTime.toString() + ")";
        assertEquals(expected, event.toString());
    }

    /**
     * Tests the string representation of an Event with dates only.
     */
    @Test
    public void testEventDatesOnlyToString() throws IllegalCommandException {
        Date dateWithoutTimeFirst = new Date(EventTest.dateWithoutTimeString);
        Date dateWithoutTimeSecond = new Date(EventTest.dateWithoutTimeString);
        Event event = new Event(EventTest.description, dateWithoutTimeFirst, dateWithoutTimeSecond,
                "event " + description + " /from " + dateWithoutTimeString + " /to "
                        + dateWithoutTimeString);
        String expected = "[E][ ] Submit assignment (from: " + dateWithoutTimeFirst.toString()
                + " to: " + dateWithoutTimeSecond.toString() + ")";
        assertEquals(expected, event.toString());
    }

    /**
     * Tests the string representation of an Event with times only.
     */
    @Test
    public void testEventTimesOnlyToString() throws IllegalCommandException {
        Date dateFirst = new Date("2026-08-08 1900");
        Date dateSecond = new Date("2026-12-11 1800");
        Event event = new Event("Submit assignment", dateFirst, dateSecond,
                "event Submit assignment /from 2026-08-08 1900 /to 2026-12-11 1800");
        String expected = "[E][ ] Submit assignment (from: " + dateFirst.toString() + " to: "
                + dateSecond.toString() + ")";
        assertEquals(expected, event.toString());
    }

    /**
     * Tests marking an Event task as done.
     */
    @Test
    public void testEventMarkDone() throws IllegalCommandException {
        Date dateFirst = new Date("2026-08-08 1900");
        Date dateSecond = new Date("2026-12-11 1800");
        Event event = new Event("Submit assignment", dateFirst, dateSecond,
                "event Submit assignment /from 2026-08-08 1900 /to 2026-12-11 1800");
        event.markDone();
        assertEquals(event.isDone(), true);
    }

    /**
     * Tests marking an Event task as not done.
     */
    @Test
    public void testEventMarkNotDone() throws IllegalCommandException {
        Date dateFirst = new Date("2026-08-08 1900");
        Date dateSecond = new Date("2026-12-11 1800");
        Event event = new Event("Submit assignment", dateFirst, dateSecond,
                "event Submit assignment /from 2026-08-08 1900 /to 2026-12-11 1800");
        event.markDone();
        event.markNotDone();
        assertEquals(event.isDone(), false);
    }

}
