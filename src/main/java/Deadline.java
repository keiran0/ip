public class Deadline extends Task {

    private String by;

    public Deadline(String description, String by, String input) {
        super(description, input);
        this.by = by;
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
    
}
