package originalNameBot.tasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import originalNameBot.exceptions.IllegalCommandException;
import originalNameBot.utils.Date;
import originalNameBot.utils.Parser;

public class DeadlineTest {
    @Test
    public void testDeadlineWithoutTimeToString() throws IllegalCommandException {

        Date date = new Date("2026-01-28");
        Deadline task = new Deadline("Submit assignment", date, "deadline Submit assignment");
        String expected = "[D][ ] Submit assignment (by: " + date.toString() + ")";
        assertEquals(expected, task.toString());

    }

    @Test
    public void testDeadlineWithTimeToString() throws IllegalCommandException {
        Date date = new Date("2026-01-28 1900");
        Deadline task = new Deadline("Submit assignment", date, "deadline Submit assignment");
        String expected = "[D][ ] Submit assignment (by: " + date.toString() + ")";
        assertEquals(expected, task.toString());
    }

    @Test
    public void testDeadlineMarkDone() throws IllegalCommandException {
        Date date = new Date("2026-01-28 1900");
        Deadline task = new Deadline("Submit assignment", date, "deadline Submit assignment");
        task.markDone();
        assertEquals(task.getIsDone(), true);
    }

    @Test
    public void testDeadlineMarkNotDone() throws IllegalCommandException {
        Date date = new Date("2026-01-28 1900");
        Deadline task = new Deadline("Submit assignment", date, "deadline Submit assignment");
        task.markNotDone();
        assertEquals(task.getIsDone(), false);
    }

    @Test
    public void testDeadlineCreationString() throws IllegalCommandException {
        String input = "deadline testdeadline /by 2000-10-10 1900";
        Deadline deadlineFromParser = Parser.parseDeadline(input);

        Date date = new Date("2000-10-10 1900");
        Deadline deadline = new Deadline("testdeadline", date, input);

        assertEquals(deadline.getIsDone(), deadlineFromParser.getIsDone());
        assertEquals(deadline.getCreationString(), deadlineFromParser.getCreationString());
        assertEquals(deadline.toString(), deadlineFromParser.toString());

    }
}
