import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class OriginalNameBot {

    public static List<Task> tasks = new ArrayList<>();
    public static int numTasks = 0;

    public static void main(String[] args) {
        System.out.println("Hello from original name bot! As you can see, this is a very original name. \n");
        System.out.println("What can I do for you?");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                break;
            } 
                
            if (input.equals("list")) {
                for (int i = 0; i < numTasks; i++) {
                    System.out.println(i + 1 + ". " + tasks.get(i).toString());
                }
                continue;
            } 

            String[] splitted = input.split(" ");

            if (splitted[0].equals("mark")) {
                Task current = tasks.get(Integer.parseInt(input.split(" ")[1]) - 1);
                current.markDone();
                System.out.println("Congratulations, you did something you were supposed to do!");
                System.out.println(current.toString());
            } else if (splitted[0].equals("unmark")) {
                Task current = tasks.get(Integer.parseInt(input.split(" ")[1]) - 1);
                current.markNotDone();
                System.out.println("Why?");
            } else if (splitted[0].equals("todo")) {
                addTask(splitted[0], input.replace("todo ", ""));
            } else {
                System.out.println(splitted[0] + ".");
                Task newTask = new Task(input);
                tasks.add(newTask);
                numTasks++;
                System.out.println("added: " + input);
            }
        }

        System.out.println("Whew, finally. Bye.");
        scanner.close();
    }

    private static void addTask(String type, String other) {
        Task current;

        if (type.equals("todo")) {
            current = new Todo(other);
        } else {
            System.out.println("not a todo");
            System.out.println(type);
            current = new Task(other);
        }

        tasks.add(current);
        numTasks++;
        System.out.println("added: " + other);
    }
}
