import java.util.ArrayList;

public class User {

    private String name;
    private int xp;
    private int level;

    private ArrayList<Task> tasks;
    private ArrayList<Badge> badges;

    // CONSTRUCTOR
    public User(String name) {

        this.name = name;

        xp = 0;
        level = 0;

        tasks = new ArrayList<>();
        badges = new ArrayList<>();
    }

    // ADD TASK
    public void addTask(String title, String category, int xpReward) {

        tasks.add(new Task(title, category, xpReward));
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

            if (b.getName().equals(badgeName())) {

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
}