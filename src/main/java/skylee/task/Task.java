package skylee.task;

import skylee.exception.SkyleeException;
import skylee.io.Config;

import static skylee.io.Message.MESSAGE_FILE_FORMAT_ERROR;

abstract public class Task {
    private String description;

    private boolean isDone;

    public Task(String description) {
        this(description, false);
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.setDone(isDone);
    }

    abstract public String show();

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public String getStatusIcon() {
        return (isDone() ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        setDone(true);
    }

    public void unmarkAsNotDone() {
        setDone(false);
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }

    public static Task parseTask(String line) throws SkyleeException {
        String[] fields = line.split(Config.DELIMITER_REGEX);
        boolean isDone = Integer.parseInt(fields[1]) != 0;
        switch (fields[0]) {
        case Todo.type:
            if (fields.length != 3) {
                throw new SkyleeException(MESSAGE_FILE_FORMAT_ERROR);
            }
            return new Todo(fields[2], isDone);
        case Deadline.type:
            if (fields.length != 4) {
                throw new SkyleeException(MESSAGE_FILE_FORMAT_ERROR);
            }
            return new Deadline(fields[2], fields[3], isDone);
        case Event.type:
            if (fields.length != 5) {
                throw new SkyleeException(MESSAGE_FILE_FORMAT_ERROR);
            }
            return new Event(fields[2], fields[3], fields[4], isDone);
        default:
            throw new SkyleeException(MESSAGE_FILE_FORMAT_ERROR);
        }
    }
}
