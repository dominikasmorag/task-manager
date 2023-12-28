package usertools;

import task.Status;
import task.TaskEntity;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class TaskModifier {

    public static void completeTask(TaskEntity task, boolean isCompleted) {
        if(isCompleted) {
            task.setStatus(Status.COMPLETED);
        }
        else {
            task.setStatus(Status.PENDING);
        }
    }

    public static void changeTitle(TaskEntity task, String title) {
        task.setTitle(title);
    }

    public static void changeDescription(TaskEntity task, String description) {
        task.setDescription(description);
    }

    public static void changeDueDate(TaskEntity task, Timestamp dueDate) {
        task.setDueDate(dueDate);
    }

}
