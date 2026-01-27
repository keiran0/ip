import java.util.Scanner;

public class OriginalNameBot {
    public static void main(String[] args) {
        System.out.println(BotLines.GREETING);
        FileManager.initFile();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();

            if (!Parser.isValid(input)) {
                System.out.println(BotLines.UNKNOWN_COMMAND);
                continue;
            }

            try {
                String command = Parser.obtainCommand(input);
                if (command.equals("bye")) {
                    break;
                } else {
                    Parser.parseTask(input, command);
                }


            } catch (IllegalCommandException e) {
                System.out.println(BotLines.UNKNOWN_COMMAND);
            }

        }

        System.out.println(BotLines.GOODBYE);
        scanner.close();
    }

}
