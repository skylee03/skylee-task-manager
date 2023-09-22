package skylee.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static skylee.ui.Message.LINE;
import static skylee.ui.Message.PREFIX_MESSAGE;

public class Ui {
    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public String getUserCommand() {
        return in.nextLine();
    }

    public void showMessages(String... messages) {
        out.print(LINE);
        for (String message : messages) {
            out.println(PREFIX_MESSAGE + message);
        }
        out.println(LINE);
    }
}
