public class Event extends Task {
    protected String from;
    protected String to;

    public static Event parseEvent(String commandArgs) throws SkyleeException {
        final int fromIndex = commandArgs.indexOf("/from");
        final int toIndex = commandArgs.indexOf("/to");
        if (fromIndex == -1) {
            throw new SkyleeException("The \"from\" field is missing.");
        }
        if (toIndex == -1) {
            throw new SkyleeException("The \"to\" field is missing.");
        }
        final String description = commandArgs.substring(0, fromIndex).trim();
        final String from = commandArgs.substring(fromIndex + "/from".length(), toIndex).trim();
        final String to = commandArgs.substring(toIndex + "/to".length()).trim();
        if (description.isEmpty()) {
            throw new SkyleeException("The \"description\" field of an event cannot be empty.");
        }
        if (from.isEmpty()) {
            throw new SkyleeException("The \"from\" field of an event cannot be empty.");
        }
        if (to.isEmpty()) {
            throw new SkyleeException("The \"to\" field of an event cannot be empty.");
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
