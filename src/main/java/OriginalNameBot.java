import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class OriginalNameBot {

    public static List<Task> tasks = new ArrayList<>();
    public static List<String> taskTypes = new ArrayList<>(List.of("todo", "deadline", "event"));

    public static void main(String[] args) {
        System.out.println("Hello from original name bot! As you can see, this is a very original name. \n");
        System.out.println("What can I do for you?");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();

            if (!Parser.isValid(input)) {
                System.out.println("Invalid command");
                continue;
            }

            try {
                String command = Parser.obtainCommand(input);
                if (command.equals("bye")) {
                    break;
                }

                if (command.equals("list")) {
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println(i + 1 + ". " + tasks.get(i).toString());
                    }
                    continue;
                }

                if (command.equals("todo") || command.equals("deadline") || command.equals("event")) {
                    addTask(input, command);
                }

                if (command.equals("mark")) {
                    try {
                        int i = Parser.parseMark(input);
                        Task task = tasks.get(i - 1);
                        task.markDone();
                        System.out.println("Congratulations, you did something you were supposed to do!");
                        System.out.println(task);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("You don't have that many tasks!");
                    } catch (NoTaskFoundException e2) {
                        System.out.println(e2);
                    }
                    
                }

                if (command.equals("unmark")) {
                    try {
                        int i = Parser.parseUnmark(input);
                        Task task = tasks.get(i - 1);
                        task.markNotDone();
                        System.out.println("Why?");

                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("You don't have that many tasks!");
                    } catch (NoTaskFoundException e2) {
                        System.out.println(e2);
                    }
                }

                if (command.equals("delete")) {
                    try {
                        int i = Parser.parseDelete(input);
                        Task task = tasks.get(i - 1);
                        tasks.remove(i - 1);
                        System.out.println("Good, please keep doing this so I don't have to remember so many things. Removed:");
                        System.out.println(task);
                        countTasks();
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("You don't have that many tasks!");
                    } catch (NoTaskFoundException e2) {
                        System.out.println(e2);
                    }
                }

            } catch (IllegalCommandException e) {
                System.out.println("I don't understand this!");
            }

        }

        System.out.println("Whew, finally. Bye.");
        scanner.close();
    }

    private static void addTask(String input, String command) {

        Task newTask = null;

        try {
            if (command.equals("todo")) {
                newTask = Parser.parseTodo(input);
            }

            if (command.equals("deadline")) {
                newTask = Parser.parseDeadline(input);
            }

            if (command.equals("event")) {
                newTask = Parser.parseEvent(input);
            }
            tasks.add(newTask);
            System.out.println("added: " + input.replace(command + " ", ""));
            countTasks();
        } catch (IllegalCommandException e) {
            System.out.println("Invalid format for task type!");

        }

    }

    public static void countTasks() {
        if (tasks.size() == 0) {
            System.out.println("You have no tasks");
        } else if (tasks.size() == 1) {
            System.out.println("You have 1 task in the list. Hurry up and finish it.");
        } else {
            System.out.println("You have " +  tasks.size() + " tasks in the list. Hurry up and finish them.");
        }
        
    }
}
