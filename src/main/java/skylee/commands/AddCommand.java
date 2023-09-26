package skylee.commands;

import static skylee.ui.Message.MESSAGE_ADD;
import static skylee.ui.Message.MESSAGE_COUNT;
import static skylee.ui.Message.PREFIX_TASK;

import skylee.task.Task;
import skylee.task.TaskList;

/**
 * Defines the basic fields and methods of an add command.
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Constructs an <code>AddCommand</code> object with the task to be added.
     *
     * @param task  The task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Add the task to the current task list,
     * and returns the messages to be shown to the user.
     *
     * @param tasks The current task list.
     * @return      The messages to be shown to the user.
     */
    @Override
    public String[] execute(TaskList tasks) {
        tasks.add(task);
        return new String[]{MESSAGE_ADD, PREFIX_TASK + task,
                String.format(MESSAGE_COUNT, tasks.size(), tasks.size() > 1 ? "s" : "")};
    }
}
