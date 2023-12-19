package database;

import org.h2.jdbc.JdbcSQLNonTransientException;
import task.Category;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Optional;


public class CategoryDAO implements DAO<Category> {

    private Connection connection;
    private static final String INSERT_QUERY = "INSERT INTO categories (name, creationDate) VALUES (?, ?)";
    private PreparedStatement insertPrepStatement;

    public CategoryDAO(Connection connection) {
        this.connection = connection;
    }
    @Override
    public Optional<Category> get(int id) {
        return Optional.empty();
    }

    @Override
    public void insert(Category category){
        try {
            category.setCreationDate(LocalDateTime.now());
            insertPrepStatement = connection.prepareStatement(INSERT_QUERY);
            insertPrepStatement.setString(1, category.getCategoryName());
            insertPrepStatement.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            insertPrepStatement.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("SQLException at 'insert' method in CategoryDAO");
        }
    }

    public int getIdByName(String name) throws SQLException {
        String query = "SELECT id FROM categories WHERE name = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, name);
        ResultSet rs = statement.executeQuery();
        rs.next();
        int id = rs.getInt(1);
        return id;
    }

    public Category getCategoryByName(String name) {
        Category category = new Category();
        String query = "SELECT name FROM categories WHERE name = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            try {
                rs.next();
                category = new Category(rs.getString(1));
            } catch (JdbcSQLNonTransientException ex) {
                System.err.println("this category didn't exist but it is created now.");
                category = new Category(name);
                insert(category);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return category;
    }
}
