package originalnamebot.bot;

import java.util.Scanner;

import originalnamebot.exceptions.IllegalCommandException;
import originalnamebot.exceptions.NoTaskFoundException;
import originalnamebot.utils.FileManager;
import originalnamebot.utils.Parser;

public class OriginalNameBot {

    /**
     * Runs the application until the 'bye' command is input.
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(BotLines.GREETING);
        FileManager.initFile();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();

            try {
                String command = Parser.obtainCommand(input);
                if (command.equals("bye")) {
                    break;
                } else {
                    Parser.parseCommand(input);
                }
            } catch (IllegalCommandException e) {
                System.out.println(BotLines.UNKNOWN_COMMAND + ": " + e.getMessage());
            } catch (NoTaskFoundException e) {
                System.out.println(BotLines.UNKNOWN_COMMAND + ": " + e.getMessage());
            }

        }

        System.out.println(BotLines.GOODBYE);
        scanner.close();
    }

}
