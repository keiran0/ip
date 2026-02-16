package originalnamebot.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import originalnamebot.exceptions.IllegalCommandException;
import originalnamebot.exceptions.NoTaskFoundException;

/**
 * Tests for Parser error handling and simple parsing.
 */
public class ParserTest {

    @Test
    public void testParseEventInvalidFormatThrows() {
        assertThrows(IllegalCommandException.class, () -> {
            Parser.parseEvent("event this is invalid");
        });
    }

    @Test
    public void testParseMarkNonNumberThrows() throws IllegalCommandException, NoTaskFoundException {
        assertThrows(NoTaskFoundException.class, () -> {
            Parser.parseMark("mark notanumber");
        });
    }

    @Test
    public void testParseFindReturns() throws IllegalCommandException {
        String input = "find keyword";
        String result = Parser.parseFind(input);
        assertEquals("keyword", result);
    }

}
