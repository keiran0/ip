package originalnamebot.tasks;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Edge-case tests for Task.find behaviour (case-insensitive and trimming).
 */
public class TaskFindEdgeTest {

    /**
     * Tests that `find` is case-insensitive and trims whitespace.
     */
    @Test
    public void testFindIsCaseInsensitiveAndTrimmed() {
        Todo t = new Todo("Write Code", false);
        assertTrue(t.find("write"));
        assertTrue(t.find("  write  "));
        assertTrue(t.find("CODE"));
        assertFalse(t.find("something"));
    }

}
