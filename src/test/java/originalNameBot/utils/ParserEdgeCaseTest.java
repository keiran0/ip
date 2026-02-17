package originalnamebot.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import originalnamebot.exceptions.IllegalCommandException;
import originalnamebot.exceptions.NoTaskFoundException;

/**
 * Edge-case tests for Parser behaviour with whitespace and mixed-case inputs.
 */
public class ParserEdgeCaseTest {

    /**
     * Tests that `isValid` accepts commands with surrounding whitespace.
     */
    @Test
    public void testIsValidRejectsLeadingWhitespace() {
        assertTrue(Parser.isValid("  todo something"));
    }

    /**
     * Tests that mixed-case commands are not considered valid.
     */
    @Test
    public void testIsValidMixedCaseIsFalse() {
        assertFalse(Parser.isValid("Todo something"));
    }

    /**
     * Tests parsing a `mark` command with extra spaces.
     */
    @Test
    public void testParseMarkWithExtraSpaces() throws IllegalCommandException, NoTaskFoundException {
        int idx = Parser.parseMark("  mark   5  ");
        assertEquals(5, idx);
    }

    /**
     * Tests `find` parsing trims internal/trailing whitespace.
     */
    @Test
    public void testParseFindTrimsWhitespace() throws IllegalCommandException {
        String result = Parser.parseFind("  find   keyword  ");
        assertEquals("keyword", result);
    }

}
