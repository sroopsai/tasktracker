package in.roopsai;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class Task {

    private static final AtomicInteger counter = new AtomicInteger();
    // Fields
    private final long id;

    private String description;

    private Status status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Task(String description) {
        this.id = counter.getAndIncrement();
        this.status = Status.TODO;
        this.description = description;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

}
