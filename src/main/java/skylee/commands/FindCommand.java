package skylee.commands;

import skylee.exception.SkyleeException;
import skylee.task.Task;
import skylee.task.TaskList;

import java.util.ArrayList;

import static skylee.ui.Message.MESSAGE_FIND;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

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
