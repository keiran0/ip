package originalnamebot.tasks;

import originalnamebot.utils.Date;

/**
 * Event is a child class of Task that contains a description, a from Date, and a to Date.
 */
public class Event extends Task {

    private Date from;
    private Date to;

    /**
     * Constructor for Event task.
     * @param description Description of event.
     * @param from Start date (optional: time) of event.
     * @param to End date (optional: time) of event.
     * @param input Input string provided by the user. Used to save/load from data.
     */
    public Event(String description, Date from, Date to, String input) {
        super(description, input);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
}
