package originalnamebot.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import originalnamebot.exceptions.IllegalCommandException;
import originalnamebot.utils.Date;
import originalnamebot.utils.Parser;

public class DeadlineTest {
    /**
     * Tests the string representation of a Deadline without time.
     */
    @Test
    public void testDeadlineWithoutTimeToString() throws IllegalCommandException {

        Date date = new Date("2026-01-28");
        Deadline task = new Deadline("Submit assignment", date, "deadline Submit assignment");
        String expected = "[D][ ] Submit assignment (by: " + date.toString() + ")";
        assertEquals(expected, task.toString());

    }

    /**
     * Tests the string representation of a Deadline with time.
     */
    @Test
    public void testDeadlineWithTimeToString() throws IllegalCommandException {
        Date date = new Date("2026-01-28 1900");
        Deadline task = new Deadline("Submit assignment", date, "deadline Submit assignment");
        String expected = "[D][ ] Submit assignment (by: " + date.toString() + ")";
        assertEquals(expected, task.toString());
    }

    /**
     * Tests marking a Deadline task as done.
     */
    @Test
    public void testDeadlineMarkDone() throws IllegalCommandException {
        Date date = new Date("2026-01-28 1900");
        Deadline task = new Deadline("Submit assignment", date, "deadline Submit assignment");
        task.markDone();
        assertEquals(task.isDone(), true);
    }

    /**
     * Tests marking a Deadline task as not done.
     */
    @Test
    public void testDeadlineMarkNotDone() throws IllegalCommandException {
        Date date = new Date("2026-01-28 1900");
        Deadline task = new Deadline("Submit assignment", date, "deadline Submit assignment");
        task.markDone();
        task.markNotDone();
        assertEquals(task.isDone(), false);
    }

    /**
     * Tests that Deadline creation string matches parsed Deadline.
     */
    @Test
    public void testDeadlineCreationString() throws IllegalCommandException {
        String input = "deadline testdeadline /by 2000-10-10 1900";
        Deadline deadlineFromParser = Parser.parseDeadline(input);

        Date date = new Date("2000-10-10 1900");
        Deadline deadline = new Deadline("testdeadline", date, input);

        assertEquals(deadline.isDone(), deadlineFromParser.isDone());
        assertEquals(deadline.getCreationString(), deadlineFromParser.getCreationString());
        assertEquals(deadline.toString(), deadlineFromParser.toString());

    }
}
