import java.util.regex.*;

public class Parser {
    
    private static String validCommands = "^(list|todo|deadline|event|mark|unmark|bye).*";
    private static String todo = "todo (\\S+.*)";
    private static String event = "event (\\S+.*)/from (\\S+.*)/to (\\S+.*)";
    private static String deadline = "deadline (\\S+.*) /by (\\S+.*)";
    private static String mark = "^mark (\\d*)";
    private static String unmark = "^unmark (\\d*)";

    public static boolean isValid(String input) {
        return Pattern.matches(validCommands, input);
        // can throw invalidcommandexception here?
    }

    public static String obtainCommand(String input) throws IllegalCommandException {
        Pattern p = Pattern.compile(validCommands);
        Matcher m = p.matcher(input.strip());

        if (!m.find()) throw new IllegalCommandException("Invalid command");

        return m.group(1);
    }

    public static Todo parseTodo(String input) throws IllegalCommandException {
        Pattern p = Pattern.compile(todo);
        Matcher m = p.matcher(input.strip());
        if (!m.find()) throw new IllegalCommandException("Invalid todo format");
        String description = m.group(1);
        return new Todo(description);
    }

    public static Deadline parseDeadline(String input) throws IllegalCommandException {
        Pattern p = Pattern.compile(deadline);
        Matcher m = p.matcher(input.strip());
        if (!m.find()) throw new IllegalCommandException("Invalid deadline format");
        String description = m.group(1);
        String by = m.group(2);
        return new Deadline(description, by);
    }

    public static Event parseEvent(String input) throws IllegalCommandException {
        Pattern p = Pattern.compile(event);
        Matcher m = p.matcher(input.strip());
        if (!m.find()) throw new IllegalCommandException("Invalid event format");
        String description = m.group(1);
        String from = m.group(2);
        String to = m.group(3);
        return new Event(description, from, to);
    }

    public static int parseMark(String input) throws IllegalCommandException, NoTaskFoundException {
        Pattern p = Pattern.compile(mark);
        Matcher m = p.matcher(input.strip());
        if (!m.find()) throw new IllegalCommandException("Invalid mark format");
        try {
            return Integer.parseInt(m.group(1));
        } catch (NumberFormatException e) {
            throw new NoTaskFoundException("Enter a number!!!");
        }
        
    }

    public static int parseUnmark(String input) throws IllegalCommandException, NoTaskFoundException{
        Pattern p = Pattern.compile(unmark);
        Matcher m = p.matcher(input.strip());
        if (!m.find()) throw new IllegalCommandException("Invalid unmark format");
        try {
            return Integer.parseInt(m.group(1));
        } catch (NumberFormatException e) {
            throw new NoTaskFoundException("Enter a number!!!");
        }
    }


}
