package skylee.task;

import skylee.storage.Config;

public class Event extends Task {
    public static final String type = "E";

    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public Event(String description, String from, String to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[" + type + "]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public String show() {
        return String.join(Config.DELIMITER, type, isDone() ? "1" : "0", getDescription(), from, to);
    }
}
