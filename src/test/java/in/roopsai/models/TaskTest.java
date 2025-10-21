package in.roopsai.models;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    @Test
    public void testTaskCreation() {
        var now = LocalDateTime.now();
        var task = new Task(1L, "Break down the ice", Status.TODO, now, now);
        assertEquals(1L, task.id());
        assertEquals("Break down the ice", task.description());
        assertEquals(Status.TODO, task.status());
        assertEquals(now, task.createdAt());
        assertEquals(now, task.updatedAt());
    }

    @Test
    public void testEqualityBetweenTasks() {
        var now = LocalDateTime.now();
        var task1 = new Task(1L, "Break down the ice", Status.TODO, now, now);
        var task2 = new Task(1L, "Break down the ice", Status.TODO, now, now);
        assertEquals(task1, task2);
        assertEquals(task1.hashCode(), task2.hashCode());
    }

}
