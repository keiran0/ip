public class Task {

    private String description;
    private boolean isDone;
    private String creationString;

    public Task(String description, String input) {
        this.description = description;
        this.isDone = false;
        this.creationString = input;
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

}
