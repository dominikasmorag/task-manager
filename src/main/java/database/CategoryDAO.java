package database;

import task.CategoryEntity;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;


public class CategoryDAO implements DAO<CategoryEntity> {
    public Map<String, Integer> categoriesMap;
    private final Connection connection;
    private static final String INSERT_QUERY = "INSERT INTO categories (name, creationDate) VALUES (?, ?)";
    private PreparedStatement insertPrepStatement;

    public CategoryDAO(Connection connection) {
        this.connection = connection;
        categoriesMap = updateMap();

    }
    @Override
    public Optional<CategoryEntity> findById(int id) {
        CategoryEntity categoryEntity = new CategoryEntity();
        try {
            String query = "SELECT id, name, creationDate FROM categories WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            rs.next();
            categoryEntity = new CategoryEntity(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getTimestamp(3)
            );
        } catch (SQLException ex) {
            System.err.println("SQLException at CategoryEntity findById(int id)");
        }
        return Optional.ofNullable(categoryEntity);
    }

    public List<CategoryEntity> findAll() {
        List<CategoryEntity> list = new ArrayList<>();
        try {
            String query = "SELECT id, name, creationDate FROM categories";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                list.add(new CategoryEntity(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getTimestamp(3)
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    @Override
    public void save(CategoryEntity category){
        try {
            category.setCreationDate(Timestamp.valueOf(LocalDateTime.now()));
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
        String query = "SELECT id FROM categories WHERE UPPER(name) = ?";
        name = name.toUpperCase();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, name);
        ResultSet rs = statement.executeQuery();
        rs.next();
        int id = rs.getInt(1);
        return id;
    }

    public Optional<CategoryEntity> findByName(String name) {
        CategoryEntity category = new CategoryEntity();
        String query = "SELECT id, name, creationDate FROM categories WHERE UPPER(name) LIKE UPPER(?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            rs.next();
            category.setId(rs.getInt(1));
            category.setName(rs.getString(2));
            category.setCreationDate(rs.getTimestamp(3));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Optional.ofNullable(category);
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
