package originalnamebot.tasks;

/**
 * Todo is a child class of Task that contains only a description.
 */
public class Todo extends Task {

    /**
     * Constructor for Todo task.
     *
     * @param description Description of Todo.
     * @param input Input string provided by the user. Used to save/load from data.
     */
    public Todo(String description, String input) {
        super(description, input);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
