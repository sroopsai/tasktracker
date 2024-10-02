package in.roopsai.shellDemo;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class TaskListJsonWriter {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void writeTaskListToFile(TaskList taskList, String fileName) {
        try {
            File file = new File("src/main/resources/" + fileName + ".json");
            objectMapper.writeValue(file, taskList);
            System.out.println("TaskList written to file: " + file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
