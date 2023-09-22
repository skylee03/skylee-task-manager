package skylee;

import skylee.commands.Command;
import skylee.exception.SkyleeException;
import skylee.parser.Parser;
import skylee.storage.Storage;
import skylee.task.TaskList;
import skylee.ui.Ui;

public class Skylee {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    public Skylee() {
        ui = new Ui();
        storage = new Storage();
        tasks = storage.loadFile();
    }

    public static void main(String[] args) {
        new Skylee().run();
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while(!isExit) {
            final String rawUserInput = ui.getUserCommand();
            try {
                final Command command = Parser.parseCommand(rawUserInput);
                final String[] feedback = command.execute(tasks);
                ui.showMessages(feedback);
                isExit = command.isExit();
            } catch (SkyleeException e) {
                ui.showMessages(e.getMessage());
            }
        }
        try {
            storage.saveFile(tasks);
        } catch (SkyleeException e) {
            ui.showMessages(e.getMessage());
        }
    }
}
