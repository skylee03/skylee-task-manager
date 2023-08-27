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

        System.out.println(helloMsg);
        for (String command;;) {
            command = scanner.nextLine();
            if (command.equals("bye")) {
                System.out.println(byeMsg);
                break;
            } else {
                String msg = "____________________________________________________________\n" +
                        " " + command + "\n" +
                        "____________________________________________________________\n";
                System.out.println(msg);
            }
        }
    }
}
