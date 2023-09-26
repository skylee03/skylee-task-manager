package skylee.commands;

import static skylee.ui.Message.MESSAGE_DATE;

import java.time.LocalDate;
import java.util.ArrayList;

import skylee.task.Task;
import skylee.task.TaskList;

/**
 * Defines the basic fields and methods of a date command.
 */
public class DateCommand extends Command {
    private LocalDate date;

    /**
     * Constructs a <code>DateCommand</code> object with the date to retrieve.
     *
     * @param date  The date to retrieve.
     */
    public DateCommand(LocalDate date) {
        this.date = date;
    }

    /**
     * Retrieve tasks occurring on the given date in the current task list,
     * and returns the messages to be shown to the user.
     *
     * @param tasks The current task list.
     * @return      The messages to be shown to the user.
     */
    public String[] execute(TaskList tasks) {
        ArrayList<String> messages = new ArrayList<>();
        messages.add(MESSAGE_DATE);
        for (int i = 0; i < tasks.size(); i++) {
            final Task task = tasks.get(i);
            if (task.isOccurringOn(date)) {
                messages.add((i + 1) + "." + tasks.get(i));
            }
        }
        return messages.toArray(new String[0]);
    }
}
