package skylee.storage;

import skylee.exception.SkyleeException;
import skylee.parser.Parser;
import skylee.task.Task;
import skylee.task.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static skylee.ui.Message.MESSAGE_IO_EXCEPTION;

public class Storage {
    public TaskList loadFile() {
        TaskList tasks = new TaskList();
        try {
            File file = new File(Config.PATH_SAVE);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                tasks.add(Parser.parseTask(scanner.nextLine()));
            }
            return tasks;
        } catch (Exception e) {
            return new TaskList();
        }
    }

    public void saveFile(TaskList tasks) throws SkyleeException {
        try {
            File file = new File(Config.PATH_SAVE);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(tasks.show());
            fileWriter.close();
        } catch (IOException e) {
            throw new SkyleeException(MESSAGE_IO_EXCEPTION);
        }
    }
}
