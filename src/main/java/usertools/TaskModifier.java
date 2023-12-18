package usertools;

import task.Status;
import task.Task;

import java.time.LocalDateTime;
import java.util.Date;

public class TaskModifier {

    public static void completeTask(Task task, boolean isCompleted) {
        if(isCompleted) {
            task.setStatus(Status.COMPLETED);
        }
        else {
            task.setStatus(Status.PENDING);
        }
    }

    public static void changeTitle(Task task, String title) {
        task.setTitle(title);
    }

    public static void changeDescription(Task task, String description) {
        task.setDescription(description);
    }

    public static void changeDueDate(Task task, LocalDateTime dueDate) {
        task.setDueDate(dueDate);
    }

}
