package skylee.storage;

import static skylee.ui.Message.MESSAGE_IO_EXCEPTION;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import skylee.exception.SkyleeException;
import skylee.parser.Parser;
import skylee.task.TaskList;

/**
 * Defines the basic methods for file storage.
 */
public class Storage {
    /**
     * Returns the task list read from the file, or an empty
     * task list if the file does not exist or cannot be parsed properly.
     *
     * @return  The task list read from the file, or an empty task list.
     */
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

    /**
     * Saves the tasks into the file.
     *
     * @param tasks             The tasks to be saved.
     * @throws SkyleeException
     */
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
