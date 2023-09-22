package skylee;

import skylee.exception.SkyleeException;
import skylee.parser.Parser;
import skylee.storage.Storage;
import skylee.task.Task;
import skylee.ui.Ui;

import java.util.ArrayList;

import static skylee.parser.Command.COMMAND_BYE;
import static skylee.parser.Command.COMMAND_LIST;
import static skylee.parser.Command.COMMAND_MARK;
import static skylee.parser.Command.COMMAND_UNMARK;
import static skylee.parser.Command.COMMAND_TODO;
import static skylee.parser.Command.COMMAND_DEADLINE;
import static skylee.parser.Command.COMMAND_EVENT;
import static skylee.parser.Command.COMMAND_DELETE;

import static skylee.ui.Message.PREFIX_TASK;
import static skylee.ui.Message.PREFIX_EXCEPTION;
import static skylee.ui.Message.MESSAGE_HELLO;
import static skylee.ui.Message.MESSAGE_BYE;
import static skylee.ui.Message.MESSAGE_UNKNOWN_COMMAND;
import static skylee.ui.Message.MESSAGE_ID_OUT_OF_RANGE;
import static skylee.ui.Message.MESSAGE_LIST;
import static skylee.ui.Message.MESSAGE_UNMARK;
import static skylee.ui.Message.MESSAGE_MARK;
import static skylee.ui.Message.MESSAGE_DELETE;
import static skylee.ui.Message.MESSAGE_ADD;
import static skylee.ui.Message.MESSAGE_COUNT;

public class Skylee {
    private static ArrayList<Task> tasks = new ArrayList<>();
    private Ui ui;
    private Storage storage;
    private Parser parser;

    private void bye() throws SkyleeException {
        storage.saveFile(tasks);
        ui.showMessages(MESSAGE_BYE);
        System.exit(0);
    }

    private static String[] addTask(Task task) {
        tasks.add(task);
        return new String[]{MESSAGE_ADD,
                PREFIX_TASK + task,
                String.format(MESSAGE_COUNT, tasks.size(), tasks.size() > 1 ? "s" : "")};
    }

    private static String[] markTask(String commandArgs) throws SkyleeException {
        final int taskId = Parser.parseTaskId(commandArgs);
        if (taskId < 0 || taskId >= tasks.size()) {
            throw new SkyleeException(MESSAGE_ID_OUT_OF_RANGE);
        }
        tasks.get(taskId).markAsDone();
        return new String[]{MESSAGE_MARK,
                PREFIX_TASK + tasks.get(taskId)};
    }

    private static String[] unmarkTask(String commandArgs) throws SkyleeException {
        final int taskId = Parser.parseTaskId(commandArgs);
        if (taskId < 0 || taskId >= tasks.size()) {
            throw new SkyleeException(MESSAGE_ID_OUT_OF_RANGE);
        }
        tasks.get(taskId).unmarkAsNotDone();
        return new String[]{MESSAGE_UNMARK,
                PREFIX_TASK + tasks.get(taskId)};
    }

    private static String[] listTasks() {
        String[] messages = new String[tasks.size() + 1];
        messages[0] = MESSAGE_LIST;
        for (int i = 0; i < tasks.size(); i++) {
            messages[i + 1] = (i + 1) + "." + tasks.get(i);
        }
        return messages;
    }

    private static String[] deleteTask(String commandArgs) throws SkyleeException {
        final int taskId = Parser.parseTaskId(commandArgs);
        if (taskId < 0 || taskId >= tasks.size()) {
            throw new SkyleeException(MESSAGE_ID_OUT_OF_RANGE);
        }
        final Task task = tasks.get(taskId);
        tasks.remove(taskId);
        return new String[]{MESSAGE_DELETE,
                PREFIX_TASK + task,
                String.format(MESSAGE_COUNT, tasks.size(), tasks.size() > 1 ? "s" : "")};
    }

    private static String[] showException(String message) {
        return new String[]{PREFIX_EXCEPTION + message};
    }
    
    private String[] executeCommand(String userInputString) {
        final String[] commandTypeAndParams = parser.splitCommandWordAndArgs(userInputString);
        final String commandType = commandTypeAndParams[0];
        final String commandArgs = commandTypeAndParams[1];
        try {
            switch (commandType) {
            case COMMAND_BYE:
                bye();
                // Fallthrough
            case COMMAND_LIST:
                return listTasks();
            case COMMAND_MARK:
                return markTask(commandArgs);
            case COMMAND_UNMARK:
                return unmarkTask(commandArgs);
            case COMMAND_TODO:
                return addTask(Parser.parseTodo(commandArgs));
            case COMMAND_DEADLINE:
                return addTask(Parser.parseDeadline(commandArgs));
            case COMMAND_EVENT:
                return addTask(Parser.parseEvent(commandArgs));
            case COMMAND_DELETE:
                return deleteTask(commandArgs);
            default:
                throw new SkyleeException(MESSAGE_UNKNOWN_COMMAND);
            }
        } catch (SkyleeException e) {
            return showException(e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Skylee().run();
    }

    public void run() {
        tasks = storage.loadFile();
        ui.showMessages(MESSAGE_HELLO);
        for (;;) {
            final String command = ui.getUserCommand();
            final String[] feedback = executeCommand(command);
            ui.showMessages(feedback);
        }
    }
}
