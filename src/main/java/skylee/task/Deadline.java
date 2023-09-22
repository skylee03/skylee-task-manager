package skylee.task;

import skylee.storage.Config;

import java.time.LocalDateTime;

import static skylee.storage.Config.DATE_TIME_FORMAT;

public class Deadline extends Task {
    public static final String type = "D";

    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, LocalDateTime by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[" + type + "]" + super.toString() + " (by: " + by.format(DATE_TIME_FORMAT) + ")";
    }

    @Override
    public String show() {
        return String.join(Config.DELIMITER, type, isDone() ? "1" : "0", getDescription(), by.toString());
    }
}
