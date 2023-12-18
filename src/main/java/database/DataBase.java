package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataBase {
    private static final String TASKS_TABLE_NAME = "tasks";
    private static final String TASKS_TABLE_CREATION = "create table if not exists " + TASKS_TABLE_NAME +
            " (id INT AUTO_INCREMENT PRIMARY KEY NOT NULL," +
            "title VARCHAR(100) NOT NULL," +
            "description VARCHAR(300) NOT NULL," +
            "dueDate date NOT NULL, " +
            "status ENUM('PENDING', 'COMPLETED'), " +
            "categoryId INT," +
            "priorityLeve ENUM('LOW', 'MEDIUM', 'HIGH'), " +
            "FOREIGN KEY (categoryId) REFERENCES categories(id));";

    private static final String CATEGORIES_TABLE_NAME = "categories";
    private static final String CATEGORIES_TABLE_CREATION = "create table if not exists " + CATEGORIES_TABLE_NAME +
            " (id INT AUTO_INCREMENT PRIMARY KEY NOT NULL," +
            "name VARCHAR(100) unique," +
            "creationDate DATE);";

    public static void createSchema(Connection connection) throws SQLException {
        createCategoriesTable(connection);
        createTasksTable(connection);
    }

    private static void createTasksTable(Connection connection) throws SQLException {
        PreparedStatement createTasks = connection.prepareStatement(TASKS_TABLE_CREATION);
        createTasks.execute();
    }

    private static void createCategoriesTable(Connection connection) throws SQLException {
        PreparedStatement createCategories = connection.prepareStatement(CATEGORIES_TABLE_CREATION);
        createCategories.execute();
    }
}
