package originalnamebot.tasks;

/**
 * Todo is a child class of Task that contains only a description.
 */
public class Todo extends Task {

    /**
     * Constructor for Todo task.
     *
     * @param description Description of Todo.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String getFileString() {
        return "T|" + super.getFileString();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
