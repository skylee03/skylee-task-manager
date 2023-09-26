package skylee.commands;

import static skylee.ui.Message.MESSAGE_ID_OUT_OF_RANGE;
import static skylee.ui.Message.MESSAGE_UNMARK;
import static skylee.ui.Message.PREFIX_TASK;

import skylee.exception.SkyleeException;
import skylee.task.TaskList;

/**
 * Defines the basic fields and methods of an unmark command.
 */
public class UnmarkCommand extends Command {
    private int taskId;

    /**
     * Constructs an <code>UnmarkCommand</code> object with the index of the task to be unmarked.
     *
     * @param taskId  The index of the task to be unmarked.
     */
    public UnmarkCommand(int taskId) {
        this.taskId = taskId;
    }

    /**
     * Unmark the task in the current task list,
     * and returns the messages to be shown to the user.
     *
     * @param tasks The current task list.
     * @return      The messages to be shown to the user.
     */
    public String[] execute(TaskList tasks) throws SkyleeException {
        if (taskId < 0 || taskId >= tasks.size()) {
            throw new SkyleeException(MESSAGE_ID_OUT_OF_RANGE);
        }
        tasks.get(taskId).setDone(false);
        return new String[]{MESSAGE_UNMARK, PREFIX_TASK + tasks.get(taskId)};
    }
}
