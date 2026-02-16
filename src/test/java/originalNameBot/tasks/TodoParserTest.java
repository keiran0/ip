package originalnamebot.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import originalnamebot.exceptions.IllegalCommandException;
import originalnamebot.utils.Parser;

/**
 * Tests for Todo parsing via Parser.
 */
public class TodoParserTest {

    @Test
    public void testTodoCreationString() throws IllegalCommandException {
        String input = "todo testtodo";
        Todo todoFromParser = Parser.parseTodo(input);

        Todo todo = new Todo("testtodo", input);

        assertEquals(todo.getCreationString(), todoFromParser.getCreationString());
        assertEquals(todo.toString(), todoFromParser.toString());
    }

}
