package originalNameBot.tasks;

public class Task {

    private String description;
    private boolean isDone;
    private String creationString;

    public Task(String description, String input) {
        this.description = description;
        this.isDone = false;
        this.creationString = input.strip();
    }

    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + this.description;
        } else {
            return "[ ] " + this.description;
        }
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markNotDone() {
        this.isDone = false;
    }

    public String getCreationString() {
        return this.creationString;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Finds a sequence of character in the description attribute of the task.
     * @param input Sequence of characters to search in the description.
     * @return true if the description contains the input, false otherwise.
     */
    public boolean find(String input) {
        return this.description.contains(input);
    }

}
