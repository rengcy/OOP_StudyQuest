public class Task {

    private String title;
    private String category;
    private boolean completed;
    private int xp;

    // CONSTRUCTOR
    public Task(String title, String category, int xp) {

        this.title = title;
        this.category = category;
        this.xp = xp;

        completed = false;
    }

    // MARK TASK AS DONE
    public void markDone() {
        completed = true;
    }

    // CHECK IF DONE
    public boolean isDone() {
        return completed;
    }

    // GET XP
    public int getXP() {
        return xp;
    }

    // GET TITLE
    public String getTitle() {
        return title;
    }

    // GET CATEGORY
    public String getCategory() {
        return category;
    }

    // SAVE TO FILE
    public String toFileString() {

        return title + "," +
               category + "," +
               xp + "," +
               completed;
    }
}