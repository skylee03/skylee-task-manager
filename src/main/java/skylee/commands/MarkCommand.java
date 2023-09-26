package skylee.commands;

import static skylee.ui.Message.MESSAGE_ID_OUT_OF_RANGE;
import static skylee.ui.Message.MESSAGE_MARK;
import static skylee.ui.Message.PREFIX_TASK;

import skylee.exception.SkyleeException;
import skylee.task.TaskList;

/**
 * Defines the basic fields and methods of a mark command.
 */
public class MarkCommand extends Command {
    private int taskId;

    /**
     * Constructs a <code>MarkCommand</code> object with the index of the task to be marked.
     *
     * @param taskId  The index of the task to be marked.
     */
    public MarkCommand(int taskId) {
        this.taskId = taskId;
    }

    /**
     * Mark the task in the current task list,
     * and returns the messages to be shown to the user.
     *
     * @param tasks The current task list.
     * @return      The messages to be shown to the user.
     */
    public String[] execute(TaskList tasks) throws SkyleeException {
        if (taskId < 0 || taskId >= tasks.size()) {
            throw new SkyleeException(MESSAGE_ID_OUT_OF_RANGE);
        }
        tasks.get(taskId).setDone(true);
        return new String[]{MESSAGE_MARK, PREFIX_TASK + tasks.get(taskId)};
    }
}
