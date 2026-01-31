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
     * @param input Input string provided by the user. Used to save/load from data.
     */
    public Deadline(String description, Date by, String input) {
        super(description, input);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }

}
