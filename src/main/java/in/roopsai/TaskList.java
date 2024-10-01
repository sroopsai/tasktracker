package in.roopsai;

import java.util.List;
import java.util.Map;

public class TaskList {

    private Map<Integer, Task> taskMap;

    private List<Task> taskList;

    public void add(Task task) {
        taskMap.put(task.getId(), task);
        taskList.add(task);
    }

    public void update(int id, String title) {
        Task task = taskMap.get(id);
        task.setTitle(title);
    }

    public void delete(int id) {
        taskMap.remove(id);
    }

    public void mark(int id, String status) {
        Task task = taskMap.get(id);
        task.setStatus(status);

    }

    public void list() {
        taskList.stream().forEach(System.out::println);
    }

    public void list(String status) {

        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            if (task.getStatus().equals(status)) {
                System.out.println(task);
            }
        }

    }

}
