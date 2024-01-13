package database;

import task.PriorityLevel;
import task.Status;
import task.TaskEntity;
import task.TaskWithCategory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TaskDAO implements DAO<TaskEntity>{
    private final Connection connection;
    private final CategoryDAO categoryDAO;
    private static final String INSERT_QUERY = "INSERT INTO tasks (title, description, dueDate, status, categoryId, priorityLevel, creationDate) VALUES (?, ?, ?, ?, ?, ?, ?)";

    public TaskDAO(Connection connection) {
        this.connection = connection;
        this.categoryDAO = new CategoryDAO(connection);
    }
    public Optional<TaskEntity> findById(int id) {
        TaskEntity taskEntity = null;
        String query = "SELECT id, title, description, dueDate, status, categoryId, priorityLevel, creationDate FROM " + DataBase.TASKS_TABLE_NAME + " WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                taskEntity = new TaskEntity(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getTimestamp(4),
                        Status.valueOf(rs.getString(5)),
                        categoryDAO.findById(rs.getInt(6)).get(),
                        PriorityLevel.valueOf(rs.getString(7)),
                        rs.getTimestamp(8)
                );
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Optional.ofNullable(taskEntity);
    }

    public void updateStatus(TaskEntity task) {
        String query = "UPDATE " + DataBase.TASKS_TABLE_NAME + " SET status = 'COMPLETED' WHERE id = ?;";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, task.getId());
            statement.executeUpdate();
            System.out.println("TASK UPDATED ");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
    public List<TaskEntity> findAll() {
        ArrayList<TaskEntity> list = new ArrayList<>();
        String query = "SELECT id, title, description, dueDate, status, categoryId, priorityLevel, creationDate FROM " + DataBase.TASKS_TABLE_NAME + ";";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                list.add(new TaskEntity(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getTimestamp(4),
                        Status.valueOf(rs.getString(5)),
                        categoryDAO.findById(rs.getInt(6)).get(),
                        PriorityLevel.valueOf(rs.getString(7)),
                        rs.getTimestamp(8)));
            }
        } catch (SQLException ex) {
            System.err.println("SQLException at TaskDao.findAll(). Returning empty list");
        }
        return  list;
    }

    public List<TaskEntity> findAllWithField(Status status) throws SQLException {
        List<TaskEntity> list = new ArrayList<>();
        String query = "SELECT id, title, description, dueDate, status, categoryId, priorityLevel, creationDate FROM tasks WHERE status = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, status.name());
        ResultSet rs = statement.executeQuery();
        while(rs.next()) {
            list.add(new TaskEntity(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getTimestamp(4),
                    Status.valueOf(rs.getString(5)),
                    categoryDAO.findById(rs.getInt(6)).get(),
                    PriorityLevel.valueOf(rs.getString(7)),
                    rs.getTimestamp(8)
            ));
        }
        return list;
    }

    public List<TaskEntity> findAllWithField(PriorityLevel priorityLevel) {
        String priorityLevelName = priorityLevel.name().toUpperCase();
        List<TaskEntity> list = new ArrayList<>();
        try {
            String query = "SELECT id, title, description, dueDate, status, categoryId, priorityLevel, creationDate FROM tasks WHERE UPPER(priorityLevel) = UPPER(?);";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, priorityLevelName);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                list.add(new TaskEntity(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getTimestamp(4),
                        Status.valueOf(rs.getString(5)),
                        categoryDAO.findById(rs.getInt(6)).get(),
                        PriorityLevel.valueOf(rs.getString(7)),
                        rs.getTimestamp(8)
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return list;
    }

    public void save(TaskEntity task) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(INSERT_QUERY);
        statement.setString(1, task.getTitle());
        statement.setString(2, task.getDescription());
        statement.setTimestamp(3, task.getDueDate());
        statement.setString(4, task.getStatus().name());
        statement.setInt(5, categoryDAO.findIdByName(task.getCategory().getName()));
        statement.setString(6, task.getPriorityLevel().name());
        statement.setTimestamp(7, task.getCreationDate());
        statement.executeUpdate();
    }

}
