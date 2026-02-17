package originalnamebot.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import originalnamebot.exceptions.IllegalCommandException;
import originalnamebot.utils.Date;

/**
 * Tests for the Deadline class.
 */
public class DeadlineTest {
    /**
     * Tests the string representation of a Deadline without time.
     */
    @Test
    public void testDeadlineWithoutTimeToString() throws IllegalCommandException {

        Date date = new Date("2026-01-28");
        Deadline task = new Deadline("Submit assignment", date, false);
        String expected = "[D][ ] Submit assignment (by: " + date.toString() + ")";
        assertEquals(expected, task.toString());

    }

    /**
     * Tests the string representation of a Deadline with time.
     */
    @Test
    public void testDeadlineWithTimeToString() throws IllegalCommandException {
        Date date = new Date("2026-01-28 1900");
        Deadline task = new Deadline("Submit assignment", date, false);
        String expected = "[D][ ] Submit assignment (by: " + date.toString() + ")";
        assertEquals(expected, task.toString());
    }

    /**
     * Tests marking a Deadline task as done.
     */
    @Test
    public void testDeadlineMarkDone() throws IllegalCommandException {
        Date date = new Date("2026-01-28 1900");
        Deadline task = new Deadline("Submit assignment", date, false);
        task.markDone();
        assertEquals(task.isDone(), true);
    }

    /**
     * Tests marking a Deadline task as not done.
     */
    @Test
    public void testDeadlineMarkNotDone() throws IllegalCommandException {
        Date date = new Date("2026-01-28 1900");
        Deadline task = new Deadline("Submit assignment", date, false);
        task.markDone();
        task.markNotDone();
        assertEquals(task.isDone(), false);
    }

}
