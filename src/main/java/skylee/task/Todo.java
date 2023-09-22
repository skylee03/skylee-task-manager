package skylee.task;

import skylee.storage.Config;

public class Todo extends Task {
    public static final String type = "T";

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[" + type + "]" + super.toString();
    }

    @Override
    public String show() {
        return String.join(Config.DELIMITER, type, isDone() ? "1" : "0", getDescription());
    }
}
