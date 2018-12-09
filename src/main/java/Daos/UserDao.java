package Daos;


import Classes.User;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import Interface.Dao;
import org.bson.Document;
import sample.DbManager;

import java.util.List;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

public class UserDao implements Dao<User> {

    MongoDatabase db = DbManager.getDb("FBDB");
    MongoCollection usersCollection = db.getCollection("users");



    public UserDao(){

    }

    @Override
    public List<User> getAll() {
        List<User> users = (List) usersCollection.find();
        return users;
    }

    @Override
    public void save(User user) {
        Document userDoc = new Document();
        userDoc.put("email", user.getEmail());
        userDoc.put("password", user.getPassword());


    }

    @Override
    public void update(User user, String[] params) {

    }

    @Override
    public void delete(User user) {
        usersCollection.deleteOne(eq("email",user.getEmail()));

    }
}
