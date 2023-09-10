import java.util.Scanner;

public class Skylee {
    private static final String LINE = "____________________________________________________________\n";
    private static final String MESSAGE_INDENT = " ";
    private static final String TASK_INDENT = "  ";
    private static final String EXCEPTION_PREFIX = "☹ OOPS!!! ";
    private static final String[] HELLO_MESSAGE = {"Hello! I'm Skylee!", "What can I do for you?"};
    private static final String BYE_MESSAGE = "Bye. Hope to see you again soon!";
    private static Task[] tasks = new Task[100];
    private static int taskCount = 0;

    private static void showMessages(String... messages) {
        System.out.print(LINE);
        for (String message : messages) {
            System.out.println(MESSAGE_INDENT + message);
        }
        System.out.println(LINE);
    }

    private static String[] addTask(Task task) {
        tasks[taskCount] = task;
        taskCount++;
        return new String[]{"Got it. I've added this task:",
                TASK_INDENT + task,
                "Now you have " + taskCount + " task" + (taskCount > 1 ? "s" : "") + " in the list."};
    }

    private static String[] markTask(Task task) {
        task.markAsDone();
        return new String[]{"Nice! I've marked this task as done:",
                TASK_INDENT + task};
    }

    private static String[] unmarkTask(Task task) {
        task.unmarkAsNotDone();
        return new String[]{"OK, I've marked this task as not done yet:",
                TASK_INDENT + task};
    }

    private static String[] listTasks() {
        String[] messages = new String[taskCount + 1];
        messages[0] = "Here are the tasks in your list:";
        for (int i = 0; i < taskCount; i++) {
            messages[i + 1] = (i + 1) + "." + tasks[i];
        }
        return messages;
    }

    private static String[] showException(String message) {
        return new String[]{EXCEPTION_PREFIX + message};
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
            case "bye":
                showMessages(BYE_MESSAGE);
                System.exit(0);
            case "list":
                return listTasks();
            case "mark":
                try {
                    int taskId = Integer.parseInt(commandArgs) - 1;
                    if (taskId < 0 || taskId >= taskCount) {
                        throw new SkyleeException("Task ID is out of range.");
                    }
                    return markTask(tasks[taskId]);
                } catch (NumberFormatException e) {
                    throw new SkyleeException("Task ID must be an integer.");
                }
            case "unmark":
                try {
                    int taskId = Integer.parseInt(commandArgs) - 1;
                    if (taskId < 0 || taskId >= taskCount) {
                        throw new SkyleeException("Task ID is out of range.");
                    }
                    return unmarkTask(tasks[taskId]);
                } catch (NumberFormatException e) {
                    throw new SkyleeException("Task ID must be an integer.");
                }
            case "todo":
                return addTask(Todo.parseTodo(commandArgs));
            case "deadline":
                return addTask(Deadline.parseDeadline(commandArgs));
            case "event":
                return addTask(Event.parseEvent(commandArgs));
            default:
                throw new SkyleeException("I'm sorry, but I don't know what that means :-(");
            }
        } catch (SkyleeException e) {
            return showException(e.getMessage());
        }
    }

    public static void main(String[] args) {
        showMessages(HELLO_MESSAGE);
        Scanner scanner = new Scanner(System.in);
        for (;;) {
            final String command = scanner.nextLine();
            final String[] feedback = executeCommand(command);
            showMessages(feedback);
        }
    }
}
