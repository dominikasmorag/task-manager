package task;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class TaskEntity extends Entity {
    public static List<TaskEntity> tasks;
    private String title;
    private String description;
    private Timestamp dueDate;
    private CategoryEntity category;

    private PriorityLevel priorityLevel;
    private Status status;

    public TaskEntity(String title, String description, Timestamp dueDate, CategoryEntity category, PriorityLevel priorityLevel) {
        super(Timestamp.valueOf(LocalDateTime.now()));
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.category = category;
        this.priorityLevel = priorityLevel;
        this.status = Status.PENDING;
    }

    public TaskEntity(int id, String title, String description, Timestamp dueDate, Status status, CategoryEntity category, PriorityLevel priorityLevel, Timestamp creationDate) {
        super(id, creationDate);
        this.title = title;
        this.description = description;
        this.status = status;
        this.dueDate = dueDate;
        this.category = category;
        this.priorityLevel = priorityLevel;
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

    public Timestamp getDueDate() {
        return dueDate;
    }

    public void setDueDate(Timestamp dueDate) {
        this.dueDate = dueDate;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
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
        return "TaskEntity{" +
                "id=" + this.getId() +'\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", dueDate=" + dueDate +
                ", category=" + category +
                ", priorityLevel=" + priorityLevel +
                ", status=" + status +
                ", creationDate='"+ getCreationDate() +
                '}';
    }
}
