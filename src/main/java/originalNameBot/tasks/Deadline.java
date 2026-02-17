package originalnamebot.tasks;

import originalnamebot.utils.Date;

/**
 * Deadline is a child class of Task that contains a description and a by Date.
 */
public class Deadline extends Task {

    private Date by;

    /**
     * Constructor for Deadline task.
     *
     * @param description Description of deadline.
     * @param by End Date (optional: time) of deadline.
     */
    public Deadline(String description, Date by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }

    @Override
    public String getFileString() {
        return "D|" + super.getFileString() + "|" + this.by.getDateString();
    }

}
