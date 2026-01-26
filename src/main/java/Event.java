public class Event extends Task {

    private String from;
    private String to;

    public Event(String description, String from, String to, String input) {
        super(description, input);
        this.from = from;
        this.to = to;
    }

    public String toString() {
        return "[E]" + super.toString() + "(from: " + this.from + " to: " + this.to + ")";
    }
}
