package skylee;

import skylee.commands.Command;
import skylee.exception.SkyleeException;
import skylee.parser.Parser;
import skylee.storage.Storage;
import skylee.task.TaskList;
import skylee.ui.Ui;

/**
 * Defines the basic structure and the behavior of the task manager.
 *
 * @author  Yang Ming-Tian
 */
public class Skylee {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    /**
     * Constructs an <code>Skylee</code> object.
     * If the archive file exists and can be parsed correctly,
     * <code>tasks</code> will contain records from that file;
     * otherwise, it will be an empty <code>TaskList</code>.
     */
    public Skylee() {
        ui = new Ui();
        storage = new Storage();
        tasks = storage.loadFile();
    }

    /**
     * Creates a `Skylee` object and runs it.
     *
     * @param args  Arguments obtained from the command line.
     */
    public static void main(String[] args) {
        new Skylee().run();
    }

    /**
     * Displays the welcome interface, continuously reads user input
     * and executes corresponding instructions until exiting.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            final String rawUserInput = ui.getUserCommand();
            try {
                final Command command = Parser.parseCommand(rawUserInput);
                final String[] feedback = command.execute(tasks);
                ui.showMessages(feedback);
                isExit = command.isExit();
            } catch (SkyleeException e) {
                ui.showException(e);
            }
        }
        try {
            storage.saveFile(tasks);
        } catch (SkyleeException e) {
            ui.showException(e);
        }
    }
}
