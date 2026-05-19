import java.util.Scanner;

public class StudyQuest {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("=========================");
        System.out.println("      STUDY QUEST");
        System.out.println("=========================");

        System.out.print("Enter your name: ");
        String name = sc.nextLine();

        User user = new User(name);

        int choice;

        do {

            System.out.println("\n===== MAIN MENU =====");
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Complete Task");
            System.out.println("4. Show Stats");
            System.out.println("5. Show Badges");
            System.out.println("6. Save Tasks");
            System.out.println("7. Exit");

            System.out.print("Choose: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                // ADD TASK
                case 1:

                    System.out.print("Enter task title: ");
                    String title = sc.nextLine();

                    System.out.println("Choose Category:");
                    System.out.println("1. School (+50 XP)");
                    System.out.println("2. Personal (+20 XP)");
                    System.out.println("3. Fitness (+30 XP)");
                    System.out.println("4. Review (+40 XP)");
                    System.out.println("5. Assignment (+60 XP)");

                    System.out.print("Enter choice: ");
                    int categoryChoice = sc.nextInt();
                    sc.nextLine();

                    String category = "";
                    int xp = 0;

                    switch(categoryChoice) {

                        case 1:
                            category = "School";
                            xp = 50;
                            break;

                        case 2:
                            category = "Personal";
                            xp = 20;
                            break;

                        case 3:
                            category = "Fitness";
                            xp = 30;
                            break;

                        case 4:
                            category = "Review";
                            xp = 40;
                            break;

                        case 5:
                            category = "Assignment";
                            xp = 60;
                            break;

                        default:
                            category = "Unknown";
                            xp = 10;
                    }

                    user.addTask(title, category, xp);

                    System.out.println("Task added successfully!");
                    System.out.println("Reward: +" + xp + " XP");

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

                // SHOW STATS
                case 4:

                    user.showStats();

                    break;

                // SHOW BADGES
                case 5:

                    user.showBadges();

                    break;

                // SAVE TASKS
                case 6:

                    FileManager.saveTasks(user.getTasks());

                    break;

                // EXIT
                case 7:

                    System.out.println("Goodbye!");

                    break;

                default:

                    System.out.println("Invalid choice.");
            }

        } while (choice != 7);

        sc.close();
    }
}