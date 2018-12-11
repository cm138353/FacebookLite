package Daos;

import Classes.Posts;
import Interface.Dao;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import sample.DbManager;

import java.util.List;

public class PostsDao implements Dao<Posts> {


    MongoDatabase db = DbManager.getDb("FBDB");
    MongoCollection postsCollection = db.getCollection("posts");

    @Override
    public List<Posts> getAll() {
        return null;
    }

    @Override
    public Document find(String e) {
        return null;
    }


    @Override
    public void save(Posts post) {
        Document postDoc = new Document();
        postDoc.put("credId", post.getID());
        postDoc.put("content", post.getContent());
        postDoc.put("isHidden", post.getHidden());
        postsCollection.insertOne(postDoc);
    }

    @Override
    public void update(Posts post, String[] params) {

    }

    @Override
    public void delete(Posts post) {

    }
}