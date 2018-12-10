package Daos;

import Classes.Post;
import Interface.Dao;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import sample.DbManager;

import java.util.List;

public class PostDao implements Dao<Post> {


    MongoDatabase db = DbManager.getDb("FBDB");
    MongoCollection postsCollection = db.getCollection("posts");

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
        Document postDoc = new Document();
        postDoc.put("credId", post.getID());
        postDoc.put("content", post.getContent());
        postDoc.put("isHidden", post.getHidden());
        postsCollection.insertOne(postDoc);
    }

    @Override
    public void update(Post post, String[] params) {

    }

    @Override
    public void delete(Post post) {

    }
}
