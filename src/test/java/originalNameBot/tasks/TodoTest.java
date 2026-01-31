package originalnamebot.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    private static Todo todo = new Todo("Submit assignment", "todo Submit assignment");

    @Test
    public void testTodoToString() {
        String expected = "[T][ ] Submit assignment";
        assertEquals(todo.toString(), expected);
    }


    @Test
    public void testTodoMarkDone() {
        todo.markDone();
        assertEquals(todo.isDone(), true);
        String expected = "[T][X] Submit assignment";
        assertEquals(todo.toString(), expected);
    }

    @Test
    public void testTodoMarkNotDone() {
        todo.markDone();
        todo.markNotDone();
        assertEquals(todo.isDone(), false);
    }


}


