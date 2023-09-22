package skylee.commands;

import skylee.exception.SkyleeException;
import skylee.task.TaskList;

import static skylee.ui.Message.MESSAGE_ID_OUT_OF_RANGE;
import static skylee.ui.Message.MESSAGE_MARK;
import static skylee.ui.Message.PREFIX_TASK;

public class MarkCommand extends Command {
    private int taskId;

    public MarkCommand(int taskId) {
        this.taskId = taskId;
    }

    public String[] execute(TaskList tasks) throws SkyleeException {
        if (taskId < 0 || taskId >= tasks.size()) {
            throw new SkyleeException(MESSAGE_ID_OUT_OF_RANGE);
        }
        tasks.get(taskId).setDone(true);
        return new String[]{MESSAGE_MARK,
                PREFIX_TASK + tasks.get(taskId)};
    }
}
