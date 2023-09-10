public class Event extends Task {
    public static final String PARAMETER_FROM = "/from";
    public static final String PARAMETER_TO = "/to";
    public static final String MESSAGE_FROM_MISSING = "The \"from\" field is missing.";
    public static final String MESSAGE_TO_MISSING = "The \"to\" field is missing.";
    public static final String MESSAGE_EVENT_EMPTY = "The \"description\" field of an event cannot be empty.";
    public static final String MESSAGE_FROM_EMPTY = "The \"from\" field of an event cannot be empty.";
    public static final String MESSAGE_TO_EMPTY = "The \"to\" field of an event cannot be empty.";
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
