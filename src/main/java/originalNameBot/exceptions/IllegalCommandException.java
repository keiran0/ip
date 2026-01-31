package originalnamebot.exceptions;

public class IllegalCommandException extends Exception {
    public IllegalCommandException(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "I don't understand what you're saying!";
    }
}
