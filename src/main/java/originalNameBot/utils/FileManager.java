package originalnamebot.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import originalnamebot.bot.BotLines;
import originalnamebot.exceptions.IllegalCommandException;
import originalnamebot.tasks.Deadline;
import originalnamebot.tasks.Event;
import originalnamebot.tasks.Task;
import originalnamebot.tasks.Tasklist;
import originalnamebot.tasks.Todo;
import originalnamebot.ui.Main;

/**
 * FileManager handles reading and writing to the file in /data. It contains a function `initFile`
 * to initialize the directory and create the file if it doesn't exist, and functions to read and
 * write to the file.
 */
public class FileManager {

    private static String dir = "data";
    private static String file = "tasks.txt";
    private static String filePath = String.join("/", dir, file);

    /**
     * Creates /data and /data/tasks.txt if they do not exist. If they do, call loadFile
     */
    public static void initFile() {
        try {
            new File(dir).mkdirs();
            File file = new File(filePath);
            if (file.createNewFile()) {
                Main.getMainWindow().sendBotMessage(String.valueOf(BotLines.NO_SAVE_FILE));
            } else {
                loadFile();
            }
        } catch (IOException e) {
            Main.getMainWindow().sendBotMessage(String.valueOf(BotLines.ERROR_FILE_HANDLING));
        }
    }

    /**
     * Writes tasks from tasklist into data/tasks.txt
     *
     * @param ls Tasklist
     */
    public static void writeFile(List<Task> ls) {
        try {
            FileWriter writer = new FileWriter(filePath);
            for (Task task : ls) {

                StringBuilder sb = new StringBuilder("");

                sb.append(task.getFileString() + "\n");

                writer.write(sb.toString());
            }
            writer.close();
        } catch (IOException e) {
            Main.getMainWindow().sendBotMessage(String.valueOf(BotLines.ERROR_SAVING));
        }
    }

    /**
     * Reads tasks from data/tasks.txt into Tasklist
     */
    public static void loadFile() {
        try {
            Scanner sc = new Scanner(new File(filePath));
            Main.getMainWindow().sendBotMessage(BotLines.LOADING_TASKS + filePath);
            Main.getMainWindow().setBotSilence(true);
            while (sc.hasNextLine()) {
                String input = sc.nextLine();
                Tasklist.addTask(parseLine(input));
            }
            Main.getMainWindow().setBotSilence(false);
            Tasklist.listTasks();
            sc.close();
        } catch (IOException e) {
            Main.getMainWindow().sendBotMessage(String.valueOf(BotLines.ERROR_LOADING));
        }
    }

    /**
     * Helper method used to parse a single line in the tasks.txt file.
     * @return Task
     */
    private static Task parseLine(String line) {
        String[] split = line.split("\\|");
        String description = split[1];

        boolean isDone = false;

        if (split[2].equals("T")) {
            isDone = true;
        }

        if (split[0].equals("T")) {
            return new Todo(description, isDone);
        }

        if (split[0].equals("E")) {

            try {
                Date from = new Date(split[3]);
                Date to = new Date(split[4]);
                return new Event(description, from, to, isDone);
            } catch (IllegalCommandException e) {
                Main.getMainWindow().sendBotMessage("Unable to load task due to " + e.getMessage());
            }

        }

        if (split[0].equals("D")) {
            try {
                Date by = new Date(split[3]);
                return new Deadline(description, by, isDone);
            } catch (IllegalCommandException e) {
                Main.getMainWindow().sendBotMessage("Unable to load task due to " + e.getMessage());

            }
        }

        return null;

    }
}
