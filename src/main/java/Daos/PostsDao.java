package Daos;

import Classes.Posts;
import Interface.Dao;
import org.bson.Document;

import java.util.List;

public class PostsDao implements Dao<Posts> {
    @Override
    public List<Posts> getAll() {
        return null;
    }

    @Override
    public Document find(Posts posts) {
        return null;
    }


    @Override
    public void save(Posts posts) {

    }

    @Override
    public void update(Posts posts, String[] params) {

    }

    @Override
    public void delete(Posts posts) {

    }
}
