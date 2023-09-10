public class Todo extends Task {

    public static final String MESSAGE_TODO_EMPTY = "The \"description\" field of a todo cannot be empty.";

    public static Todo parseTodo(String commandArgs) throws SkyleeException {
        final String description = commandArgs;
        if (description.isEmpty()) {
            throw new SkyleeException(MESSAGE_TODO_EMPTY);
        }
        return new Todo(description);
    }

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
