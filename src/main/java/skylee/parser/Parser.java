package skylee.parser;

import skylee.commands.AddCommand;
import skylee.commands.ByeCommand;
import skylee.commands.Command;
import skylee.commands.DeleteCommand;
import skylee.commands.FindCommand;
import skylee.commands.ListCommand;
import skylee.commands.MarkCommand;
import skylee.commands.UnmarkCommand;
import skylee.exception.SkyleeException;
import skylee.storage.Config;
import skylee.task.Deadline;
import skylee.task.Event;
import skylee.task.Task;
import skylee.task.Todo;

import static skylee.parser.CommandName.COMMAND_BYE;
import static skylee.parser.CommandName.COMMAND_DEADLINE;
import static skylee.parser.CommandName.COMMAND_DELETE;
import static skylee.parser.CommandName.COMMAND_EVENT;
import static skylee.parser.CommandName.COMMAND_FIND;
import static skylee.parser.CommandName.COMMAND_LIST;
import static skylee.parser.CommandName.COMMAND_MARK;
import static skylee.parser.CommandName.COMMAND_TODO;
import static skylee.parser.CommandName.COMMAND_UNMARK;
import static skylee.parser.Parameter.PARAMETER_BY;
import static skylee.parser.Parameter.PARAMETER_FROM;
import static skylee.parser.Parameter.PARAMETER_TO;
import static skylee.ui.Message.MESSAGE_BY_EMPTY;
import static skylee.ui.Message.MESSAGE_BY_MISSING;
import static skylee.ui.Message.MESSAGE_DEADLINE_EMPTY;
import static skylee.ui.Message.MESSAGE_EVENT_EMPTY;
import static skylee.ui.Message.MESSAGE_FILE_FORMAT_ERROR;
import static skylee.ui.Message.MESSAGE_FROM_EMPTY;
import static skylee.ui.Message.MESSAGE_FROM_MISSING;
import static skylee.ui.Message.MESSAGE_ID_FORMAT;
import static skylee.ui.Message.MESSAGE_TODO_EMPTY;
import static skylee.ui.Message.MESSAGE_TO_EMPTY;
import static skylee.ui.Message.MESSAGE_TO_MISSING;
import static skylee.ui.Message.MESSAGE_UNKNOWN_COMMAND;


public class Parser {
    public static String[] splitCommandWordAndArgs(String rawUserInput) {
        final String[] split = rawUserInput.trim().split("\\s+", 2);
        return split.length == 2 ? split : new String[] { split[0] , "" };
    }

    public static Command parseCommand(String rawUserInput) throws SkyleeException {
        final String[] commandTypeAndParams = splitCommandWordAndArgs(rawUserInput);
        final String commandType = commandTypeAndParams[0];
        final String commandArgs = commandTypeAndParams[1];
        try {
            switch (commandType) {
            case COMMAND_BYE:
                return new ByeCommand();
            case COMMAND_LIST:
                return new ListCommand();
            case COMMAND_MARK:
                return new MarkCommand(parseTaskId(commandArgs));
            case COMMAND_UNMARK:
                return new UnmarkCommand(parseTaskId(commandArgs));
            case COMMAND_TODO:
                return new AddCommand(parseTodo(commandArgs));
            case COMMAND_DEADLINE:
                return new AddCommand(parseDeadline(commandArgs));
            case COMMAND_EVENT:
                return new AddCommand(parseEvent(commandArgs));
            case COMMAND_DELETE:
                return new DeleteCommand(parseTaskId(commandArgs));
            case COMMAND_FIND:
                return new FindCommand(commandArgs);
            default:
                throw new SkyleeException(MESSAGE_UNKNOWN_COMMAND);
            }
        } catch (SkyleeException e) {
            throw e;
        }
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
