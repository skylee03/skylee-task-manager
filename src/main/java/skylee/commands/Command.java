package skylee.commands;

import skylee.exception.SkyleeException;
import skylee.task.TaskList;

public abstract class Command {
    public abstract String[] execute(TaskList tasks) throws SkyleeException;

    public boolean isExit() {
        return false;
    }
}
