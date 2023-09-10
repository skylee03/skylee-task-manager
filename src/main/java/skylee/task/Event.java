package skylee.task;

import skylee.SkyleeException;

import static skylee.io.Message.MESSAGE_FROM_MISSING;
import static skylee.io.Message.MESSAGE_TO_MISSING;
import static skylee.io.Message.MESSAGE_EVENT_EMPTY;
import static skylee.io.Message.MESSAGE_FROM_EMPTY;
import static skylee.io.Message.MESSAGE_TO_EMPTY;

import static skylee.io.Parameter.PARAMETER_FROM;
import static skylee.io.Parameter.PARAMETER_TO;

public class Event extends Task {
    protected String from;
    protected String to;

    public static Event parseEvent(String commandArgs) throws SkyleeException {
        final int fromIndex = commandArgs.indexOf(PARAMETER_FROM);
        final int toIndex = commandArgs.indexOf(PARAMETER_TO);
        if (fromIndex == -1) {
            throw new SkyleeException(MESSAGE_FROM_MISSING);
        }
        if (toIndex == -1) {
            throw new SkyleeException(MESSAGE_TO_MISSING);
        }
        final String description = commandArgs.substring(0, fromIndex).trim();
        final String from = commandArgs.substring(fromIndex + PARAMETER_FROM.length(), toIndex).trim();
        final String to = commandArgs.substring(toIndex + PARAMETER_TO.length()).trim();
        if (description.isEmpty()) {
            throw new SkyleeException(MESSAGE_EVENT_EMPTY);
        }
        if (from.isEmpty()) {
            throw new SkyleeException(MESSAGE_FROM_EMPTY);
        }
        if (to.isEmpty()) {
            throw new SkyleeException(MESSAGE_TO_EMPTY);
        }
        return new Event(description, from, to);
    }

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
