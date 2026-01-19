import java.util.Scanner;

public class OriginalNameBot {
    public static void main(String[] args) {
        System.out.println("Hello from original name bot! As you can see, this is a very original name. \n");
        System.out.println("What can I do for you?");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) break;
            System.out.println(input);
        }

        System.out.println("Whew, finally. Bye.");
        scanner.close();
    }
}
