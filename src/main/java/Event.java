public class Event extends Task {
    protected String from;
    protected String to;

    public static Event parseEvent(String command) {
        int fromIndex = command.indexOf(" /from ");
        int toIndex = command.indexOf(" /to ");
        String description = command.substring("event ".length(), fromIndex);
        String from = command.substring(fromIndex + " /from ".length(), toIndex);
        String to = command.substring(toIndex + " /to ".length());
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
