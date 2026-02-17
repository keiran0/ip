package originalnamebot.tasks;

/**
 * A task contains a description and an isDone attribute, as well as methods to set isDone to true or false.
 */
public class Task {

    private String description;
    private boolean isDone = false;

    /**
     * Constructor for Task.
     *
     * @param description Description of Task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + this.description;
        } else {
            return "[ ] " + this.description;
        }
    }

    /**
     * Marks the task's isDone attribute to true.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the task's isDone attribute to false.
     */
    public void markNotDone() {
        this.isDone = false;
    }


    /**
     * Returns the task's isDone attribute.
     *
     * @return isDone attribute of the task.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Finds a sequence of character in the description attribute of the task.
     *
     * @param input Sequence of characters to search in the description.
     * @return true if the description contains the input, false otherwise.
     */
    public boolean find(String input) {
        return this.description.toLowerCase().contains(input.toLowerCase().strip());
    }

    /**
     * Obtains the file string needed for saving/loading from the txt file.
     * @return File string.
     */
    public String getFileString() {
        String doneString = "F";
        if (this.isDone) {
            doneString = "T";
        }
        return this.description + "|" + doneString;
    }

}
