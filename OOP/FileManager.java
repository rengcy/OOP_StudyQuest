import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {

    // SAVE TASKS WITH BETTER DELIMITER (|)
    public static void saveTasks(ArrayList<Task> tasks, String playerName) {

        try {
            PrintWriter writer = new PrintWriter(playerName + "_tasks.txt");

            for (Task t : tasks) {
                writer.println(t.getTitle() + "|" + t.getCategory() + "|" + t.getXP() + "|" + t.isDone());
            }

            writer.close();
            System.out.println("Tasks saved successfully.");

        } catch (Exception e) {
            System.out.println("Error saving tasks.");
        }
    }

    // SAVE BADGES
    public static void saveBadges(ArrayList<Badge> badges, String playerName) {

        try {
            PrintWriter writer = new PrintWriter(playerName + "_badges.txt");

            for (Badge b : badges) {
                writer.println(b.getName());
            }

            writer.close();

        } catch (Exception e) {
            System.out.println("Error saving badges.");
        }
    }

    // SAVE USER STATS
    public static void saveUserStats(String playerName, int xp, int level) {

        try {
            PrintWriter writer = new PrintWriter(playerName + "_stats.txt");

            writer.println(xp + "|" + level);

            writer.close();

        } catch (Exception e) {
            System.out.println("Error saving stats.");
        }
    }

    // SAVE CUSTOM CATEGORIES
    public static void saveCategories(ArrayList<Category> categories, String playerName) {

        try {
            PrintWriter writer = new PrintWriter(playerName + "_categories.txt");

            for (Category c : categories) {
                writer.println(c.toFileString());
            }

            writer.close();

        } catch (Exception e) {
            System.out.println("Error saving categories.");
        }
    }

    // LOAD TASKS WITH BETTER PARSING
    public static ArrayList<Task> loadTasks(String playerName) {

        ArrayList<Task> tasks = new ArrayList<>();

        try {
            File file = new File(playerName + "_tasks.txt");

            if (!file.exists()) {
                return tasks;
            }

            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()) {

                String line = reader.nextLine();

                if (!line.trim().isEmpty()) {
                    String[] parts = line.split("\\|");

                    if (parts.length >= 4) {
                        String title = parts[0];
                        String category = parts[1];
                        int xp = Integer.parseInt(parts[2]);
                        boolean isDone = Boolean.parseBoolean(parts[3]);

                        Task t = new Task(title, category, xp);
                        if (isDone) {
                            t.markDone();
                        }

                        tasks.add(t);
                    }
                }
            }

            reader.close();

        } catch (Exception e) {
            System.out.println("Error loading tasks.");
        }

        return tasks;
    }

    // LOAD BADGES
    public static ArrayList<Badge> loadBadges(String playerName) {

        ArrayList<Badge> badges = new ArrayList<>();

        try {
            File file = new File(playerName + "_badges.txt");

            if (!file.exists()) {
                return badges;
            }

            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()) {

                String line = reader.nextLine();

                if (!line.trim().isEmpty()) {
                    badges.add(new Badge(line.trim()));
                }
            }

            reader.close();

        } catch (Exception e) {
            System.out.println("Error loading badges.");
        }

        return badges;
    }

    // LOAD USER STATS
    public static int[] loadUserStats(String playerName) {

        int[] stats = {0, 0}; // {xp, level}

        try {
            File file = new File(playerName + "_stats.txt");

            if (!file.exists()) {
                return stats;
            }

            Scanner reader = new Scanner(file);

            if (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] parts = line.split("\\|");

                if (parts.length >= 2) {
                    stats[0] = Integer.parseInt(parts[0]); // xp
                    stats[1] = Integer.parseInt(parts[1]); // level
                }
            }

            reader.close();

        } catch (Exception e) {
            System.out.println("Error loading stats.");
        }

        return stats;
    }

    // LOAD CUSTOM CATEGORIES
    public static ArrayList<Category> loadCategories(String playerName) {

        ArrayList<Category> categories = new ArrayList<>();

        try {
            File file = new File(playerName + "_categories.txt");

            if (!file.exists()) {
                return categories;
            }

            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()) {

                String line = reader.nextLine();

                if (!line.trim().isEmpty()) {
                    String[] parts = line.split("\\|");

                    if (parts.length >= 2) {
                        String name = parts[0];
                        String difficulty = parts[1];

                        categories.add(new Category(name, difficulty));
                    }
                }
            }

            reader.close();

        } catch (Exception e) {
            System.out.println("Error loading categories.");
        }

        return categories;
    }

    // CHECK IF PLAYER EXISTS
    public static boolean playerExists(String playerName) {
        return new File(playerName + "_tasks.txt").exists();
    }
}
