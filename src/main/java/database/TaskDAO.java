package database;

import task.Task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Optional;

public class TaskDAO implements DAO<Task>{
    private final Connection connection;
    private final CategoryDAO categoryDAO;
    private static final String INSERT_QUERY = "INSERT INTO tasks (title, description, dueDate, status, categoryId, priorityLevel, creationDate) VALUES (?, ?, ?, ?, ?, ?, ?)";

    public TaskDAO(Connection connection) {
        this.connection = connection;
        this.categoryDAO = new CategoryDAO(connection);
    }
    @Override
    public Optional<Task> get(int id) {
        return null;
    }

    public void insert(Task task) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(INSERT_QUERY);
        statement.setString(1, task.getTitle());
        statement.setString(2, task.getDescription());
        statement.setTimestamp(3, Timestamp.valueOf(task.getDueDate()));
        statement.setString(4, task.getStatus().name());
        statement.setInt(4, categoryDAO.findIdByName(task.getCategory().getName()));
        statement.setString(5, task.getPriorityLevel().name());
        statement.setTimestamp(6, Timestamp.valueOf(task.getCreationDate()));
        statement.executeUpdate();
    }

}
