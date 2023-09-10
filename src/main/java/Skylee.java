import java.util.Scanner;

public class Skylee {
    private static final String PREFIX_EXCEPTION = "☹ OOPS!!! ";
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String LINE = "____________________________________________________________\n";
    private static final String PREFIX_MESSAGE = " ";
    private static final String PREFIX_TASK = "  ";
    private static final String[] MESSAGE_HELLO = {"Hello! I'm Skylee!", "What can I do for you?"};
    private static final String MESSAGE_BYE = "Bye. Hope to see you again soon!";
    public static final String MESSAGE_UNKNOWN_COMMAND = "I'm sorry, but I don't know what that means :-(";
    public static final String MESSAGE_ID_FORMAT = "Task ID must be an integer.";
    public static final String MESSAGE_ID_OUT_OF_RANGE = "Task ID is out of range.";
    public static final String MESSAGE_LIST = "Here are the tasks in your list:";
    public static final String MESSAGE_UNMARK = "OK, I've marked this task as not done yet:";
    public static final String MESSAGE_MARK = "Nice! I've marked this task as done:";
    public static final String MESSAGE_ADD = "Got it. I've added this task:";
    public static final String MESSAGE_COUNT = "Now you have %d task%s in the list.";
    private static Task[] tasks = new Task[100];
    private static int taskCount = 0;

    private static void showMessages(String... messages) {
        System.out.print(LINE);
        for (String message : messages) {
            System.out.println(PREFIX_MESSAGE + message);
        }
        System.out.println(LINE);
    }

    private static String[] addTask(Task task) {
        tasks[taskCount] = task;
        taskCount++;
        return new String[]{MESSAGE_ADD,
                PREFIX_TASK + task,
                String.format(MESSAGE_COUNT, taskCount, taskCount > 1 ? "s" : "")};
    }

    private static String[] markTask(Task task) {
        task.markAsDone();
        return new String[]{MESSAGE_MARK,
                PREFIX_TASK + task};
    }

    private static String[] unmarkTask(Task task) {
        task.unmarkAsNotDone();
        return new String[]{MESSAGE_UNMARK,
                PREFIX_TASK + task};
    }

    private static String[] listTasks() {
        String[] messages = new String[taskCount + 1];
        messages[0] = MESSAGE_LIST;
        for (int i = 0; i < taskCount; i++) {
            messages[i + 1] = (i + 1) + "." + tasks[i];
        }
        return messages;
    }

    private static String[] showException(String message) {
        return new String[]{PREFIX_EXCEPTION + message};
    }

    private static String[] splitCommandWordAndArgs(String rawUserInput) {
        final String[] split = rawUserInput.trim().split("\\s+", 2);
        return split.length == 2 ? split : new String[] { split[0] , "" };
    }
    
    private static String[] executeCommand(String userInputString) {
        final String[] commandTypeAndParams = splitCommandWordAndArgs(userInputString);
        final String commandType = commandTypeAndParams[0];
        final String commandArgs = commandTypeAndParams[1];
        try {
            switch (commandType) {
            case COMMAND_BYE:
                showMessages(MESSAGE_BYE);
                System.exit(0);
            case COMMAND_LIST:
                return listTasks();
            case COMMAND_MARK:
                try {
                    int taskId = Integer.parseInt(commandArgs) - 1;
                    if (taskId < 0 || taskId >= taskCount) {
                        throw new SkyleeException(MESSAGE_ID_OUT_OF_RANGE);
                    }
                    return markTask(tasks[taskId]);
                } catch (NumberFormatException e) {
                    throw new SkyleeException(MESSAGE_ID_FORMAT);
                }
            case COMMAND_UNMARK:
                try {
                    int taskId = Integer.parseInt(commandArgs) - 1;
                    if (taskId < 0 || taskId >= taskCount) {
                        throw new SkyleeException(MESSAGE_ID_OUT_OF_RANGE);
                    }
                    return unmarkTask(tasks[taskId]);
                } catch (NumberFormatException e) {
                    throw new SkyleeException(MESSAGE_ID_FORMAT);
                }
            case COMMAND_TODO:
                return addTask(Todo.parseTodo(commandArgs));
            case COMMAND_DEADLINE:
                return addTask(Deadline.parseDeadline(commandArgs));
            case COMMAND_EVENT:
                return addTask(Event.parseEvent(commandArgs));
            default:
                throw new SkyleeException(MESSAGE_UNKNOWN_COMMAND);
            }
        } catch (SkyleeException e) {
            return showException(e.getMessage());
        }
    }

    public static void main(String[] args) {
        showMessages(MESSAGE_HELLO);
        Scanner scanner = new Scanner(System.in);
        for (;;) {
            final String command = scanner.nextLine();
            final String[] feedback = executeCommand(command);
            showMessages(feedback);
        }
    }
}
