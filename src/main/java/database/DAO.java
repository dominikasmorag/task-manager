package database;

import java.sql.SQLException;
import java.util.Optional;

public interface DAO<T> {

    Optional<T> get(int id);

    void insert(T t) throws SQLException;

}
