package originalnamebot.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import originalnamebot.exceptions.IllegalCommandException;
import originalnamebot.exceptions.NoTaskFoundException;
import originalnamebot.tasks.Task;
import originalnamebot.tasks.Tasklist;
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
                Main.getMainWindow().sendBotMessage("No save file detected, created new file");
            } else {
                Main.getMainWindow().sendBotMessage("File already exists, reading from file");
                loadFile();
            }
        } catch (IOException e) {
            Main.getMainWindow().sendBotMessage("An error occurred handling files");
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
                writer.write(task.getCreationString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            Main.getMainWindow().sendBotMessage("An error occurred saving to file");
        }
    }

    /**
     * Reads tasks from data/tasks.txt into Tasklist
     */
    public static void loadFile() {
        try {
            Scanner sc = new Scanner(new File(filePath));
            Main.getMainWindow().sendBotMessage("Loading tasks from " + filePath);
            while (sc.hasNextLine()) {
                String input = sc.nextLine();
                try {
                    Main.getMainWindow().setBotSilence(true);
                    Parser.parseCommand(input);
                } catch (IllegalCommandException e) {
                    Main.getMainWindow().sendBotMessage("Wrong command in save file:" + input);
                } catch (NoTaskFoundException e) {
                    Main.getMainWindow().sendBotMessage("No task found!");
                } finally {
                    Main.getMainWindow().setBotSilence(false);
                }
            }
            Main.getMainWindow().setBotSilence(false);
            Tasklist.listTasks();
            sc.close();
        } catch (IOException e) {
            Main.getMainWindow().sendBotMessage("An error occured loading file");
        }
    }
}
