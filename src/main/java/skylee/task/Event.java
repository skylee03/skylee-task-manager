package skylee.task;

import skylee.storage.Config;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static skylee.storage.Config.DATE_TIME_FORMAT;

public class Event extends Task {
    public static final String type = "E";
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public Event(String description, LocalDateTime from, LocalDateTime to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[" + type + "]" + super.toString()
                + " (from: " + from.format(DATE_TIME_FORMAT)
                + " to: " + to.format(DATE_TIME_FORMAT) + ")";
    }

    @Override
    public String show() {
        return String.join(Config.DELIMITER, type, isDone() ? "1" : "0",
                getDescription(), from.toString(), to.toString());
    }

    @Override
    public boolean isOccurringOn(LocalDate date) {
        LocalDate fromDate = from.toLocalDate();
        LocalDate toDate = to.toLocalDate();
        return (date.isEqual(fromDate) || date.isAfter(fromDate))
                && (date.isEqual(toDate) || date.isBefore(toDate));
    }
}
