public class Deadline extends Task {
    public static final String PARAMETER_BY = "/by";
    public static final String MESSAGE_BY_MISSING = "The \"by\" field is missing.";
    public static final String MESSAGE_DEADLINE_EMPTY = "The \"description\" field of a deadline cannot be empty.";
    public static final String MESSAGE_BY_EMPTY = "The \"by\" field of a deadline cannot be empty.";
    protected String by;

    public static Deadline parseDeadline(String commandArgs) throws SkyleeException {
        final int byIndex = commandArgs.indexOf(PARAMETER_BY);
        if (byIndex == -1) {
            throw new SkyleeException(MESSAGE_BY_MISSING);
        }
        final String description = commandArgs.substring(0, byIndex).trim();
        final String by = commandArgs.substring(byIndex + PARAMETER_BY.length()).trim();
        if (description.isEmpty()) {
            throw new SkyleeException(MESSAGE_DEADLINE_EMPTY);
        }
        if (by.isEmpty()) {
            throw new SkyleeException(MESSAGE_BY_EMPTY);
        }
        return new Deadline(description, by);
    }

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
