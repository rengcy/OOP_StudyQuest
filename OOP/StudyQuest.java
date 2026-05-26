import java.util.Scanner;
import java.util.ArrayList;

public class StudyQuest {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("=========================");
        System.out.println("      STUDY QUEST");
        System.out.println("=========================");

        System.out.print("Enter your name: ");
        String name = sc.nextLine();

        User user = new User(name);

        // Load existing player data if it exists
        if (FileManager.playerExists(name)) {
            System.out.println("Welcome back, " + name + "!");
            ArrayList<Task> loadedTasks = FileManager.loadTasks(name);
            ArrayList<Badge> loadedBadges = FileManager.loadBadges(name);
            ArrayList<Category> loadedCategories = FileManager.loadCategories(name);
            int[] stats = FileManager.loadUserStats(name);
            
            user.loadTasks(loadedTasks);
            user.loadBadges(loadedBadges);
            if (loadedCategories.size() > 0) {
                user.loadCategories(loadedCategories);
            }
            user.setStats(stats[0], stats[1]);
        } else {
            System.out.println("New player created: " + name);
        }

        int choice;

        do {

            System.out.println("\n===== MAIN MENU =====");
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Complete Task");
            System.out.println("4. Delete Task");
            System.out.println("5. Show Stats");
            System.out.println("6. Show Badges");
            System.out.println("7. Show Categories");
            System.out.println("8. Create Custom Category");
            System.out.println("9. Delete Category");
            System.out.println("10. Save Progress");
            System.out.println("11. Exit");

            System.out.print("Choose: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                // ADD TASK
                case 1:

                    System.out.print("Enter task title: ");
                    String title = sc.nextLine();

                    if (title.trim().isEmpty()) {
                        System.out.println("Task title cannot be empty!");
                        break;
                    }

                    user.showCategories();

                    System.out.print("Enter category number (0 to cancel): ");
                    int categoryChoice = 0;
                    
                    try {
                        categoryChoice = sc.nextInt();
                        sc.nextLine();
                    } catch (Exception e) {
                        sc.nextLine();
                        System.out.println("Invalid input.");
                        break;
                    }

                    if (categoryChoice == 0) {
                        System.out.println("Task creation cancelled.");
                        break;
                    }

                    ArrayList<Category> cats = user.getCategories();
                    
                    if (categoryChoice < 1 || categoryChoice > cats.size()) {
                        System.out.println("Invalid choice.");
                        break;
                    }

                    Category selectedCat = cats.get(categoryChoice - 1);
                    user.addTask(title, selectedCat.getName(), selectedCat.getXpReward());

                    System.out.println("✓ Task added successfully!");
                    System.out.println("Reward: +" + selectedCat.getXpReward() + " XP");

                    break;

                // VIEW TASKS
                case 2:

                    user.showTasks();

                    break;

                // COMPLETE TASK
                case 3:

                    user.showTasks();

                    System.out.print("Enter task number: ");
                    int taskNum = sc.nextInt();
                    sc.nextLine();

                    user.completeTask(taskNum - 1);

                    break;

                // DELETE TASK
                case 4:

                    user.showTasks();

                    System.out.print("Enter task number to delete (0 to cancel): ");
                    int deleteTaskNum = 0;
                    
                    try {
                        deleteTaskNum = sc.nextInt();
                        sc.nextLine();
                    } catch (Exception e) {
                        sc.nextLine();
                        System.out.println("Invalid input.");
                        break;
                    }

                    if (deleteTaskNum == 0) {
                        System.out.println("Deletion cancelled.");
                        break;
                    }

                    ArrayList<Task> allTasks = user.getTasks();

                    if (deleteTaskNum < 1 || deleteTaskNum > allTasks.size()) {
                        System.out.println("Invalid task number.");
                        break;
                    }

                    String deletedTaskTitle = allTasks.get(deleteTaskNum - 1).getTitle();

                    if (user.deleteTask(deleteTaskNum - 1)) {
                        System.out.println("✓ Task '" + deletedTaskTitle + "' deleted successfully!");
                    } else {
                        System.out.println("Failed to delete task.");
                    }

                    break;

                // SHOW STATS
                case 5:

                    user.showStats();

                    break;

                // SHOW BADGES
                case 6:

                    user.showBadges();

                    break;

                // SHOW CATEGORIES
                case 7:

                    user.showCategories();

                    break;

                // CREATE CUSTOM CATEGORY
                case 8:

                    System.out.print("Enter category name: ");
                    String categoryName = sc.nextLine();

                    if (categoryName.trim().isEmpty()) {
                        System.out.println("Category name cannot be empty!");
                        break;
                    }

                    System.out.println("Select difficulty level:");
                    System.out.println("1. Easy (+20 XP)");
                    System.out.println("2. Medium (+50 XP)");
                    System.out.println("3. Hard (+100 XP)");
                    System.out.println("0. Cancel");

                    System.out.print("Enter difficulty: ");
                    
                    int diffChoice = 0;
                    try {
                        diffChoice = sc.nextInt();
                        sc.nextLine();
                    } catch (Exception e) {
                        sc.nextLine();
                        System.out.println("Invalid input.");
                        break;
                    }

                    String difficulty = "";

                    switch (diffChoice) {
                        case 0:
                            System.out.println("Category creation cancelled.");
                            break;
                        case 1:
                            difficulty = "Easy";
                            break;
                        case 2:
                            difficulty = "Medium";
                            break;
                        case 3:
                            difficulty = "Hard";
                            break;
                        default:
                            System.out.println("Invalid difficulty choice.");
                            break;
                    }

                    if (!difficulty.isEmpty()) {
                        user.addCategory(categoryName, difficulty);
                        System.out.println("✓ Category '" + categoryName + "' (" + difficulty + ") created successfully!");
                    }

                    break;

                // DELETE CATEGORY
                case 9:

                    user.showCategories();

                    System.out.print("Enter category number to delete (0 to cancel): ");
                    int deleteChoice = 0;
                    
                    try {
                        deleteChoice = sc.nextInt();
                        sc.nextLine();
                    } catch (Exception e) {
                        sc.nextLine();
                        System.out.println("Invalid input.");
                        break;
                    }

                    if (deleteChoice == 0) {
                        System.out.println("Deletion cancelled.");
                        break;
                    }

                    ArrayList<Category> allCats = user.getCategories();

                    if (deleteChoice < 1 || deleteChoice > allCats.size()) {
                        System.out.println("Invalid category number.");
                        break;
                    }

                    String deletedCatName = allCats.get(deleteChoice - 1).getName();

                    if (user.deleteCategory(deleteChoice - 1)) {
                        System.out.println("✓ Category '" + deletedCatName + "' deleted successfully!");
                    } else {
                        System.out.println("Failed to delete category.");
                    }

                    break;

                // SAVE PROGRESS
                case 10:

                    FileManager.saveTasks(user.getTasks(), name);
                    FileManager.saveUserStats(name, user.getXP(), user.getLevel());
                    FileManager.saveBadges(user.getBadges(), name);
                    FileManager.saveCategories(user.getCategories(), name);
                    System.out.println("All progress saved successfully!");

                    break;

                // EXIT
                case 11:

                    // Auto-save before exit
                    FileManager.saveTasks(user.getTasks(), name);
                    FileManager.saveUserStats(name, user.getXP(), user.getLevel());
                    FileManager.saveBadges(user.getBadges(), name);
                    FileManager.saveCategories(user.getCategories(), name);

                    System.out.println("Progress saved. Goodbye!");

                    break;

                default:

                    System.out.println("Invalid choice.");
            }

        } while (choice != 11);

        sc.close();
    }
}