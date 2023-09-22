package skylee.task;

abstract public class Task {
    private String description;

    private boolean isDone;

    public Task(String description) {
        this(description, false);
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.setDone(isDone);
    }

    abstract public String show();

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public String getStatusIcon() {
        return (isDone() ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        setDone(true);
    }

    public void unmarkAsNotDone() {
        setDone(false);
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }
}
