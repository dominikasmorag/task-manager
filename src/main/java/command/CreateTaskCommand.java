package command;

import database.CategoryDAO;
import database.TaskDAO;
import task.PriorityLevel;
import task.Task;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class CreateTaskCommand implements Command {
    private final TaskDAO taskDAO;
    private final CategoryDAO categoryDAO;

    public CreateTaskCommand(TaskDAO taskDAO, CategoryDAO categoryDAO) {
        this.taskDAO = taskDAO;
        this.categoryDAO = categoryDAO;
      //  Category category, LocalDateTime creationDate, PriorityLevel
      //  priorityLevel
    }
    public void execute() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        int year, month, day, hour, minute;
        Scanner sc = new Scanner(System.in);
        System.out.print("Task title: ");
        String title = sc.nextLine();
        System.out.print("Task description: ");
        String description = sc.nextLine();
        String[] date;
        String[] time;
        LocalDateTime dueDate;
        try {
            System.out.print("Task due date (yyyy.MM.dd): ");
            date = sc.next().trim().split("\\.");
            System.out.print("Task due hour (hh:mm) ");
            time = sc.next().trim().split(":");
            dueDate = LocalDateTime.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]), Integer.parseInt(time[0]), Integer.parseInt(time[1]));
        } catch (DateTimeException | NumberFormatException ex) {
            System.err.println("Invalid value. it needs to be in yyyy.MM.dd hh:mm format.\nDue date is set to 2100.01.01 00:00 now");
            dueDate = LocalDateTime.of(2100,01,01,00,00);
        }
        System.out.print("Category: ");
        sc.nextLine();
        String categoryName = sc.nextLine();
        System.out.print("Priority level [low, medium, high]: ");
        PriorityLevel priorityLevel = PriorityLevel.valueOf(sc.next().trim().toUpperCase());
        Task task = new Task(title, description, dueDate, categoryDAO.findByName(categoryName), priorityLevel);
        System.out.println("TASKKK: " + task);
    }
}
