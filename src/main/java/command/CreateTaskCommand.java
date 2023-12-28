package command;

import database.CategoryDAO;
import database.TaskDAO;
import task.PriorityLevel;
import task.TaskEntity;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.DateTimeException;
import java.util.Scanner;

public class CreateTaskCommand implements Command {
    private final TaskDAO taskDAO;
    private final CategoryDAO categoryDAO;

    public CreateTaskCommand(TaskDAO taskDAO, CategoryDAO categoryDAO) {
        this.taskDAO = taskDAO;
        this.categoryDAO = categoryDAO;
    }
    public void execute() {
        Scanner sc = new Scanner(System.in);
        System.out.print("TaskEntity title: ");
        String title = sc.nextLine();
        System.out.print("TaskEntity description: ");
        String description = sc.nextLine();
        String[] date;
        String[] time;
        Timestamp dueDate;
        try {
            System.out.print("TaskEntity due date (yyyy.MM.dd): ");
            date = sc.next().trim().split("\\.");
            System.out.print("TaskEntity due hour (hh:mm) ");
            time = sc.next().trim().split(":");
            dueDate = new Timestamp(Integer.parseInt(date[0])-1900, Integer.parseInt(date[1])-1, Integer.parseInt(date[2]), Integer.parseInt(time[0]), Integer.parseInt(time[1]), 0, 0);
        } catch (DateTimeException | NumberFormatException ex) {
            System.err.println("Invalid value. it needs to be in yyyy.MM.dd hh:mm format.\nDue date is set to 2100.01.01 00:00 now");
            dueDate = new Timestamp(2100-1900, 1-1, 1, 0, 0, 0, 0);
        }
        System.out.print("CategoryEntity: ");
        sc.nextLine();
        String categoryName = sc.nextLine();
        System.out.print("Priority level [low, medium, high]: ");
        PriorityLevel priorityLevel = PriorityLevel.valueOf(sc.next().trim().toUpperCase());
        TaskEntity task = new TaskEntity(title, description, dueDate, categoryDAO.findByName(categoryName), priorityLevel);
        System.out.println("TASK: " + task);
        try {
            taskDAO.insert(task);
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }
}
