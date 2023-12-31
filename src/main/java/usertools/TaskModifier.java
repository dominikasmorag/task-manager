package usertools;

import database.CategoryDAO;
import database.TaskDAO;
import task.CategoryEntity;
import task.PriorityLevel;
import task.Status;
import task.TaskEntity;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskModifier {
    private final Connection conn;
    private final TaskDAO taskDAO;
    private final CategoryDAO categoryDAO;

    public TaskModifier(Connection conn) {
        this.conn = conn;
        taskDAO = new TaskDAO(conn);
        categoryDAO = new CategoryDAO(conn);
    }

    public void createTask() {
        Scanner sc = new Scanner(System.in);
        String title = createTitle(sc);
        String description = createDescription(sc);
        Timestamp dueDate = createDueDate(sc);
        CategoryEntity category = assignCategoryEntity(sc);
        PriorityLevel priorityLevel = assignPriorityLevel(sc);
        TaskEntity task = new TaskEntity(title, description, dueDate, category, priorityLevel);
        System.out.println("TASK: " + task);
        try {
            taskDAO.insert(task);
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }

    private String createTitle(Scanner sc) {
        System.out.print("TaskEntity title: ");
        return sc.nextLine();
    }

    private String createDescription(Scanner sc) {
        System.out.print("Description: ");
        return sc.nextLine();
    }

    private Timestamp createDueDate(Scanner sc) {
        String[] date;
        String[] time;
        Timestamp dueDate = new Timestamp(1, 1, 1, 1, 1, 1, 1);
        try {
            System.out.print("TaskEntity due date (yyyy.MM.dd): ");
            date = sc.next().trim().split("\\.");
            System.out.print("TaskEntity due hour (hh:mm) ");
            time = sc.next().trim().split(":");
            dueDate = new Timestamp(Integer.parseInt(date[0])-1900, Integer.parseInt(date[1])-1, Integer.parseInt(date[2]), Integer.parseInt(time[0]), Integer.parseInt(time[1]), 0, 0);
        } catch (DateTimeException | NumberFormatException | ArrayIndexOutOfBoundsException ex) {
            System.err.println("Invalid value. it needs to be in yyyy.MM.dd hh:mm format.\nDue date is set to 2100.01.01 00:00 now");
            createDueDate(sc);
        }
        return dueDate;
    }

    private CategoryEntity assignCategoryEntity(Scanner sc) {
        List<CategoryEntity> availableCategories = categoryDAO.findAll();
        List<String> names = new ArrayList<>();
        for(CategoryEntity cat : availableCategories) {
            names.add(cat.getName().toUpperCase());
        }
        System.out.println("Available categories: " + names);
        System.out.println("Category: ");
        String category = sc.nextLine();
        if(availableCategories.contains(category.toUpperCase())) {
            return categoryDAO.findByName(category);
        }
        else {
            CategoryEntity anotherEntity = new CategoryEntity(category);
            categoryDAO.insert(anotherEntity);
            System.out.println("New category " + category + " created");
            return anotherEntity;
        }
    }

    private PriorityLevel assignPriorityLevel(Scanner sc) {
        PriorityLevel pl = PriorityLevel.MEDIUM;
        System.out.println("Priority level [LOW, MEDIUM, HIGH]: ");
        try {
            pl = PriorityLevel.valueOf(sc.next());
        } catch (Exception ex) {
            System.err.println("There is no such priority level, try again.");
            assignCategoryEntity(sc);
        }
        return pl;
    }
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
