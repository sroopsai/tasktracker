package in.roopsai.shellDemo;

import org.springframework.context.annotation.Bean;

import java.util.Date;

public record Task(Integer id, String description, String status, Date createdAt, Date updatedAt) {


    @Override
    public String toString() {
        return "Task [id=" + id + ", description=" + description + ", status=" + status + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt;
    }
}
