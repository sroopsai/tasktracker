package in.roopsai.models;

import java.time.LocalDateTime;

public record Task(Long id, String description, Status status, LocalDateTime createdAt, LocalDateTime updatedAt) {

}
