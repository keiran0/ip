import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import originalNameBot.exceptions.IllegalCommandException;
import originalNameBot.utils.Date;
import originalNameBot.tasks.Todo;

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
        assertEquals(todo.getIsDone(), true);
        String expected =  "[T][X] Submit assignment";
        assertEquals(todo.toString(), expected);
    }

    @Test
    public void testTodoMarkNotDone() {
        todo.markDone();
        todo.markNotDone();
        assertEquals(todo.getIsDone(), false);
    }


}


