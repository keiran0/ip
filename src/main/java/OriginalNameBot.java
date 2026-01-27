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
                }

                if (command.equals("list")) {
                    Tasklist.listTasks();
                    continue;
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

            } catch (IllegalCommandException e) {
                System.out.println(BotLines.UNKNOWN_COMMAND);
            }

        }

        System.out.println(BotLines.GOODBYE);
        scanner.close();
    }

}
