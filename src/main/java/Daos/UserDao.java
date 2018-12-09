package Daos;


import collections.User;
import com.mongodb.DBCollection;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import Interface.Dao;
import org.bson.Document;
import sample.DbManager;

import java.util.ArrayList;
import java.util.List;

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
        userDoc.put("userName", user.getUsername());
        userDoc.put("password", user.getPassword());
    }

    @Override
    public void update(User user, String[] params) {

    }

    @Override
    public void delete(User user) {

    }
}
