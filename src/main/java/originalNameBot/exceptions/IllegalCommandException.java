package originalnamebot.exceptions;

/**
 * Describes an exception where a command is not recognised or
 */
public class IllegalCommandException extends Exception {
    public IllegalCommandException(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "I don't understand what you're saying!";
    }
}
