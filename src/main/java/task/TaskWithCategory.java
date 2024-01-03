package task;

import java.sql.Timestamp;

public class TaskWithCategory extends Entity {
    private String title;
    private String description;
    private Timestamp dueDate;
    private Status status;
    private String categoryString;
    private PriorityLevel priorityLevel;

    public TaskWithCategory(int id, String title, String description, Timestamp dueDate, Status status, String categoryString, PriorityLevel priorityLevel, Timestamp creationDate) {
        super(id, creationDate);
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.status = status;
        this.categoryString = categoryString;
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

    public String getCategoryString() {
        return categoryString;
    }

    public void setCategoryString(String categoryString) {
        this.categoryString = categoryString;
    }

    public PriorityLevel getPriorityLevel() {
        return priorityLevel;
    }

    public void setPriorityLevel(PriorityLevel priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TaskWithCategory{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", dueDate=" + dueDate +
                ", status=" + status +
                ", categoryString='" + categoryString + '\'' +
                ", priorityLevel=" + priorityLevel +
                '}';
    }
}
