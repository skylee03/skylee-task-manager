package skylee.commands;

import skylee.exception.SkyleeException;
import skylee.task.TaskList;

/**
 * Defines the basic methods of a command.
 */
public abstract class Command {
    /**
     * Executes the command with the current task list,
     * and returns the messages to be shown to the user.
     *
     * @param tasks             The current task list.
     * @return                  The messages to be shown to the user.
     * @throws SkyleeException
     */
    public abstract String[] execute(TaskList tasks) throws SkyleeException;

    /**
     * Returns <code>true</code> if this is a <code>ByeCommand</code> object, otherwise returns <code>false</code>.
     *
     * @return <code>true</code> if this is a <code>ByeCommand</code> object, otherwise returns <code>false</code>.
     */
    public boolean isExit() {
        return false;
    }
}
