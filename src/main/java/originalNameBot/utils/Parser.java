package originalNameBot.utils;
import java.util.regex.*;

import originalNameBot.exceptions.IllegalCommandException;
import originalNameBot.exceptions.NoTaskFoundException;
import originalNameBot.tasks.Deadline;
import originalNameBot.tasks.Event;
import originalNameBot.tasks.Todo;
import originalNameBot.tasks.Tasklist;

public class Parser {

    private static String validCommands = "^(list|todo|deadline|event|mark|unmark|bye|delete).*";
    private static String todo = "todo *(\\S+.*)";
    private static String event = "event *(\\S+.*)/from (.*) /to (.*)";
    private static String deadline = "deadline *(\\S+.*) /by (.*)";
    private static String mark = "^mark *(\\d*)";
    private static String unmark = "^unmark *(\\d*)";
    private static String delete = "^delete *(\\d*)";

    /**
     * Returns true if the input starts with list/todo/deadline/event/mark/unmark/bye/delete.
     * @param input User input
     */
    public static boolean isValid(String input) {
        return Pattern.matches(validCommands, input);
        // can throw invalidcommandexception here?
    }

    /**
     * Writes tasks from tasklist into data/tasks.txt
     * @param input User input to create/delete/mark/unmark/list tasks
     * @param command String that specifies the command given in the input.
     */
    public static void parseTask(String input, String command) {
        if (command.equals("list")) {
            Tasklist.listTasks();
        }

        if (command.equals("todo") || command.equals("deadline") || command.equals("event")) {
            Tasklist.addTask(input, command);
        }

        if (command.equals("mark")) {
            Tasklist.markTask(input);
        }

        if (command.equals("unmark")) {
            Tasklist.unmarkTask(input);
        }

        if (command.equals("delete")) {
            Tasklist.deleteTask(input);
        }
    }

    /**
     * Obtains the command from user input. 
     * @param input User input to check for valid commands from.
     * @return command obtained from input string.
     * @throws IllegalCommandException if the command is invalid.
     */
    public static String obtainCommand(String input) throws IllegalCommandException {
        Pattern p = Pattern.compile(validCommands);
        Matcher m = p.matcher(input.strip());

        if (!m.find())
            throw new IllegalCommandException("Invalid command");

        return m.group(1);
    }

    /**
     * Parses an input string that creates Todo tasks. 
     * @param input User input string that creates Todo tasks.
     * @throws IllegalCommandException if the todo format is invalid.
     */
    public static Todo parseTodo(String input) throws IllegalCommandException {
        Pattern p = Pattern.compile(todo);
        Matcher m = p.matcher(input.strip());
        if (!m.find())
            throw new IllegalCommandException("Invalid todo format");
        String description = m.group(1);
        return new Todo(description, input);
    }

    /**
     * Parses an input string that creates Deadline tasks. 
     * @param input User input string that creates Deadline tasks.
     * @return Deadline object that was created from the input string.
     * @throws IllegalCommandException if the deadline format is invalid.
     */
    public static Deadline parseDeadline(String input) throws IllegalCommandException {
        Pattern p = Pattern.compile(deadline);
        Matcher m = p.matcher(input.strip());
        if (!m.find())
            throw new IllegalCommandException("Invalid deadline format");
        String description = m.group(1);
        Date date = new Date(m.group(2));
        return new Deadline(description, date, input);
    }

    /**
     * Parses an input string that creates Event tasks. 
     * @param input User input string that creates Event tasks.
     * @return Event object that was created from the input string.
     * @throws IllegalCommandException if the event format is invalid.
     */
    public static Event parseEvent(String input) throws IllegalCommandException {
        Pattern p = Pattern.compile(event);
        Matcher m = p.matcher(input.strip());
        if (!m.find())
            throw new IllegalCommandException("Invalid event format");
        String description = m.group(1);
        Date from = new Date(m.group(2));
        Date to = new Date(m.group(3));
        return new Event(description, from, to, input);
    }

    /**
     * Parses an input string that marks tasks. 
     * @param input User input string that marks tasks as done.
     * @return int specified by the user.
     * @throws IllegalCommandException if the mark input format is invalid.
     * @throws NoTaskFoundException if the user specifies a task that doesn't exist.
     */
    public static int parseMark(String input) throws IllegalCommandException, NoTaskFoundException {
        Pattern p = Pattern.compile(mark);
        Matcher m = p.matcher(input.strip());
        if (!m.find())
            throw new IllegalCommandException("Invalid mark format");
        try {
            return Integer.parseInt(m.group(1));
        } catch (NumberFormatException e) {
            throw new NoTaskFoundException("Enter a number!!!");
        }

    }

    /**
     * Parses an input string that unmarks tasks. 
     * @param input User input string that marks tasks as not done.
     * @return int specified by the user
     * @throws IllegalCommandException if the unmark input format is invalid.
     * @throws NoTaskFoundException if the user specifies a task that doesn't exist.
     */
    public static int parseUnmark(String input) throws IllegalCommandException, NoTaskFoundException {
        Pattern p = Pattern.compile(unmark);
        Matcher m = p.matcher(input.strip());
        if (!m.find())
            throw new IllegalCommandException("Invalid unmark format");
        try {
            return Integer.parseInt(m.group(1));
        } catch (NumberFormatException e) {
            throw new NoTaskFoundException("Enter a number!!!");
        }
    }

    /**
     * Parses an input string that deletes tasks. 
     * @param input User input string that deletes tasks.
     * @return int specified by the user.
     * @throws IllegalCommandException if the delete input format is invalid.
     * @throws NoTaskFoundException if the user specifies a task that doesn't exist.
     */
    public static int parseDelete(String input) throws IllegalCommandException, NoTaskFoundException {
        Pattern p = Pattern.compile(delete);
        Matcher m = p.matcher(input.strip());
        if (!m.find())
            throw new IllegalCommandException("Invalid delete format");
        try {
            return Integer.parseInt(m.group(1));
        } catch (NumberFormatException e) {
            throw new NoTaskFoundException("Enter a number!!!");
        }
    }

}
