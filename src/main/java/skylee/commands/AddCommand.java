package skylee.commands;

import skylee.task.Task;
import skylee.task.TaskList;

import static skylee.ui.Message.MESSAGE_ADD;
import static skylee.ui.Message.MESSAGE_COUNT;
import static skylee.ui.Message.PREFIX_TASK;

public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public String[] execute(TaskList tasks) {
        tasks.add(task);
        return new String[]{MESSAGE_ADD,
                PREFIX_TASK + task,
                String.format(MESSAGE_COUNT, tasks.size(), tasks.size() > 1 ? "s" : "")};
    }
}
