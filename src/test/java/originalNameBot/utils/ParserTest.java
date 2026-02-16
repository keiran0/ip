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

    /**
     * Tests that parsing an invalid event format throws.
     */
    @Test
    public void testParseEventInvalidFormatThrows() {
        assertThrows(IllegalCommandException.class, () -> {
            Parser.parseEvent("event this is invalid");
        });
    }

    /**
     * Tests that a non-numeric mark argument throws NoTaskFoundException.
     */
    @Test
    public void testParseMarkNonNumberThrows() throws IllegalCommandException, NoTaskFoundException {
        assertThrows(NoTaskFoundException.class, () -> {
            Parser.parseMark("mark notanumber");
        });
    }

    /**
     * Tests that parseFind returns the search keyword.
     */
    @Test
    public void testParseFindReturns() throws IllegalCommandException {
        String input = "find keyword";
        String result = Parser.parseFind(input);
        assertEquals("keyword", result);
    }

}
