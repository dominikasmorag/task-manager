package usertools;

import database.CategoryDAO;
import database.TaskDAO;
import task.Status;
import task.TaskEntity;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;

public class TaskModifier {
    private final Connection conn;
    protected static TaskDAO taskDAO;
    protected static CategoryDAO categoryDAO;

    public TaskModifier(Connection conn) {
        this.conn = conn;
        taskDAO = new TaskDAO(conn);
        categoryDAO = new CategoryDAO(conn);
    }


    public void changeStatus() {
        try {
            List<TaskEntity> tasks = taskDAO.findAllWithField(Status.PENDING);
            for(TaskEntity task : tasks) {
                System.out.println("Id: " +task.getId() + ".\nTitle: " + task.getTitle() + ",\nDescription: " + task.getDescription() + ",\nDue date: " + task.getDueDate() + "\n============");
            }
            System.out.println("Enter id of desired task: ");
            Scanner sc = new Scanner(System.in);
            sc.nextLine();
            int taskId = sc.nextInt();
            TaskEntity taskEntity = taskDAO.findById(taskId).get();
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
