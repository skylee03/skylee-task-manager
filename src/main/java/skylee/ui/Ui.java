package skylee.ui;

import static skylee.ui.Message.LINE;
import static skylee.ui.Message.MESSAGE_HELLO;
import static skylee.ui.Message.PREFIX_EXCEPTION;
import static skylee.ui.Message.PREFIX_MESSAGE;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Defines the behavior of the CLI.
 */
public class Ui {
    private final Scanner in;
    private final PrintStream out;

    /**
     * Constructs a <code>Ui</code> object, whose input <code>in</code>
     * and output <code>out</code> is the standard input and the standard
     * output, respectively.
     */
    public Ui() {
        this(System.in, System.out);
    }

    /**
     * Constructs a <code>Ui</code> object, whose input is an <code>InputStream</code>
     * object <code>in</code> and output is an <code>PrintStream</code> object <code>out</code>.
     *
     * @param in    The <code>InputStream</code> accepting the user's input.
     * @param out   The <code>PrintStream</code> displaying the program's output.
     */
    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Returns the user's input.
     *
     * @return The user's input.
     */
    public String getUserCommand() {
        out.print("> ");
        return in.nextLine();
    }

    /**
     * Shows the <code>messages</code> in a beautiful format.
     *
     * @param messages  The messages to be shown.
     */
    public void showMessages(String... messages) {
        out.print(LINE);
        for (String message : messages) {
            out.println(PREFIX_MESSAGE + message);
        }
        out.println(LINE);
    }

    /**
     * Shows message for exception <code>e</code>.
     *
     * @param e The exception whose message will be shown.
     */
    public void showException(Exception e) {
        showMessages(PREFIX_EXCEPTION + e.getMessage());
    }

    /**
     * Shows the welcome message.
     */
    public void showWelcome() {
        showMessages(MESSAGE_HELLO);
    }
}
