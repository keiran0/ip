package originalnamebot.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Tests for the Todo class.
 */
public class TodoTest {
    private static Todo todo = new Todo("Submit assignment", false);

    /**
     * Tests the string representation of a Todo task.
     */
    @Test
    public void testTodoToString() {
        String expected = "[T][ ] Submit assignment";
        assertEquals(todo.toString(), expected);
    }


    /**
     * Tests marking a Todo task as done.
     */
    @Test
    public void testTodoMarkDone() {
        todo.markDone();
        assertEquals(todo.isDone(), true);
        String expected = "[T][X] Submit assignment";
        assertEquals(todo.toString(), expected);
    }

    /**
     * Tests marking a Todo task as not done.
     */
    @Test
    public void testTodoMarkNotDone() {
        todo.markDone();
        todo.markNotDone();
        assertEquals(todo.isDone(), false);
    }


}


