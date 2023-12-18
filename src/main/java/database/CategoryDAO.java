package database;

import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;
import task.Category;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

import static java.lang.System.err;

public class CategoryDAO implements DAO<Category> {

    private Connection connection;
    private static final String INSERT_QUERY = "INSERT INTO categories (name, creationDate) VALUES (?, ?)";
    private PreparedStatement insertPrepStatement;

    public CategoryDAO(Connection connection) throws SQLException {
        this.connection = connection;
    }
    @Override
    public Optional<Category> get(int id) {
        return Optional.empty();
    }

    @Override
    public void insert(Category category) throws SQLException {
        category.setCreationDate(LocalDateTime.now());
        insertPrepStatement =  connection.prepareStatement(INSERT_QUERY);
        insertPrepStatement.setString(1, category.getCategoryName());
        insertPrepStatement.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));

        try {
            insertPrepStatement.executeUpdate();
        } catch (JdbcSQLIntegrityConstraintViolationException ex) {
            System.err.println("Unique index or primary key violation");
        }
    }

    public int getIdByName(String name) throws SQLException {
        String query = "SELECT id, name FROM categories WHERE name = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, name);
        ResultSet rs = statement.executeQuery();
        rs.next();
        int one = rs.getInt(1);
        String two = rs.getString(2);
        System.out.println("one: " + one + "\ntwo: " + two);
        System.out.println("rs: " + rs);
        return 10;
    }
}
