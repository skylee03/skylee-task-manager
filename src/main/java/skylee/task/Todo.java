package skylee.task;

import skylee.exception.SkyleeException;
import skylee.io.Config;

import static skylee.ui.Message.MESSAGE_TODO_EMPTY;

public class Todo extends Task {
    public static final String type = "T";

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

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[" + type + "]" + super.toString();
    }

    @Override
    public String show() {
        return String.join(Config.DELIMITER, type, isDone() ? "1" : "0", getDescription());
    }
}
