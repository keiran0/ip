public class Task {

    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
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

}
