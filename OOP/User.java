import java.util.ArrayList;

public class User {

    private String name;
    private int xp;
    private int level;

    private ArrayList<Task> tasks;
    private ArrayList<Badge> badges;
    private ArrayList<Category> categories;

    // CONSTRUCTOR
    public User(String name) {

        this.name = name;

        xp = 0;
        level = 0;

        tasks = new ArrayList<>();
        badges = new ArrayList<>();
        categories = new ArrayList<>();

        // Add default categories
        categories.add(new Category("School", "Medium"));
        categories.add(new Category("Personal", "Easy"));
        categories.add(new Category("Fitness", "Medium"));
        categories.add(new Category("Review", "Medium"));
        categories.add(new Category("Assignment", "Hard"));
    }

    // ADD TASK
    public void addTask(String title, String category, int xpReward) {

        tasks.add(new Task(title, category, xpReward));
    }

    // DELETE TASK
    public boolean deleteTask(int index) {

        if (index < 0 || index >= tasks.size()) {
            return false;
        }

        tasks.remove(index);
        return true;
    }

    // SHOW TASKS
    public void showTasks() {

        if (tasks.isEmpty()) {

            System.out.println("No tasks available.");
            return;
        }

        System.out.println("\n===== TASK LIST =====");

        for (int i = 0; i < tasks.size(); i++) {

            Task t = tasks.get(i);

            System.out.print((i + 1) + ". ");
            System.out.print(t.getTitle());
            System.out.print(" | Category: " + t.getCategory());

            if (t.isDone()) {

                System.out.print(" [DONE]");
            }

            System.out.println();
        }
    }

    // COMPLETE TASK
    public void completeTask(int index) {

        if (index < 0 || index >= tasks.size()) {

            System.out.println("Invalid task number.");
            return;
        }

        Task t = tasks.get(index);

        if (!t.isDone()) {

            t.markDone();

            xp += t.getXP();

            System.out.println("Task completed!");
            System.out.println("+" + t.getXP() + " XP");

            checkLevelUp();
            checkBadges();

        } else {

            System.out.println("Task already completed.");
        }
    }

    // LEVEL SYSTEM
    private void checkLevelUp() {

        int newLevel = xp / 100;

        if (newLevel > level) {

            level = newLevel;

            System.out.println("================================");
            System.out.println("LEVEL UP!");
            System.out.println("You are now Level " + level);
            System.out.println("================================");
        }
    }

    // BADGE SYSTEM
    private void checkBadges() {

        int completedCount = 0;

        for (Task t : tasks) {

            if (t.isDone()) {

                completedCount++;
            }
        }

        // FIRST STEP
        if (completedCount >= 1 && !hasBadge("First Step")) {

            badges.add(new Badge("First Step"));

            System.out.println("NEW BADGE UNLOCKED: First Step");
        }

        // TASK BEGINNER
        if (completedCount >= 5 && !hasBadge("Task Beginner")) {

            badges.add(new Badge("Task Beginner"));

            System.out.println("NEW BADGE UNLOCKED: Task Beginner");
        }

        // FOCUSED STUDENT
        if (completedCount >= 10 && !hasBadge("Focused Student")) {

            badges.add(new Badge("Focused Student"));

            System.out.println("NEW BADGE UNLOCKED: Focused Student");
        }

        // STUDY MASTER
        if (completedCount >= 20 && !hasBadge("Study Master")) {

            badges.add(new Badge("Study Master"));

            System.out.println("NEW BADGE UNLOCKED: Study Master");
        }
    }

    // CHECK IF USER HAS BADGE
    private boolean hasBadge(String badgeName) {

        for (Badge b : badges) {

            if (b.getName().equals(badgeName)) {

                return true;
            }
        }

        return false;
    }

    // SHOW BADGES
    public void showBadges() {

        if (badges.isEmpty()) {

            System.out.println("No badges yet.");
            return;
        }

        System.out.println("\n===== BADGES =====");

        for (Badge b : badges) {

            System.out.println("- " + b.getName());
        }
    }

    // SHOW STATS
    public void showStats() {

        System.out.println("\n===== PLAYER STATS =====");
        System.out.println("Name: " + name);
        System.out.println("XP: " + xp);
        System.out.println("Level: " + level);
        System.out.println("Progress to next level: " + (xp % 100) + "/100");
    }

    // GET TASKS
    public ArrayList<Task> getTasks() {

        return tasks;
    }

    // LOAD TASKS
    public void loadTasks(ArrayList<Task> loadedTasks) {

        this.tasks = loadedTasks;
    }

    // GET XP
    public int getXP() {
        return xp;
    }

    // GET LEVEL
    public int getLevel() {
        return level;
    }

    // SET XP AND LEVEL (WITHOUT RECALCULATING BADGES)
    public void setStats(int xp, int level) {
        this.xp = xp;
        this.level = level;
    }

    // GET BADGES
    public ArrayList<Badge> getBadges() {
        return badges;
    }

    // LOAD BADGES
    public void loadBadges(ArrayList<Badge> loadedBadges) {
        this.badges = loadedBadges;
    }

    // GET CATEGORIES
    public ArrayList<Category> getCategories() {
        return categories;
    }

    // LOAD CATEGORIES
    public void loadCategories(ArrayList<Category> loadedCategories) {
        this.categories = loadedCategories;
    }

    // ADD CUSTOM CATEGORY
    public void addCategory(String name, String difficulty) {
        categories.add(new Category(name, difficulty));
    }

    // DELETE CATEGORY
    public boolean deleteCategory(int index) {

        if (index < 0 || index >= categories.size()) {
            return false;
        }

        categories.remove(index);
        return true;
    }

    // SHOW CATEGORIES
    public void showCategories() {
        System.out.println("\n===== AVAILABLE CATEGORIES =====");
        for (int i = 0; i < categories.size(); i++) {
            Category c = categories.get(i);
            System.out.println((i + 1) + ". " + c.getName() + " (" + c.getDifficulty() + ") - " + c.getXpReward() + " XP");
        }
    }
}