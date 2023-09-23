package skylee.commands;

import skylee.task.Task;
import skylee.task.TaskList;

import java.time.LocalDate;
import java.util.ArrayList;

import static skylee.ui.Message.MESSAGE_DATE;

public class DateCommand extends Command {
    private LocalDate date;

    public DateCommand(LocalDate date) {
        this.date = date;
    }

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
