package task;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class Task extends Entity {
    public static List<Task> tasks;
    private String title;
    private String description;
    private LocalDateTime dueDate;
    private Category category;

    private PriorityLevel priorityLevel;
    private Status status;

    public Task(String title, String description, LocalDateTime dueDate, Category category, PriorityLevel priorityLevel) {
        super(LocalDateTime.now());
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.category = category;
        this.priorityLevel = priorityLevel;
        this.status = Status.PENDING;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public PriorityLevel getPriorityLevel() { return priorityLevel; }

    public void setPriorityLevel(PriorityLevel priorityLevel) { this.priorityLevel = priorityLevel; }

    @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", dueDate=" + dueDate +
                ", category=" + category +
                ", priorityLevel=" + priorityLevel +
                ", status=" + status +
                '}';
    }
}
