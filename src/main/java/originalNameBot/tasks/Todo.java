package originalnamebot.tasks;

public class Todo extends Task {
    public Todo(String description, String command) {
        super(description, command);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
