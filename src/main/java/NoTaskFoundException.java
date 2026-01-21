public class NoTaskFoundException extends Exception {
    public NoTaskFoundException(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "Enter a number!";
    }
}
