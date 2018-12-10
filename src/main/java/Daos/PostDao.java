package Daos;

import Classes.Post;
import Interface.Dao;
import org.bson.Document;

import java.util.List;

public class PostDao implements Dao<Post> {
    @Override
    public List<Post> getAll() {
        return null;
    }

    @Override
    public Document find(Post post) {
        return null;
    }


    @Override
    public void save(Post post) {

    }

    @Override
    public void update(Post post, String[] params) {

    }

    @Override
    public void delete(Post post) {

    }
}
