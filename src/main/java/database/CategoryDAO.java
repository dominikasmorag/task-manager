package database;

import org.h2.jdbc.JdbcSQLNonTransientException;
import task.Category;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


public class CategoryDAO implements DAO<Category> {
    public Map<String, Integer> categoriesMap;
    private Connection connection;
    private static final String INSERT_QUERY = "INSERT INTO categories (name, creationDate) VALUES (?, ?)";
    private PreparedStatement insertPrepStatement;

    public CategoryDAO(Connection connection) {
        this.connection = connection;
        categoriesMap = updateMap();

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
            insertPrepStatement.setString(1, category.getName());
            insertPrepStatement.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            insertPrepStatement.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("SQLException at 'insert' method in CategoryDAO");
            ex.printStackTrace();
        }
    }

    public int findIdByName(String name) throws SQLException {
        String query = "SELECT id FROM categories WHERE name = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, name);
        ResultSet rs = statement.executeQuery();
        rs.next();
        int id = rs.getInt(1);
        return id;
    }

    public Category findByName(String name) {
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

     public Map<String, Integer> updateMap() {
        HashMap<String, Integer> map = new HashMap<>();
        String query = "SELECT name, id FROM categories";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                map.put(rs.getString(1), rs.getInt(2));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return map;
    }
}
