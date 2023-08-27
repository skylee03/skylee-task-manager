import java.util.Scanner;

public class Skylee {
    public static void main(String[] args) {
        String helloMsg = "____________________________________________________________\n" +
                " Hello! I'm Skylee!\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n";
        String byeMsg = "____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n";

        Scanner scanner = new Scanner(System.in);

        var tasks = new Task[100];
        int taskCount = 0;

        System.out.println(helloMsg);
        for (String command;;) {
            command = scanner.nextLine();
            if (command.equals("bye")) {
                System.out.println(byeMsg);
                break;
            } else if (command.equals("list")) {
                System.out.print("____________________________________________________________\n");
                System.out.println(" Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println(" " + (i + 1) + ".[" + tasks[i].getStatusIcon() + "] " + tasks[i].getDescription());
                }
                System.out.println("____________________________________________________________\n");
            } else if (command.startsWith("mark ")) {
                int i = Integer.parseInt(command.substring(5)) - 1;
                tasks[i].markAsDone();
                System.out.print("____________________________________________________________\n");
                System.out.println(" Nice! I've marked this task as done:");
                System.out.println("   [X] " + tasks[i].getDescription());
                System.out.println("____________________________________________________________\n");
            } else if (command.startsWith("unmark ")) {
                int i = Integer.parseInt(command.substring(7)) - 1;
                tasks[i].unmarkAsNotDone();
                System.out.print("____________________________________________________________\n");
                System.out.println(" OK, I've marked this task as not done yet:");
                System.out.println("   [ ] " + tasks[i].getDescription());
                System.out.println("____________________________________________________________\n");
            } else {
                tasks[taskCount] = new Task(command);
                taskCount++;
                System.out.print("____________________________________________________________\n");
                System.out.println(" added: " + command);
                System.out.println("____________________________________________________________\n");
            }
        }
    }
}
