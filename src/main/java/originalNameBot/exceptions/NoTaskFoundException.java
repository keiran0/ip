package originalnamebot.exceptions;

/**
 * Describes an exception where a valid command is entered but the task does not exist (>= length of
 * task list or negative)
 */
public class NoTaskFoundException extends Exception {
    public NoTaskFoundException(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "Enter a number!";
    }
}
