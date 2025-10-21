package in.roopsai.services;

import in.roopsai.models.Status;
import in.roopsai.models.Task;
import in.roopsai.repositories.TaskRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class TaskService {

    private final TaskRepository taskRepository = new TaskRepository();

    public Task addTask(String description) {
        Task newTask = new Task(description);
        return taskRepository.saveTask(newTask);
    }

    public Optional<Task> updateTask(Long id, String description) {
        Optional<Task> task = taskRepository.fetchTask(id);
        if (task.isPresent()) {
            Task oldTask = task.get();
            Task updatedTask = new Task(oldTask.id(), description, oldTask.status(), oldTask.createdAt(), LocalDateTime.now());
            taskRepository.saveTask(updatedTask);
            task = Optional.of(updatedTask);
        }
        return task;

    }

    public Optional<Task> updateStatus(Long id, Status status) {
        Optional<Task> task = taskRepository.fetchTask(id);
        if (task.isPresent()) {
            Task oldTask = task.get();
            Task updatedTask = new Task(oldTask.id(), oldTask.description(), Status.DONE, oldTask.createdAt(), LocalDateTime.now());
            taskRepository.saveTask(updatedTask);
            task = Optional.of(updatedTask);
        }

        return task;
    }

    public List<Task> getTasksByStatus(Status status) {
        return taskRepository.fetchTasks(status);
    }

    public List<Task> getAllTasks() {
        return taskRepository.fetchAllTasks();
    }

    public boolean deleteTask(Long id) {
        Optional<Task> deletedTask = taskRepository.deleteTask(id);
        return deletedTask.isPresent();
    }

}
