package database;

import java.sql.SQLException;
import java.util.Optional;

public interface DAO<T> {

    void save(T t) throws SQLException;

    Optional<T> findById(int id);

}
