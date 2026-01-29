package originalNameBot.tasks;

import java.util.ArrayList;
import java.util.List;

import originalNameBot.bot.BotLines;
import originalNameBot.exceptions.IllegalCommandException;
import originalNameBot.exceptions.NoTaskFoundException;
import originalNameBot.utils.FileManager;
import originalNameBot.utils.Parser;

public class Tasklist {

    private static List<Task> tasks = new ArrayList<>();

    public static void listTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + ". " + tasks.get(i).toString());
        }
    }

    public static void markTask(String input) {
        try {
            int i = Parser.parseMark(input);
            Task task = tasks.get(i - 1);
            task.markDone();
            System.out.println(BotLines.TASK_DONE);
            System.out.println(task);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(BotLines.NO_SUCH_TASK_AT_INDEX);
        } catch (NoTaskFoundException e2) {
            System.out.println(e2);
        } catch (IllegalCommandException e3) {
            System.out.println(e3);
        }
    }

    public static void unmarkTask(String input) {
        try {
            int i = Parser.parseUnmark(input);
            Task task = tasks.get(i - 1);
            task.markNotDone();
            System.out.println(BotLines.TASK_UNMARKED);

        } catch (IndexOutOfBoundsException e) {
            System.out.println(BotLines.NO_SUCH_TASK_AT_INDEX);
        } catch (NoTaskFoundException e2) {
            System.out.println(e2);
        } catch (IllegalCommandException e3) {
            System.out.println(e3);
        }
    }

    public static void addTask(String input, String command) {

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
            FileManager.writeFile(tasks);
        } catch (IllegalCommandException e) {
            System.out.println(BotLines.BAD_COMMAND_FORMAT);
            System.out.println(e.getMessage());
        }

    }

    public static void deleteTask(String input) {
        try {
            int i = Parser.parseDelete(input);
            Task task = tasks.get(i - 1);
            tasks.remove(i - 1);
            System.out.println(BotLines.TASK_DELETED);
            System.out.println(task);
            countTasks();
            FileManager.writeFile(tasks);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(BotLines.NO_SUCH_TASK_AT_INDEX);
        } catch (NoTaskFoundException e2) {
            System.out.println(e2);
        } catch (IllegalCommandException e3) {
            System.out.println(e3);
        }
    }

    public static void countTasks() {
        if (tasks.size() == 0) {
            System.out.println("You have no tasks");
        } else if (tasks.size() == 1) {
            System.out.println("You have 1 task in the list. Hurry up and finish it.");
        } else {
            System.out.println(
                    "You have " + tasks.size() + " tasks in the list. Hurry up and finish them.");
        }

    }

    /**
     * Finds all the tasks that contains the filter param. 
     * @param filter
     */
    public static void findTask(String filter) {
        System.out.println("Here are the tasks you found:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.find(filter)) {
                System.out.println((i + 1) + ". " + task.toString());
            }
        }
    }

}
