package originalnamebot.bot;

import originalnamebot.exceptions.IllegalCommandException;
import originalnamebot.exceptions.NoTaskFoundException;
import originalnamebot.ui.Main;
import originalnamebot.utils.FileManager;
import originalnamebot.utils.Parser;

/**
 * The entry point of the application. It takes in user input, parses the command and controls tasks.
 */
public class OriginalNameBot {

    /**
     * Initializes the application
     */
    public static void init() {
        Main.sendBotMessage(String.valueOf(BotLines.GREETING));
        FileManager.initFile();
    }

    /**
     * Handles command input from the user.
     * @param input User input.
     */
    public static void enterCommand(String input) {
        try {
            String command = Parser.obtainCommand(input);
            if (command.equals("bye")) {
                Main.exit();
            } else {
                Parser.parseCommand(input);
            }
        } catch (IllegalCommandException | NoTaskFoundException e) {
            Main.sendBotMessage(BotLines.UNKNOWN_COMMAND + ": " + e.getMessage());
        }
    }
}
