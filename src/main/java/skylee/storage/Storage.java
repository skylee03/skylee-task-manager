package skylee.storage;

import skylee.exception.SkyleeException;
import skylee.parser.Parser;
import skylee.task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static skylee.ui.Message.MESSAGE_IO_EXCEPTION;

public class Storage {
    public ArrayList<Task> loadFile() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File(Config.PATH_SAVE);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                tasks.add(Parser.parseTask(scanner.nextLine()));
            }
            return tasks;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public void saveFile(ArrayList<Task> tasks) throws SkyleeException {
        try {
            File file = new File(Config.PATH_SAVE);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file);
            for (Task task: tasks) {
                fileWriter.write(task.show() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new SkyleeException(MESSAGE_IO_EXCEPTION);
        }
    }
}
