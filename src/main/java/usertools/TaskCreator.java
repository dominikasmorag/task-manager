package usertools;

import task.CategoryEntity;
import task.PriorityLevel;
import task.TaskEntity;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskCreator extends TaskModifier {
    public TaskCreator(Connection conn) {
        super(conn);
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
        Timestamp dueDate;
        try {
            System.out.print("TaskEntity due date (yyyy.MM.dd): ");
            date = sc.next().trim().split("\\.");
            System.out.print("TaskEntity due hour (hh:mm) ");
            time = sc.next().trim().split(":");
            dueDate = new Timestamp(Integer.parseInt(date[0])-1900, Integer.parseInt(date[1])-1, Integer.parseInt(date[2]), Integer.parseInt(time[0]), Integer.parseInt(time[1]), 0, 0);
        } catch (DateTimeException | NumberFormatException | ArrayIndexOutOfBoundsException ex) {
            System.err.println("Invalid value. it needs to be in yyyy.MM.dd hh:mm format.\nDue date is set to 2100.01.01 00:00 now");
            sc.nextLine();
            return new Timestamp(2100-1900, 1, 1, 0, 0, 0, 0);
        }
        sc.nextLine();
        return dueDate;
    }

    private CategoryEntity assignCategoryEntity(Scanner sc) {
        List<CategoryEntity> availableCategories = categoryDAO.findAll();
        List<String> names = new ArrayList<>();
        for(CategoryEntity cat : availableCategories) {
            names.add(cat.getName().toUpperCase());
        }
        System.out.println("Available categories: " + names);
        System.out.print("Category: ");
        String category = sc.nextLine();
        if(names.contains(category.toUpperCase())) {
            System.out.println("CATEGORY IN names.contains: " + category);
            CategoryEntity categoryEntity = categoryDAO.findByName(category.toUpperCase()).get();
            System.out.println("CategoeryEntty = " + categoryEntity.toString());
            return categoryEntity;
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
        String priority = sc.next().toUpperCase();
        try {
            pl = PriorityLevel.valueOf(priority);
        } catch (Exception ex) {
            System.err.println("There is no such priority level. It's set to MEDIUM now.");

        }
        return pl;
    }

}
