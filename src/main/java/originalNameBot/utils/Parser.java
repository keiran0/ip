package originalNameBot.utils;

import java.util.regex.*;

import originalNameBot.exceptions.IllegalCommandException;
import originalNameBot.exceptions.NoTaskFoundException;
import originalNameBot.tasks.Deadline;
import originalNameBot.tasks.Event;
import originalNameBot.tasks.Tasklist;
import originalNameBot.tasks.Todo;

public class Parser {

    private static String VALID_COMMANDS = "^(list|todo|deadline|event|mark|unmark|bye|delete|find).*";
    private static String TODO = "todo *(\\S+.*)";
    private static String EVENT = "event *(\\S+.*)/from (.*) /to (.*)";
    private static String DEADLINE = "deadline *(\\S+.*) /by (.*)";
    private static String MARK = "^mark *(\\d*)";
    private static String UNMARK = "^unmark *(\\d*)";
    private static String DELETE = "^delete *(\\d*)";
    private static String FIND = "^find *(\\S+.*)";

    /**
     * Returns true if the input starts with list/todo/deadline/event/mark/unmark/bye/delete.
     * @param input User input
     */
    public static boolean isValid(String input) {
        return Pattern.matches(VALID_COMMANDS, input);
    }

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

    public static String obtainCommand(String input) throws IllegalCommandException {
        Pattern p = Pattern.compile(VALID_COMMANDS);
        Matcher m = p.matcher(input.strip());

        if (!m.find())
            throw new IllegalCommandException("Invalid command");

        return m.group(1);
    }

    public static Todo parseTodo(String input) throws IllegalCommandException {
        Pattern p = Pattern.compile(TODO);
        Matcher m = p.matcher(input.strip());
        if (!m.find())
            throw new IllegalCommandException("Invalid todo format");
        String description = m.group(1);
        return new Todo(description, input);
    }

    public static Deadline parseDeadline(String input) throws IllegalCommandException {
        Pattern p = Pattern.compile(DEADLINE);
        Matcher m = p.matcher(input.strip());
        if (!m.find())
            throw new IllegalCommandException("Invalid deadline format");
        String description = m.group(1);
        Date date = new Date(m.group(2));
        return new Deadline(description, date, input);
    }

    public static Event parseEvent(String input) throws IllegalCommandException {
        Pattern p = Pattern.compile(EVENT);
        Matcher m = p.matcher(input.strip());
        if (!m.find())
            throw new IllegalCommandException("Invalid event format");
        String description = m.group(1);
        Date from = new Date(m.group(2));
        Date to = new Date(m.group(3));
        return new Event(description, from, to, input);
    }

    public static int parseMark(String input) throws IllegalCommandException, NoTaskFoundException {
        Pattern p = Pattern.compile(MARK);
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
    public static int parseUnmark(String input)
            throws IllegalCommandException, NoTaskFoundException {
        Pattern p = Pattern.compile(UNMARK);
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
    public static int parseDelete(String input)
            throws IllegalCommandException, NoTaskFoundException {
        Pattern p = Pattern.compile(DELETE);
        Matcher m = p.matcher(input.strip());
        if (!m.find())
            throw new IllegalCommandException("Invalid delete format");
        try {
            return Integer.parseInt(m.group(1));
        } catch (NumberFormatException e) {
            throw new NoTaskFoundException("Enter a number!!!");
        }
    }

    public static String parseFind(String input) throws IllegalCommandException {
        Pattern p = Pattern.compile(FIND);
        Matcher m = p.matcher(input.strip());
        if (!m.find()) {
            throw new IllegalCommandException("Invalid find format");
        } else {
            return m.group(1);
        }
    }

}
