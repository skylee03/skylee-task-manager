import java.util.Scanner;

public class Skylee {
    private static final String LINE = "____________________________________________________________\n";
    private static final String HELLO_MESSAGE = LINE + " Hello! I'm Skylee!\n What can I do for you?\n" + LINE;
    private static final String BYE_MESSAGE = LINE + " Bye. Hope to see you again soon!\n" + LINE;
    private static Task[] tasks = new Task[100];
    private static int taskCount = 0;

    private static void addTaskAndPrint(Task task) {
        tasks[taskCount] = task;
        taskCount++;
        System.out.print(LINE);
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + taskCount + " task" + (taskCount > 1 ? "s" : "") + " in the list.");
        System.out.println(LINE);
    }

    private static void markAndPrint(Task task) {
        task.markAsDone();
        System.out.print(LINE);
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   " + task);
        System.out.println(LINE);
    }

    private static void unmarkAndPrint(Task task) {
        task.unmarkAsNotDone();
        System.out.print(LINE);
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println("   " + task);
        System.out.println(LINE);
    }

    private static void printTasks() {
        System.out.print(LINE);
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println(" " + (i + 1) + "." + tasks[i]);
        }
        System.out.println(LINE);
    }

    private static void printException(String message) {
        System.out.print(LINE);
        System.out.println(" ☹ OOPS!!! " + message);
        System.out.println(LINE);
    }

    private static void printHelloMessage() {
        System.out.println(HELLO_MESSAGE);
    }

    private static void printByeMessage() {
        System.out.println(BYE_MESSAGE);
    }

    private static String[] splitCommandWordAndArgs(String rawUserInput) {
        final String[] split = rawUserInput.trim().split("\\s+", 2);
        return split.length == 2 ? split : new String[] { split[0] , "" };
    }
    
    private static void executeCommand(String userInputString) {
        final String[] commandTypeAndParams = splitCommandWordAndArgs(userInputString);
        final String commandType = commandTypeAndParams[0];
        final String commandArgs = commandTypeAndParams[1];
        try {
            switch (commandType) {
            case "bye":
                printByeMessage();
                System.exit(0);
                break;
            case "list":
                printTasks();
                break;
            case "mark":
                try {
                    int taskId = Integer.parseInt(commandArgs) - 1;
                    if (taskId < 0 || taskId >= taskCount) {
                        throw new SkyleeException("Task ID is out of range.");
                    }
                    markAndPrint(tasks[taskId]);
                } catch (NumberFormatException e) {
                    throw new SkyleeException("Task ID must be an integer.");
                }
                break;
            case "unmark":
                try {
                    int taskId = Integer.parseInt(commandArgs) - 1;
                    if (taskId < 0 || taskId >= taskCount) {
                        throw new SkyleeException("Task ID is out of range.");
                    }
                    unmarkAndPrint(tasks[taskId]);
                } catch (NumberFormatException e) {
                    throw new SkyleeException("Task ID must be an integer.");
                }
                break;
            case "todo":
                addTaskAndPrint(Todo.parseTodo(commandArgs));
                break;
            case "deadline":
                addTaskAndPrint(Deadline.parseDeadline(commandArgs));
                break;
            case "event":
                addTaskAndPrint(Event.parseEvent(commandArgs));
                break;
            default:
                throw new SkyleeException("I'm sorry, but I don't know what that means :-(");
            }
        } catch (SkyleeException e) {
            printException(e.getMessage());
        }
    }

    public static void main(String[] args) {
        printHelloMessage();
        Scanner scanner = new Scanner(System.in);
        for (;;) {
            executeCommand(scanner.nextLine());
        }
    }
}
