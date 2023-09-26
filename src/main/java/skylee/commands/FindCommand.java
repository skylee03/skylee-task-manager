package skylee.commands;

import static skylee.ui.Message.MESSAGE_FIND;

import java.util.ArrayList;

import skylee.exception.SkyleeException;
import skylee.task.Task;
import skylee.task.TaskList;

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
            if (containsIgnoreCase(task.getDescription(), keyword)) {
                messages.add((i + 1) + "." + tasks.get(i));
            }
        }
        return messages.toArray(new String[0]);
    }

    /**
     * Checks if <code>String</code> contains a search <code>String</code> irrespective of case.
     *
     * @param str           The <code>String</code> to check.
     * @param searchStr     The <code>String</code> to find.
     * @return              <code>true</code> if the <code>String</code> contains the search <code>String</code>
     *                      irrespective of case or <code>false</code> if not
     */
    private static boolean containsIgnoreCase(String str, String searchStr) {
        return str.toLowerCase().contains(searchStr.toLowerCase());
    }
}
