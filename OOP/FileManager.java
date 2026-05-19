import java.io.*;
import java.util.ArrayList;

public class FileManager {

    public static void saveTasks(ArrayList<Task> tasks) {

        try {
            PrintWriter writer = new PrintWriter("tasks.txt");

            for (Task t : tasks) {
                writer.println(t.toFileString());
            }

            writer.close();
            System.out.println("Tasks saved successfully.");

        } catch (Exception e) {
            System.out.println("Error saving file.");
        }
    }
}