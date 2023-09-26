package skylee.commands;

import skylee.exception.SkyleeException;
import skylee.task.Task;
import skylee.task.TaskList;

import java.util.ArrayList;

import static skylee.ui.Message.MESSAGE_FIND;

/**
 * Defines the basic fields and methods of a find command.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructs a <code>FindCommand</code> object with the keyword to retrieve.
     *
     * @param keyword   The keyword to retrieve.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Retrieve tasks containing the given keyword in the current task list,
     * and returns the messages to be shown to the user.
     *
     * @param tasks The current task list.
     * @return      The messages to be shown to the user.
     */
    @Override
    public String[] execute(TaskList tasks) throws SkyleeException {
        ArrayList<String> messages = new ArrayList<>();
        messages.add(MESSAGE_FIND);
        for (int i = 0; i < tasks.size(); i++) {
            final Task task = tasks.get(i);
            if (task.getDescription().contains(keyword)) {
                messages.add((i + 1) + "." + tasks.get(i));
            }
        }
        return messages.toArray(new String[0]);
    }
}
