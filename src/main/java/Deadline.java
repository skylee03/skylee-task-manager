public class Deadline extends Task {
    protected String by;

    public static Deadline parseDeadline(String commandArgs) throws SkyleeException {
        final int byIndex = commandArgs.indexOf("/by");
        if (byIndex == -1) {
            throw new SkyleeException("The \"by\" field is missing.");
        }
        final String description = commandArgs.substring(0, byIndex).trim();
        final String by = commandArgs.substring(byIndex + "/by".length()).trim();
        if (description.isEmpty()) {
            throw new SkyleeException("The \"description\" field of a deadline cannot be empty.");
        }
        if (by.isEmpty()) {
            throw new SkyleeException("The \"by\" field of a deadline cannot be empty.");
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
