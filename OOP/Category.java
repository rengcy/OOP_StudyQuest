public class Category {

    private String name;
    private String difficulty; // Easy, Medium, Hard
    private int xpReward;

    // CONSTRUCTOR
    public Category(String name, String difficulty) {

        this.name = name;
        this.difficulty = difficulty;
        this.xpReward = calculateXP(difficulty);
    }

    // CALCULATE XP BASED ON DIFFICULTY
    private int calculateXP(String difficulty) {

        switch (difficulty.toLowerCase()) {
            case "easy":
                return 20;
            case "medium":
                return 50;
            case "hard":
                return 100;
            default:
                return 30;
        }
    }

    // GETTERS
    public String getName() {
        return name;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public int getXpReward() {
        return xpReward;
    }

    // CONVERT TO FILE FORMAT
    public String toFileString() {
        return name + "|" + difficulty + "|" + xpReward;
    }
}
