package database;

import com.mysql.cj.x.protobuf.MysqlxPrepare;
import task.Task;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TaskDAO implements DAO<Task>{
//String title, String description, LocalDateTime dueDate, Category category, PriorityLevel priorityLevel
    private static final String INSERT_QUERY = "INSERT INTO tasks (title, description, dueDate, category, priorityLevel) VALUES (?, ?)";
    List<Task> taskList = new ArrayList<>();

    public TaskDAO() {
        taskList = getAllRecords();
    }
    @Override
    public Optional<Task> get(int id) {
        return null;
    }

    public void insert(Task task) {
    }

    private List<Task> getAllRecords() {
        return new ArrayList<Task>();
        }
    }
