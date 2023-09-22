package skylee.commands;

import skylee.exception.SkyleeException;
import skylee.task.Task;
import skylee.task.TaskList;

import static skylee.ui.Message.MESSAGE_COUNT;
import static skylee.ui.Message.MESSAGE_DELETE;
import static skylee.ui.Message.MESSAGE_ID_OUT_OF_RANGE;
import static skylee.ui.Message.PREFIX_TASK;

public class DeleteCommand extends Command {
    private int taskId;

    public DeleteCommand(int taskId) {
        this.taskId = taskId;
    }

    public String[] execute(TaskList tasks) throws SkyleeException {
        if (taskId < 0 || taskId >= tasks.size()) {
            throw new SkyleeException(MESSAGE_ID_OUT_OF_RANGE);
        }
        final Task task = tasks.get(taskId);
        tasks.remove(taskId);
        return new String[]{MESSAGE_DELETE,
                PREFIX_TASK + task,
                String.format(MESSAGE_COUNT, tasks.size(), tasks.size() > 1 ? "s" : "")};
    }
}
