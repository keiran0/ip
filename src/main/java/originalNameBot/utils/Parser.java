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

    public static boolean isValid(String input) {
        return Pattern.matches(validCommands, input);
        // can throw invalidcommandexception here?
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
        Pattern p = Pattern.compile(validCommands);
        Matcher m = p.matcher(input.strip());

        if (!m.find())
            throw new IllegalCommandException("Invalid command");

        return m.group(1);
    }

    public static Todo parseTodo(String input) throws IllegalCommandException {
        Pattern p = Pattern.compile(todo);
        Matcher m = p.matcher(input.strip());
        if (!m.find())
            throw new IllegalCommandException("Invalid todo format");
        String description = m.group(1);
        return new Todo(description, input);
    }

    public static Deadline parseDeadline(String input) throws IllegalCommandException {
        Pattern p = Pattern.compile(deadline);
        Matcher m = p.matcher(input.strip());
        if (!m.find())
            throw new IllegalCommandException("Invalid deadline format");
        String description = m.group(1);
        Date date = new Date(m.group(2));
        return new Deadline(description, date, input);
    }

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
