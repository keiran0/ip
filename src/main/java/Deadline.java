public class Deadline extends Task {

    private Date by;

    public Deadline(String description, Date by, String input) {
        super(description, input);
        this.by = by;
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
    
}
