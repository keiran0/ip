package originalnamebot.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import originalnamebot.exceptions.IllegalCommandException;
import originalnamebot.exceptions.NoTaskFoundException;
import originalnamebot.tasks.Todo;

/**
 * Edge-case tests for Parser behaviour with whitespace and mixed-case inputs.
 */
public class ParserEdgeCaseTest {

    @Test
    public void testIsValidRejectsLeadingWhitespace() {
        assertTrue(Parser.isValid("  todo something"));
    }

    @Test
    public void testParseTodoAcceptsLeadingWhitespace() throws IllegalCommandException {
        String raw = "  todo spaced";
        Todo t = Parser.parseTodo(raw);
        assertEquals("todo spaced", t.getCreationString());
        assertEquals("[T][ ] spaced", t.toString());
    }

    @Test
    public void testIsValidMixedCaseIsFalse() {
        assertFalse(Parser.isValid("Todo something"));
    }

    @Test
    public void testParseMarkWithExtraSpaces() throws IllegalCommandException, NoTaskFoundException {
        int idx = Parser.parseMark("  mark   5  ");
        assertEquals(5, idx);
    }

    @Test
    public void testParseFindTrimsWhitespace() throws IllegalCommandException {
        String result = Parser.parseFind("  find   keyword  ");
        assertEquals("keyword", result);
    }

}
