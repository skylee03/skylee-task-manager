package skylee.task;

import static skylee.storage.Config.DATE_TIME_FORMAT;

import java.time.LocalDate;
import java.time.LocalDateTime;

import skylee.storage.Config;

/**
 * Defines the basic fields and methods of a event.
 */
public class Event extends Task {
    public static final String TYPE = "E";
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs a <code>Event</code> object with the description,
     * the start time and the end time.
     * The status of the event is "not done" by default.
     *
     * @param description   The description of the event.
     * @param from          The start time of the event.
     * @param to            The end time of the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructs a <code>Event</code> object with the description,
     * the start time, the end time and the status representing whether
     * the task is done.
     *
     * @param description   The description of the event.
     * @param from          The start time of the event.
     * @param to            The end time of the event.
     * @param isDone        Whether the event is done.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a human-readable string representing the event.
     *
     * @return  A human-readable string representing the event.
     */
    @Override
    public String toString() {
        return "[" + TYPE + "]" + super.toString()
                + " (from: " + from.format(DATE_TIME_FORMAT)
                + " to: " + to.format(DATE_TIME_FORMAT) + ")";
    }

    /**
     * Returns a parser-friendly string representing the event.
     *
     * @return  A parser-friendly string representing the event.
     */
    @Override
    public String show() {
        return String.join(Config.DELIMITER, TYPE, isDone() ? "1" : "0",
                getDescription(), from.toString(), to.toString());
    }

    /**
     * Returns whether the event is occurring on the given date.
     *
     * @param date  The date on which the event is occurring.
     * @return      Whether the event is occurring on the given date.
     */
    @Override
    public boolean isOccurringOn(LocalDate date) {
        LocalDate fromDate = from.toLocalDate();
        LocalDate toDate = to.toLocalDate();
        return (date.isEqual(fromDate) || date.isAfter(fromDate))
                && (date.isEqual(toDate) || date.isBefore(toDate));
    }
}
