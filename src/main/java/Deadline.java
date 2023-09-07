public class Deadline extends Task {
    protected String by;

    public static Deadline parseDeadline(String command) {
        int byIndex = command.indexOf(" /by ");
        String description = command.substring("deadline ".length(), byIndex);
        String by = command.substring(byIndex + " /by ".length());
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
