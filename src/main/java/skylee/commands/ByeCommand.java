package skylee.commands;

import skylee.exception.SkyleeException;
import skylee.task.TaskList;

import static skylee.ui.Message.MESSAGE_BYE;

public class ByeCommand extends Command {
    @Override
    public boolean isExit() {
        return true;
    }

    public String[] execute(TaskList tasks) throws SkyleeException {
        return new String[] {MESSAGE_BYE};
    }
}
