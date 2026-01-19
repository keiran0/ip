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
            } else if (input.equals("list")) {
                for (int i = 0; i < numTasks; i++) {
                    System.out.println(i + ". " + tasks.get(i).toString());
                }
            } else {
                Task newTask = new Task(input);
                tasks.add(newTask);
                numTasks++;
                System.out.println("added: " + newTask.toString());
            }
        }

        System.out.println("Whew, finally. Bye.");
        scanner.close();
    }
}
