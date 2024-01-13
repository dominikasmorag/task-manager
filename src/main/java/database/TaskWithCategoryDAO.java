package database;

import task.PriorityLevel;
import task.Status;
import task.TaskWithCategory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TaskWithCategoryDAO implements DAO<TaskWithCategory> {
    private final Connection connection;
    public TaskWithCategoryDAO(Connection connection) {
        this.connection = connection;
    }

    public List<TaskWithCategory> findAllByCategory(String categoryName) {
        List<TaskWithCategory> list = new ArrayList<>();
        try {
            String query = "SELECT Tasks.id, Tasks.title, Tasks.description, Tasks.dueDate, Tasks.status, Categories.name, Tasks.priorityLevel, Tasks.creationDate " +
                    "FROM tasks " +
                    "INNER JOIN Categories " +
                    "ON Tasks.categoryId = Categories.id " +
                    "WHERE UPPER(Categories.name) = UPPER(?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, categoryName);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                list.add(new TaskWithCategory(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getTimestamp(4),
                        Status.valueOf(rs.getString(5)),
                        rs.getString(6),
                        PriorityLevel.valueOf(rs.getString(7)),
                        rs.getTimestamp(8)
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return list;
    }

    @Override
    public void save(TaskWithCategory taskWithCategory) throws SQLException {

    }

    @Override
    public Optional<TaskWithCategory> findById(int id) {
        return Optional.empty();
    }
}
