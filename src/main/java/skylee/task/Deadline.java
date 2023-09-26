package skylee.task;

import static skylee.storage.Config.DATE_TIME_FORMAT;

import java.time.LocalDate;
import java.time.LocalDateTime;

import skylee.storage.Config;

/**
 * Defines the basic fields and methods of a deadline.
 */
public class Deadline extends Task {
    public static final String TYPE = "D";

    protected LocalDateTime by;

    /**
     * Constructs a <code>Deadline</code> object with the description
     * and the time.
     * The status of the deadline is "not done" by default.
     *
     * @param description   The description of the deadline.
     * @param by            The time of the deadline.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructs a <code>Deadline</code> object with the description,
     * the time and the status representing whether the task is done.
     *
     * @param description   The description of the deadline.
     * @param by            The time of the deadline.
     * @param isDone        Whether the event is done.
     */
    public Deadline(String description, LocalDateTime by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Returns a human-readable string representing the deadline.
     *
     * @return  A human-readable string representing the deadline.
     */
    @Override
    public String toString() {
        return "[" + TYPE + "]" + super.toString() + " (by: " + by.format(DATE_TIME_FORMAT) + ")";
    }

    /**
     * Returns a parser-friendly string representing the deadline.
     *
     * @return  A parser-friendly string representing the deadline.
     */
    @Override
    public String show() {
        return String.join(Config.DELIMITER, TYPE, isDone() ? "1" : "0", getDescription(), by.toString());
    }

    /**
     * Returns whether the deadline is occurring on the given date.
     *
     * @param date  The date on which the deadline is occurring.
     * @return      Whether the deadline is occurring on the given date.
     */
    @Override
    public boolean isOccurringOn(LocalDate date) {
        return date.isEqual(by.toLocalDate());
    }
}
