package Daos;


import Classes.User;
import com.mongodb.Block;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import Interface.Dao;
import javafx.collections.transformation.SortedList;
import org.bson.Document;
import org.bson.conversions.Bson;
import sample.DbManager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class UserDao implements Dao<User> {

    MongoDatabase db = DbManager.getDb("FBDB");
    MongoCollection usersCollection = db.getCollection("users");



    public UserDao(){

    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        ArrayList<Document> usersDoc = new ArrayList<>();

        Block<Document> storeBlock = new Block<Document>() {
            @Override
            public void apply(final Document document) {
                usersDoc.add(document);
            }
        };
        usersCollection.find().forEach(storeBlock);
        Iterator<Document> itr = usersDoc.iterator();
        while(itr.hasNext()){
            Document doc = itr.next();
            System.out.println(doc.toJson());
            users.add(new User(doc.getString("email"), doc.getString("password")));
        }


        return users;
    }

    @Override
    public void save(User user) {
        Document userDoc = new Document();
        userDoc.put("email", user.getEmail());
        userDoc.put("password", user.getPassword());
        usersCollection.insertOne(userDoc);
    }

    @Override
    public void update(User user, String[] params) {
        usersCollection.updateOne(
                eq("email", user.getEmail()),
                new Document("$set", new Document("email", params[0])));

        usersCollection.updateOne(
                eq("email", user.getEmail()),
                new Document("$set", new Document("password", params[1])));
    }

    @Override
    public void delete(User user) {
        usersCollection.deleteOne(eq("email",user.getEmail()));

    }
}
