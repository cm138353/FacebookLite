package Interface;

import org.bson.Document;

import java.util.List;

public interface Dao<T> {
    List<T> getAll();

    Document find(T t);

    void save(T t);

    void update(T t, String[] params);

    void delete(T t);
}
