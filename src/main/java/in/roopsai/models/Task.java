package in.roopsai.models;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;

public record Task(Long id, String description, Status status, LocalDateTime createdAt, LocalDateTime updatedAt) {

    private final static AtomicLong counter = new AtomicLong(0L);

    public Task(String description, Status status) {
        this(counter.getAndIncrement(), description, status, LocalDateTime.now(), LocalDateTime.now());
    }

    public Task(String description) {
        this(counter.getAndIncrement(), description, Status.TODO, LocalDateTime.now(), LocalDateTime.now());
    }

    public static void initializeCounter(long id) {
        counter.set(id);
    }
}
