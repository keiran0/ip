package originalNameBot.bot;

import java.util.Scanner;

import originalNameBot.exceptions.IllegalCommandException;
import originalNameBot.exceptions.NoTaskFoundException;
import originalNameBot.utils.FileManager;
import originalNameBot.utils.Parser;

public class OriginalNameBot {
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
                    Parser.parseCommand(input, command);
                }
            } catch (IllegalCommandException e) {
                System.out.println(BotLines.UNKNOWN_COMMAND + ": " + e.getMessage());
            } catch (NoTaskFoundException e) {
                System.out.println(BotLines.UNKNOWN_COMMAND + ": " + e.getMessage());
            };

        }

        System.out.println(BotLines.GOODBYE);
        scanner.close();
    }

}
