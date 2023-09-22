package skylee.task;

import skylee.storage.Config;

public class Deadline extends Task {
    public static final String type = "D";

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[" + type + "]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String show() {
        return String.join(Config.DELIMITER, type, isDone() ? "1" : "0", getDescription(), by);
    }
}
