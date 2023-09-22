package skylee.commands;

import skylee.task.TaskList;

import static skylee.ui.Message.MESSAGE_LIST;

public class ListCommand extends Command {
    public String[] execute(TaskList tasks) {
        String[] messages = new String[tasks.size() + 1];
        messages[0] = MESSAGE_LIST;
        for (int i = 0; i < tasks.size(); i++) {
            messages[i + 1] = (i + 1) + "." + tasks.get(i);
        }
        return messages;
    }
}
