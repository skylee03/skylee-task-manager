package skylee.task;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Defines the basic methods of a task list.
 */
public class TaskList extends ArrayList<Task> {
    /**
     * Returns a parser-friendly string representing the task list.
     *
     * @return  A parser-friendly string representing the task list.
     */
    public String show() {
        return this.stream()
                .map(task -> task.show())
                .collect(Collectors.joining("\n"));
    }
}
