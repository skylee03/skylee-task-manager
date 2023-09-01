import java.util.Scanner;

public class Skylee {
    private static final String LINE = "____________________________________________________________\n";
    private static Task[] tasks = new Task[100];
    private static int taskCount = 0;

    private static void addTask(Task task) {
        tasks[taskCount] = task;
        taskCount++;
        System.out.print(LINE);
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + taskCount + " task" + (taskCount > 1 ? "s" : "") + " in the list.");
        System.out.println(LINE);
    }

    public static void main(String[] args) {
        String helloMsg = LINE +
                " Hello! I'm Skylee!\n" +
                " What can I do for you?\n" +
                LINE;
        String byeMsg = LINE +
                " Bye. Hope to see you again soon!\n" +
                LINE;

        Scanner scanner = new Scanner(System.in);

        System.out.println(helloMsg);
        for (String command;;) {
            command = scanner.nextLine();
            if (command.equals("bye")) {
                System.out.println(byeMsg);
                break;
            } else if (command.equals("list")) {
                System.out.print(LINE);
                System.out.println(" Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println(" " + (i + 1) + "." + tasks[i]);
                }
                System.out.println(LINE);
            } else if (command.startsWith("mark ")) {
                int i = Integer.parseInt(command.substring("mark ".length())) - 1;
                tasks[i].markAsDone();
                System.out.print(LINE);
                System.out.println(" Nice! I've marked this task as done:");
                System.out.println("   " + tasks[i]);
                System.out.println(LINE);
            } else if (command.startsWith("unmark ")) {
                int i = Integer.parseInt(command.substring("unmark ".length())) - 1;
                tasks[i].unmarkAsNotDone();
                System.out.print(LINE);
                System.out.println(" OK, I've marked this task as not done yet:");
                System.out.println("   " + tasks[i]);
                System.out.println(LINE);
            } else if (command.startsWith("todo ")) {
                String description = command.substring("todo ".length());
                addTask(new Todo(description));
            } else if (command.startsWith("deadline ")) {
                int byIndex = command.indexOf(" /by ");
                String description = command.substring("deadline ".length(), byIndex);
                String by = command.substring(byIndex + " /by ".length());
                addTask(new Deadline(description, by));
            } else if (command.startsWith("event ")) {
                int fromIndex = command.indexOf(" /from ");
                int toIndex = command.indexOf(" /to ");
                String description = command.substring("event ".length(), fromIndex);
                String from = command.substring(fromIndex + " /from ".length(), toIndex);
                String to = command.substring(toIndex + " /to ".length());
                addTask(new Event(description, from, to));
            } else {
                addTask(new Task(command));
            }
        }
    }
}
