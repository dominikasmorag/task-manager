package usertools;

import database.CategoryDAO;
import database.TaskDAO;
import task.Status;
import task.TaskEntity;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

public class TaskModifier {
    protected static TaskDAO taskDAO;
    protected static CategoryDAO categoryDAO;

    public TaskModifier(Connection conn) {
        taskDAO = new TaskDAO(conn);
        categoryDAO = new CategoryDAO(conn);

    }


    public void changeStatus() {
        try {
            List<TaskEntity> tasks = taskDAO.findAllWithField(Status.PENDING);
            Set<Integer> idSet = new HashSet<>();
            for(TaskEntity task : tasks) {
                System.out.println("Id: " +task.getId() + ".\nTitle: " + task.getTitle() + ",\nDescription: " + task.getDescription() + ",\nDue date: " + task.getDueDate() + "\n============");
                idSet.add(task.getId());
            }
            System.out.println("Enter id of desired task: ");
            Scanner sc = new Scanner(System.in);
            int taskId = sc.nextInt();
            TaskEntity taskEntity = new TaskEntity();
            if(idSet.contains(taskId)) {
                taskEntity = taskDAO.findById(taskId).get();
            }
            else {
                System.out.println("There is no element with such id");
                changeStatus();
            }
            taskEntity.setStatus(Status.COMPLETED);
            taskDAO.updateStatus(taskEntity);
        } catch (SQLException ex) {
            ex.printStackTrace();
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
