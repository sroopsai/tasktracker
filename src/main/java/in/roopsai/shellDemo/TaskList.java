package in.roopsai.shellDemo;

import java.util.ArrayList;
import java.util.List;

public record TaskList(List<Task> tasks) {

    public TaskList() {
        this(new ArrayList<Task>());
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

   public List<Task> getTasks() {
        return tasks;
   }
}
