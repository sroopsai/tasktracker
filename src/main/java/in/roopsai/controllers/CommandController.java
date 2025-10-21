package in.roopsai.controllers;

import in.roopsai.models.Status;
import in.roopsai.models.Task;
import in.roopsai.services.TaskService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class CommandController {

    private final TaskService taskService = new TaskService();

    public void start() {
        System.out.println("Welcome to TaskTracker CLI Tool!");
        System.out.println("Type 'help' to see available commands.\n");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print(">");
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Goodbye \uD83D\uDC4B");
                break;
            }
            if (input.isEmpty()) continue;
            try {
                handleCommand(input);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void handleCommand(String input) {
        String[] parts = input.split(" ", 2);
        String command = parts[0];
        String args = parts.length > 1 ? parts[1] : "";

        switch (command.toLowerCase()) {
            case "add" -> handleAdd(args);
            case "update" -> handleUpdate(args);
            case "delete" -> handleDelete(args);
            case "mark-done" -> handleStatusUpdate(args, Status.DONE);
            case "mark-in-progress" -> handleStatusUpdate(args, Status.INPROGRESS);
            case "list" -> handleList(args);
            case "help" -> printHelp();
            default -> System.out.println("Unknown command. Type 'help' to see available commands.");
        }
    }

    private void handleUpdate(String args) {
        String[] parts = args.split(" ", 2);
        if (parts.length < 2) {
            System.out.println("Usage: update <id> \"New description\"");
        }

        try {
            long id = Long.parseLong(parts[0]);
            String description = extractQuotedText(parts[1]);
            Optional<Task> updated = taskService.updateTask(id, description);
            if (updated.isPresent()) {
                System.out.println("✅ Task updated successfully.");
            } else {
                System.out.println("❌ Task with ID " + id + " not found.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format");
        }
    }

    private void handleDelete(String args) {


        try {
            long id = Long.parseLong(args.trim());
            boolean deleted = taskService.deleteTask(id);
            if (deleted) {
                System.out.println("Task with id " + id + " got deleted");
            } else {
                System.out.println("Task with id " + id + " not found");
            }
        } catch (NumberFormatException e) {
            System.out.println("Usage: delete <id>");
        }
    }

    private void handleStatusUpdate(String args, Status status) {
        try {
            long id = Long.parseLong(args.trim());
            Optional<Task> updatedTask = taskService.updateStatus(id, status);
            if (updatedTask.isPresent()) {
                System.out.println("Task " + id + " marked as " + status);
            } else {
                System.out.println("Task with ID " + " not found");
            }

        } catch (NumberFormatException ex) {
            System.out.println("Usage: mark-done <id> or mark-in-progress <id>");
        }
    }

    private void printHelp() {
        System.out.println("""
                Available commands:
                add "Task description"
                update <id> "New description"
                delete <id>
                mark-in-progress <id>
                mark-done <id>
                list [done|todo|in-progress]
                exit
                """);
    }

    private void handleList(String args) {
        List<Task> tasks;
        switch (args.trim().toLowerCase()) {
            case "done" -> tasks = taskService.getTasksByStatus(Status.DONE);
            case "todo" -> tasks = taskService.getTasksByStatus(Status.TODO);
            case "in-progress" -> tasks = taskService.getTasksByStatus(Status.INPROGRESS);
            default -> tasks = taskService.getAllTasks();
        }

        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
        } else {
            System.out.println("\n Task List:");
            tasks.forEach(task -> System.out.printf("[%d] %-20s %-12s Created: %s Updated: %s%n", task.id(), task.description(), task.status(), task.createdAt(), task.updatedAt()));
            System.out.println();
        }

    }

    private void handleAdd(String args) {
        String description = extractQuotedText(args);
        if (description.isEmpty()) {
            System.out.println("Usage: add \"Task description\"");
            return;
        }
        Task newTask = taskService.addTask(description);
        System.out.println("✅ Task added successfully (ID: " + newTask.id() + ")\"");
    }

    private String extractQuotedText(String input) {
        int start = input.indexOf('"');
        int end = input.lastIndexOf('"');
        if (start != -1 && end > start) {
            return input.substring(start + 1, end);
        }
        return "";
    }
}
