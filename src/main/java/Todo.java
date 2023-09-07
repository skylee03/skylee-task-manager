public class Todo extends Task {
    public static Todo parseTodo(String command) {
        String description = command.substring("todo ".length());
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
