package originalnamebot.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import originalnamebot.bot.BotLines;
import originalnamebot.exceptions.IllegalCommandException;
import originalnamebot.exceptions.NoTaskFoundException;
import originalnamebot.tasks.Deadline;
import originalnamebot.tasks.Event;
import originalnamebot.tasks.Task;
import originalnamebot.tasks.Tasklist;
import originalnamebot.tasks.Todo;
import originalnamebot.ui.Main;

/**
 * Parser contains methods to parse different user commands. It also calls functions from other
 * classes to execute the command if the command is valid.
 */
public class Parser {

    private static final String VALID_COMMANDS =
            "^(list|todo|deadline|event|mark|unmark|bye|delete|find).*";
    private static final String TODO = "todo *(\\S+.*)";
    private static final String EVENT = "event *(\\S+.*)/from (.*) /to (.*)";
    private static final String DEADLINE = "deadline *(\\S+.*) /by (.*)";
    private static final String MARK = "^mark *(\\d*)";
    private static final String UNMARK = "^unmark *(\\d*)";
    private static final String DELETE = "^delete *(\\d*)";
    private static final String FIND = "^find *(\\S+.*)";

    /**
     * Returns true if the input starts with list/todo/deadline/event/mark/unmark/bye/delete.
     *
     * @param input User input
     */
    public static boolean isValid(String input) {
        return Pattern.matches(VALID_COMMANDS, input.trim());
    }

    /**
     * Parses an input command and executes the command's respective actions.
     *
     * @param input Input command from user.
     * @throws IllegalCommandException when command is wrong.
     * @throws NoTaskFoundException when command is correct, but the task attempted to be found does
     *         not exist.
     */
    public static void parseCommand(String input)
            throws IllegalCommandException, NoTaskFoundException {

        if (!isValid(input)) {
            Main.getMainWindow().sendBotMessage(String.valueOf(BotLines.UNKNOWN_COMMAND));
            return;
        }

        assert isValid(input);

        String command = obtainCommand(input);

        Task newTask;

        switch (command) {
        case "list":
            Tasklist.listTasks();
            break;
        case "todo":
            newTask = parseTodo(input);
            Tasklist.addTask(newTask);
            break;
        case "deadline":
            newTask = parseDeadline(input);
            Tasklist.addTask(newTask);
            break;
        case "event":
            newTask = parseEvent(input);
            Tasklist.addTask(newTask);
            break;
        case "mark":
            Tasklist.markTask(parseMark(input));
            break;
        case "unmark":
            Tasklist.unmarkTask(parseUnmark(input));
            break;
        case "find":
            Tasklist.findTask(parseFind(input));
            break;
        case "delete":
            Tasklist.deleteTask(parseDelete(input));
            break;
        default:
            break;
        }
    }

    /**
     * Obtains the command from the input.
     *
     * @param input User input
     * @return command type from the user input.
     * @throws IllegalCommandException if command type is invalid.
     */
    public static String obtainCommand(String input) throws IllegalCommandException {
        Pattern p = Pattern.compile(VALID_COMMANDS);
        Matcher m = p.matcher(input.strip());

        if (!m.find()) {
            throw new IllegalCommandException("Invalid command, valid commands include mark, "
                    + "unmark, todo, event, deadline, delete, list, find and bye.");
        }

        assert isValid(input);

        return m.group(1);
    }

    /**
     * Parses a todo command.
     *
     * @param input User input string.
     * @return Todo created from the user input.
     * @throws IllegalCommandException if format of the user input is invalid.
     */
    public static Todo parseTodo(String input) throws IllegalCommandException {
        assert isValid(input);
        Pattern p = Pattern.compile(TODO);
        Matcher m = p.matcher(input.strip());
        if (!m.find()) {
            throw new IllegalCommandException("Invalid todo format, format is 'todo description'");
        }
        String description = m.group(1);
        return new Todo(description, false);
    }

    /**
     * Parses a deadline command.
     *
     * @param input User input string.
     * @return Deadline created from the user input.
     * @throws IllegalCommandException if format of the user input is invalid.
     */
    public static Deadline parseDeadline(String input) throws IllegalCommandException {
        assert isValid(input);
        Pattern p = Pattern.compile(DEADLINE);
        Matcher m = p.matcher(input.strip());
        if (!m.find()) {
            throw new IllegalCommandException("Invalid deadline format, "
                    + "format is 'deadline description /by date \n" + BotLines.DATE_FORMAT_INFORMATION);
        }
        String description = m.group(1);
        Date date = new Date(m.group(2));
        return new Deadline(description, date, false);
    }

    /**
     * Parses an event command.
     *
     * @param input User input string.
     * @return Event created from the user input.
     * @throws IllegalCommandException if format of the user input is invalid.
     */
    public static Event parseEvent(String input) throws IllegalCommandException {
        assert isValid(input);
        Pattern p = Pattern.compile(EVENT);
        Matcher m = p.matcher(input.strip());
        if (!m.find()) {
            throw new IllegalCommandException("Invalid event format, "
                    + "format is 'event description /from date /to date' \n" + BotLines.DATE_FORMAT_INFORMATION);
        }
        String description = m.group(1);
        Date from = new Date(m.group(2));
        Date to = new Date(m.group(3));
        return new Event(description, from, to, false);
    }

    /**
     * Parses an input string that marks tasks.
     *
     * @param input User input string that marks tasks as done.
     * @return int specified by the user
     * @throws IllegalCommandException if the mark input format is invalid.
     * @throws NoTaskFoundException if the user specifies a task that doesn't exist.
     */
    public static int parseMark(String input) throws IllegalCommandException, NoTaskFoundException {
        assert isValid(input);
        Pattern p = Pattern.compile(MARK);
        Matcher m = p.matcher(input.strip());
        if (!m.find()) {
            throw new IllegalCommandException("Invalid mark format, format is 'mark number'");
        }
        try {
            return Integer.parseInt(m.group(1));
        } catch (NumberFormatException e) {
            throw new NoTaskFoundException("Enter a number!!!");
        }
    }

    /**
     * Parses an input string that unmarks tasks.
     *
     * @param input User input string that marks tasks as not done.
     * @return int specified by the user
     * @throws IllegalCommandException if the unmark input format is invalid.
     * @throws NoTaskFoundException if the user specifies a task that doesn't exist.
     */
    public static int parseUnmark(String input)
            throws IllegalCommandException, NoTaskFoundException {
        assert isValid(input);
        Pattern p = Pattern.compile(UNMARK);
        Matcher m = p.matcher(input.strip());
        if (!m.find()) {
            throw new IllegalCommandException("Invalid unmark format, format is 'unmark number'");
        }
        try {
            return Integer.parseInt(m.group(1));
        } catch (NumberFormatException e) {
            throw new NoTaskFoundException("Enter a number!!!");
        }
    }

    /**
     * Parses an input string that deletes tasks.
     *
     * @param input User input string that deletes tasks.
     * @return int specified by the user.
     * @throws IllegalCommandException if the delete input format is invalid.
     * @throws NoTaskFoundException if the user specifies a task that doesn't exist.
     */
    public static int parseDelete(String input)
            throws IllegalCommandException, NoTaskFoundException {
        assert isValid(input);
        Pattern p = Pattern.compile(DELETE);
        Matcher m = p.matcher(input.strip());
        if (!m.find()) {
            throw new IllegalCommandException("Invalid delete format, format is 'delete number'");
        }
        try {
            return Integer.parseInt(m.group(1));
        } catch (NumberFormatException e) {
            throw new NoTaskFoundException("Enter a number!!!");
        }
    }

    /**
     * Parses an input string that finds tasks.
     *
     * @param input User input string that finds tasks.
     * @return String that the tasks will be filtered with.
     * @throws IllegalCommandException if the delete input format is invalid.
     */
    public static String parseFind(String input) throws IllegalCommandException {
        assert isValid(input);
        Pattern p = Pattern.compile(FIND);
        Matcher m = p.matcher(input.strip());
        if (!m.find()) {
            throw new IllegalCommandException("Invalid find format, format is 'find description_partial_match'");
        } else {
            return m.group(1);
        }
    }

}
