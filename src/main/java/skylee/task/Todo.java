package skylee.task;

import skylee.storage.Config;

/**
 * Defines the basic fields and methods of a todo.
 */
public class Todo extends Task {
    public static final String TYPE = "T";

    /**
     * Constructs a <code>Todo</code> object with the description.
     * The status of the todo is "not done" by default.
     *
     * @param description   The description of the todo.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructs a <code>Todo</code> object with the description
     * and the status representing whether the task is done.
     *
     * @param description   The description of the todo.
     * @param isDone        Whether the todo is done.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns a human-readable string representing the todo.
     *
     * @return  A human-readable string representing the todo.
     */
    @Override
    public String toString() {
        return "[" + TYPE + "]" + super.toString();
    }

    /**
     * Returns a parser-friendly string representing the todo.
     *
     * @return  A parser-friendly string representing the todo.
     */
    @Override
    public String show() {
        return String.join(Config.DELIMITER, TYPE, isDone() ? "1" : "0", getDescription());
    }
}
