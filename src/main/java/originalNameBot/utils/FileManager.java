package originalNameBot.utils;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import originalNameBot.exceptions.IllegalCommandException;
import originalNameBot.tasks.Task;
import originalNameBot.tasks.Tasklist;

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
                System.out.println("No save file detected, created new file");
            } else {
                System.out.println("File already exists, reading from file");
                loadFile();
            }
        } catch (IOException e) {
            System.out.println("An error occurred handling files");
            e.printStackTrace();
        }
    }

    /**
     * Writes tasks from tasklist into data/tasks.txt
     */
    public static void writeFile(List<Task> ls) {
        try {
            FileWriter writer = new FileWriter(filePath);
            for (Task task : ls) {
                writer.write(task.getCreationString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred saving to file");
            e.printStackTrace();
        }
    }  

    /**
     * Reads tasks from data/tasks.txt into Tasklist
     */
    public static void loadFile() {
        try {
            Scanner sc = new Scanner(new File(filePath));
            System.out.println("Loading tasks from " + filePath);
            while (sc.hasNextLine()) {
                String input = sc.nextLine();
                try {
                    String command = Parser.obtainCommand(input);
                    Tasklist.addTask(input, command);
                } catch (IllegalCommandException e) {
                    System.out.println("Wrong command in save file:" + input);
                    continue;
                }
            }
            sc.close();
        } catch (IOException e) {
            System.out.println("An error occured loading file");
        } 
    }
}
