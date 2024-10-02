package in.roopsai.shellDemo;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

@ShellComponent
public class Commands {

    private final AtomicInteger idGenerator = new AtomicInteger(0);

    private final TaskList taskList = new TaskList();

    private final TaskListJsonWriter jsonWriter = new TaskListJsonWriter();


    public void writeToFile() {
        // Check if the file exists, if not create and populate it
        File file = new File("src/main/resources/tasks.json");
        if (!file.exists()) {
            try {

                boolean created = file.createNewFile();
                if (!created) {
                    System.out.println("File creation failed");
                } else {
                    jsonWriter.writeTaskListToFile(taskList, "tasks");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {

            jsonWriter.writeTaskListToFile(taskList, "tasks");
        }

    }

    @ShellMethod(value = "Add Operation")
    public void add(@ShellOption(defaultValue = "") String arg) {
        if (arg.isBlank()) {
            System.out.println("Please enter task");

        } else {
            int id = idGenerator.getAndIncrement();
            Task task = new Task(id, arg, "todo", new Date().toString(), new Date().toString());
            taskList.addTask(task);
            System.out.println(task);
            writeToFile();

        }
    }

    @ShellMethod(value = "Update Operation")
    public void update(@ShellOption(defaultValue = "") String id, String description) {
        if (id.isBlank()) {
            System.out.println("Id should be provided!");
        } else {
            try {
                Integer i = Integer.parseInt(id);
                if (description.isBlank()) {
                    System.out.println("Description should be provided!");
                }
                Task task = taskList.getTasks().stream().filter(t -> t.id().equals(i)).findFirst().orElse(null);
                if (task != null) {
                    Task updatedTask = new Task(task.id(), description, task.status(), task.createdAt(), new Date().toString());
                    taskList.getTasks().remove(task);
                    taskList.addTask(task);
                    System.out.println(updatedTask);
                    writeToFile();
                } else {
                    System.out.println("No such task with id: " + id);
                }
            } catch (NumberFormatException e) {
                System.out.println(e);

            }
        }
    }

    @ShellMethod(value = "Mark In Progress")
    public void markInProgress(@ShellOption(defaultValue = "") String arg) {
        if (arg.isBlank()) {
            System.out.println("Please enter task id");
        } else {
            try {
                int i = Integer.parseInt(arg);
                Task task = taskList.getTasks().stream().filter(t -> t.id().equals(i)).findFirst().orElse(null);

                if (task != null) {
                    Task updatedTask = new Task(task.id(), task.description(), "mark-in-progress", task.createdAt(), new Date().toString());
                    taskList.getTasks().remove(task);
                    taskList.addTask(task);
                    System.out.println(updatedTask);
                    writeToFile();
                } else {
                    System.out.println("No such task with id: " + i);
                }


            } catch (NumberFormatException e) {
                System.out.println(e);
            }
        }
    }

    @ShellMethod(value = "Done")
    public void done(@ShellOption(defaultValue = "") String arg) {
        if (arg.isBlank()) {
            System.out.println("Please enter task id");
        } else {
            try {
                int i = Integer.parseInt(arg);
                Task task = taskList.getTasks().stream().filter(t -> t.id().equals(i)).findFirst().orElse(null);

                if (task != null) {
                    Task updatedTask = new Task(task.id(), task.description(), "done", task.createdAt(), new Date().toString());
                    taskList.getTasks().remove(task);
                    taskList.addTask(task);
                    System.out.println(updatedTask);
                    writeToFile();
                } else {
                    System.out.println("No such task with id: " + i);
                }


            } catch (NumberFormatException e) {
                System.out.println(e);
            }
        }
    }

    @ShellMethod(value = "List Operation")
    public void list(@ShellOption(defaultValue = "") String arg) {
        taskList.getTasks().forEach(System.out::println);
    }

    @ShellMethod(value = "Delete Operation")
    public void delete(@ShellOption(defaultValue = "") String id) {
        if (id.isBlank()) {
            System.out.println("Provide an id!");
        } else {
            try {
                Integer i = Integer.parseInt(id);
                // Search the list using id
                Task task = taskList.getTasks().stream().filter(t -> t.id().equals(i)).findFirst().orElse(null);
                if (task != null) {
                    System.out.println("Removing task " + task);
                    taskList.getTasks().remove(task);
                    writeToFile();
                } else {
                    System.out.println("Task with id " + i + " Not found!");
                }

            } catch (NumberFormatException e) {
                System.out.println(e);
            }

        }

    }


}
