package skylee.commands;

import static skylee.ui.Message.MESSAGE_LIST;

import skylee.task.TaskList;

/**
 * Defines the basic fields and methods of a list command.
 */
public class ListCommand extends Command {
    /**
     * Returns messages showing the tasks in the current task list.
     *
     * @param tasks The current task list.
     * @return      The messages showing the tasks in the current task list.
     */
    public String[] execute(TaskList tasks) {
        String[] messages = new String[tasks.size() + 1];
        messages[0] = MESSAGE_LIST;
        for (int i = 0; i < tasks.size(); i++) {
            messages[i + 1] = (i + 1) + "." + tasks.get(i);
        }
        return messages;
    }
}
