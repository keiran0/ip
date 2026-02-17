package originalnamebot.tasks;

import java.util.ArrayList;
import java.util.List;

import originalnamebot.bot.BotLines;
import originalnamebot.ui.Main;
import originalnamebot.utils.FileManager;

/**
 * Tasklist contains methods to manipulate and display tasks.
 */
public class Tasklist {

    private static List<Task> tasks = new ArrayList<>();

    /**
     * Prints all the tasks in the 'tasks' list.
     */
    public static void listTasks() {
        if (tasks.size() == 0) {
            Main.getMainWindow().sendBotMessage(String.valueOf(BotLines.NO_TASKS));
            return;
        }
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1 + ". " + tasks.get(i).toString() + "\n");
        }
        Main.getMainWindow().sendBotMessage(sb.toString());
    }

    /**
     * Marks a task as done. If the input string specifies a task that does not exist, the method
     * prints the exception.
     *
     * @param i Task number to mark as done (task number is the number that shows up
     *        on the left of the task when the 'list' command is run) to mark as done
     */
    public static void markTask(int i) {
        try {
            Task task = tasks.get(i - 1);
            task.markDone();
            String output = BotLines.TASK_DELETED + "\n" + task;
            FileManager.writeFile(tasks);
            Main.getMainWindow().sendBotMessage(output);
        } catch (IndexOutOfBoundsException e) {
            Main.getMainWindow().sendBotMessage(String.valueOf(BotLines.NO_SUCH_TASK_AT_INDEX));
        }
    }

    /**
     * Marks a task as not done. If the input string specifies a task that does not exist, the
     * method prints the exception.
     *
     * @param i Task number to mark as not done (task number is the number that shows up
     *        on the left of the task when the 'list' command is run) to mark as not done
     */
    public static void unmarkTask(int i) {
        try {
            Task task = tasks.get(i - 1);
            task.markNotDone();
            String output = BotLines.TASK_UNMARKED + "\n" + task;
            FileManager.writeFile(tasks);
            Main.getMainWindow().sendBotMessage(output);
        } catch (IndexOutOfBoundsException e) {
            Main.getMainWindow().sendBotMessage(String.valueOf(BotLines.NO_SUCH_TASK_AT_INDEX));
        }
    }

    /**
     * Adds a new task to the tasklist, prints a confirmation message and the task count, then saves
     * to the txt file.
     *
     * @param task The task to add to the task list.
     */
    public static void addTask(Task task) {

        if (task == null) {
            return;
        }

        tasks.add(task);
        Main.getMainWindow().sendBotMessage("added: " + task.toString() + "\n" + countTasks());
        FileManager.writeFile(tasks);

    }

    /**
     * Deletes the task, prints a confirmation message and the task count, then saves to the txt
     * file. If the input string specifies a task that does not exist, the method prints the
     * exception.
     *
     * @param i Task number to delete (task number is the number that shows up
     *        on the left of the task when the 'list' command is run)
     */
    public static void deleteTask(int i) {
        try {
            Task task = tasks.get(i - 1);
            tasks.remove(i - 1);
            String output = BotLines.TASK_DELETED + "\n" + task.toString() + "\n" + countTasks();
            Main.getMainWindow().sendBotMessage(output);
            FileManager.writeFile(tasks);
        } catch (IndexOutOfBoundsException e) {
            Main.getMainWindow().sendBotMessage(String.valueOf(BotLines.NO_SUCH_TASK_AT_INDEX));
        }
    }

    /**
     * Prints a message that indicates the task count.
     */
    public static String countTasks() {
        if (tasks.size() == 0) {
            return String.valueOf(BotLines.NO_TASKS);
        } else if (tasks.size() == 1) {
            return "You have 1 task in the list. Hurry up and finish it.";
        } else {
            return "You have " + tasks.size() + " tasks in the list. Hurry up and finish them.";
        }

    }

    /**
     * Finds all the tasks that contains the filter param.
     *
     * @param filter String to filter the task's description by.
     */
    public static void findTask(String filter) {

        if (tasks.size() == 0) {
            Main.getMainWindow().sendBotMessage(String.valueOf(BotLines.NO_TASKS));
            return;
        }

        StringBuilder sb = new StringBuilder("Here are the tasks you found:\n");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.find(filter)) {
                sb.append((i + 1) + ". " + task.toString() + "\n");
            }
        }
        Main.getMainWindow().sendBotMessage(sb.toString());
    }

}
