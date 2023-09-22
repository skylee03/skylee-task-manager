package skylee.task;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class TaskList extends ArrayList<Task> {
    public String show() {
        return this.stream()
                .map(task -> task.show())
                .collect(Collectors.joining("\n"));
    }
}
