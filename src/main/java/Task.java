public class Task {
    private String description;

    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        isDone = true;
    }

    public void unmarkAsNotDone() {
        isDone = false;
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }
}
