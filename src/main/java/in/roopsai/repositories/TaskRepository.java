package in.roopsai.repositories;

import in.roopsai.models.Status;
import in.roopsai.models.Task;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class TaskRepository {

    private static final String FILE_NAME = "tasks.json";
    private static final Map<Long, Task> tasksCache = new HashMap<>();

    static {
        try {
            initializeRepository();
        } catch (IOException e) {
            throw new RuntimeException("Failed to initialize cache");
        }
    }

    private static void initializeRepository() throws IOException {
        Path path = Path.of(FILE_NAME);
        if (!Files.exists(path)) {
            Files.createFile(path);
            Files.writeString(path, "[]");
        } else {
            readTasksFromFile();
            long maxId = tasksCache.keySet().stream()
                    .mapToLong(Long::longValue)
                    .max()
                    .orElse(0L);

        }
    }

    private static void readTasksFromFile() {
        Path path = Path.of(FILE_NAME);
        try (var br = new BufferedReader(new FileReader(path.toFile()))) {
            String json = br.lines().collect(Collectors.joining());
            String[] tasks = json.substring(1, json.length() - 1).split("},\\{");
            for (String t : tasks) {
                var task = parseTaskFromJson(t);
                tasksCache.put(task.id(), task);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Task parseTaskFromJson(String task) {
        String[] fields = task.split(",");

        Map<String, String> fieldMap = new HashMap<>();
        for (String field : fields) {
            String[] keyValuePair = field
                    .replace("{", "")
                    .replace("}", "")
                    .split(":", 2);
            String key = keyValuePair[0];
            String value = keyValuePair[1];
            fieldMap.put(key, value);
        }
        return new Task(Long.parseLong(fieldMap.get("id")), fieldMap.get("description"), Status.valueOf(fieldMap.get("status")), LocalDateTime.parse(fieldMap.get("createdAt")), LocalDateTime.parse(fieldMap.get("updatedAt")));

    }

    private static void saveTask(Task task) {
        tasksCache.put(task.id(), task);
        writeTasksToFile();
    }

    private static void writeTasksToFile() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        int count = 0;
        for (Task task : tasksCache.values()) {
            if (count > 0) sb.append(",");
            sb.append("{");
            sb.append("\"id\":\"").append(task.id()).append("\",");
            sb.append("\"description\":\"").append(task.description()).append("\",");
            sb.append("\"status\":\"").append(task.status()).append("\",");
            sb.append("\"createdAt\":\"").append(task.createdAt()).append("\",");
            sb.append("\"updatedAt\":\"").append(task.updatedAt()).append("\",");

            sb.append("}");
            count++;
        }
        sb.append("]");

        Path path = Path.of(FILE_NAME);
        try (var bw = new BufferedWriter(new FileWriter(path.toFile()))) {
            bw.write(sb.toString());
        } catch (IOException e) {
            throw new RuntimeException("Cannot connect to the file");
        }
    }
}
