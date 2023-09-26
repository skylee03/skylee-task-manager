package skylee.task;

import java.time.LocalDate;

/**
 * Defines the basic fields and methods of a task.
 */
public abstract class Task {
    private String description;

    private boolean isDone;

    /**
     * Constructs a <code>Task</code> object with the description.
     * The status of the task is "not done" by default.
     *
     * @param description   The description of the task.
     */
    public Task(String description) {
        this(description, false);
    }

    /**
     * Constructs a <code>Task</code> object with the description
     * and the status representing whether the task is done.
     *
     * @param description   The description of the task.
     * @param isDone        Whether the task is done.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.setDone(isDone);
    }

    /**
     * Returns a parser-friendly string representing the task.
     *
     * @return  A parser-friendly string representing the task.
     */
    public abstract String show();

    /**
     * Returns the description of the task.
     *
     * @return  The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns whether the task is done.
     *
     * @return  Whether the task is done.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Sets whether the task is done.
     *
     * @param done  Whether the task is done.
     */
    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     * Returns a string representing whether the task is done.
     *
     * @return  A string representing whether the task is done.
     */
    public String getStatusIcon() {
        return (isDone() ? "X" : " "); // mark done task with X
    }

    /**
     * Returns a human-readable string representing the task.
     *
     * @return  A human-readable string representing the task.
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }

    /**
     * Returns whether the task is occurring on the given date.
     *
     * @param date  The date on which the task is occurring.
     * @return      Whether the task is occurring on the given date.
     */
    public boolean isOccurringOn(LocalDate date) {
        return false;
    }
}
