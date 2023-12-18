package command;

import database.TaskDAO;
import task.Category;
import task.PriorityLevel;
import task.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

public class CreateTaskCommand implements Command {
    private TaskDAO dao;
    public CreateTaskCommand(TaskDAO dao) {

      //  Category category, LocalDateTime creationDate, PriorityLevel
      //  priorityLevel
    }
    public void execute() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        int year, month, day, hour, minute;
        this.dao = dao;
        Scanner sc = new Scanner(System.in);
        System.out.print("Task title: ");
        String title = sc.nextLine();
        System.out.print("Task description: ");
        String description = sc.nextLine();
        System.out.print("Task due date (yyyy.MM.dd): ");
        String[] date = sc.next().trim().split("\\.");
        System.out.print("Task due hour (hh:mm) ");
        String[] time = sc.next().trim().split(":");
        LocalDateTime dueDate = LocalDateTime.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]), Integer.parseInt(time[0]), Integer.parseInt(time[1]));
        System.out.print("Category: ");
        sc.nextLine();
        String categoryName = sc.nextLine();
        System.out.print("Priority level [low, medium, high]: ");
        PriorityLevel priorityLevel = PriorityLevel.valueOf(sc.next().trim().toUpperCase());
        Task task = new Task(title, description, dueDate, new Category(categoryName), priorityLevel);
        System.out.println("TASKKK: " + task);
    }
}
