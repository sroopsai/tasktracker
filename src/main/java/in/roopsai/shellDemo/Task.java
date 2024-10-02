package in.roopsai.shellDemo;

import java.util.Date;

public record Task(Integer id, String description, String status, String createdAt, String updatedAt) {


    @Override
    public String toString() {
        return "Task [id=" + id + ", description=" + description + ", status=" + status + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt;
    }
}
