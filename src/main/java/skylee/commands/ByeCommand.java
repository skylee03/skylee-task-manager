package skylee.commands;

import static skylee.ui.Message.MESSAGE_BYE;

import skylee.exception.SkyleeException;
import skylee.task.TaskList;

/**
 * Defines the basic fields and methods of a bye command.
 */
public class ByeCommand extends Command {
    /**
     * Returns <code>true</code> if this is a <code>ByeCommand</code> object, otherwise returns <code>false</code>.
     *
     * @return <code>true</code> if this is a <code>ByeCommand</code> object, otherwise returns <code>false</code>.
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Returns the bye message to be shown to the user.
     *
     * @param tasks The current task list.
     * @return      The messages to be shown to the user.
     * @throws SkyleeException
     */
    public String[] execute(TaskList tasks) throws SkyleeException {
        return new String[] {MESSAGE_BYE};
    }
}
