package skylee.task;

import java.time.LocalDate;

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

    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }

    public boolean isOccurringOn(LocalDate date) {
        return false;
    }
}
