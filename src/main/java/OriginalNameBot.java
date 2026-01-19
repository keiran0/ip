import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class OriginalNameBot {

    public static List<Task> tasks = new ArrayList<>();
    public static int numTasks = 0;
    public static List<String> taskTypes = new ArrayList<>(List.of("todo", "deadline", "event"));

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
                continue;
            }
                
            if (splitted[0].equals("unmark")) {
                Task current = tasks.get(Integer.parseInt(input.split(" ")[1]) - 1);
                current.markNotDone();
                System.out.println("Why?");
                continue;
            }  

            if (taskTypes.contains(splitted[0])) {
                addTask(splitted[0], input.replace(splitted[0] + " ", ""));
            } else {
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
        } else if (type.equals("event")) {
            String[] details = other.split("/");
            String[] strings1 = details[1].split(" ");
            String[] strings2 = details[2].split(" ");
            
            String description = details[0];
            String from = "";
            String to = "";

            if (strings1[0].equals("from") && strings2[0].equals("to")) {
                from = details[1].replace("from ", "");
                to = details[2].replace("to ", "");                
            } else if (strings2[0].equals("to") && strings1[0].equals("from")) {
                from = details[2].replace("from ", "");
                to = details[1].replace("to ", "");  
            } else {
                System.out.println("Invalid flags");
                return;
            }

            current = new Event(description, from, to);

        } else if (type.equals("deadline")) {
            // deadline do homework /by no idea :-p
            // details1 = by no idea :-p
            String[] details = other.split("/");
            String description = details[0];
            String deadline = "";
            if (details[1].split(" ")[0].equals("by")) {
                deadline = details[1].replace("by ", "");
            }
            current = new Deadline(description, deadline);
            
        } else {
            current = new Task(other);
        }

        tasks.add(current);
        numTasks++;
        System.out.println("added: " + other);
    }
}
