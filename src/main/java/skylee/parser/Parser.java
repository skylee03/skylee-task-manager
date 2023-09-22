package skylee.parser;

import skylee.exception.SkyleeException;
import skylee.storage.Config;
import skylee.task.Deadline;
import skylee.task.Event;
import skylee.task.Task;
import skylee.task.Todo;

import static skylee.parser.Parameter.*;
import static skylee.ui.Message.*;

public class Parser {
    public static String[] splitCommandWordAndArgs(String rawUserInput) {
        final String[] split = rawUserInput.trim().split("\\s+", 2);
        return split.length == 2 ? split : new String[] { split[0] , "" };
    }

    public static int parseTaskId(String commandArgs) throws SkyleeException {
        int taskId;
        try {
            taskId = Integer.parseInt(commandArgs) - 1;
        } catch (NumberFormatException e) {
            throw new SkyleeException(MESSAGE_ID_FORMAT);
        }
        return taskId;
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

    public static Todo parseTodo(String commandArgs) throws SkyleeException {
        final String description = commandArgs;
        if (description.isEmpty()) {
            throw new SkyleeException(MESSAGE_TODO_EMPTY);
        }
        return new Todo(description);
    }

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
}
