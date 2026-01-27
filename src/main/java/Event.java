public class Event extends Task {

    private Date from;
    private Date to;

    public Event(String description, Date from, Date to, String input) {
        super(description, input);
        this.from = from;
        this.to = to;    
    }

    public String toString() {
        return "[E]" + super.toString() + "(from: " + this.from + " to: " + this.to + ")";
    }
}
