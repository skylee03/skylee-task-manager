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

        String[] commandList = new String[100];
        int commandCount = 0;

        System.out.println(helloMsg);
        for (String command;;) {
            command = scanner.nextLine();
            if (command.equals("bye")) {
                System.out.println(byeMsg);
                break;
            } else if (command.equals("list")) {
                System.out.print("____________________________________________________________\n");
                for (int i = 0; i < commandCount; i++) {
                    System.out.println(" " + (i + 1) + ". " + commandList[i]);
                }
                System.out.println("____________________________________________________________\n");
            } else {
                commandList[commandCount] = command;
                commandCount++;
                System.out.print("____________________________________________________________\n");
                System.out.println(" added: " + command);
                System.out.println("____________________________________________________________\n");
            }
        }
    }
}
