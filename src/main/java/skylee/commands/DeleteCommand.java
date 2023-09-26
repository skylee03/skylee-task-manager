package skylee.commands;

import static skylee.ui.Message.MESSAGE_COUNT;
import static skylee.ui.Message.MESSAGE_DELETE;
import static skylee.ui.Message.MESSAGE_ID_OUT_OF_RANGE;
import static skylee.ui.Message.PREFIX_TASK;

import skylee.exception.SkyleeException;
import skylee.task.Task;
import skylee.task.TaskList;

/**
 * Defines the basic fields and methods of a delete command.
 */
public class DeleteCommand extends Command {
    private int taskId;

    /**
     * Constructs a <code>DeleteCommand</code> object with the index of the task to be deleted.
     *
     * @param taskId  The index of the task to be deleted.
     */
    public DeleteCommand(int taskId) {
        this.taskId = taskId;
    }

    /**
     * Delete the task from the current task list,
     * and returns the messages to be shown to the user.
     *
     * @param tasks The current task list.
     * @return      The messages to be shown to the user.
     */
    public String[] execute(TaskList tasks) throws SkyleeException {
        if (taskId < 0 || taskId >= tasks.size()) {
            throw new SkyleeException(MESSAGE_ID_OUT_OF_RANGE);
        }
        final Task task = tasks.get(taskId);
        tasks.remove(taskId);
        return new String[]{MESSAGE_DELETE, PREFIX_TASK + task,
                String.format(MESSAGE_COUNT, tasks.size(), tasks.size() > 1 ? "s" : "")};
    }
}
