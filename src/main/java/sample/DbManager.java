package sample;


import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class DbManager {
    private static MongoDatabase db;
    public static MongoDatabase getDb (String name) {
        MongoClient mongo = MongoClients.create();
        if ( db == null){
            db = mongo.getDatabase(name);
        }
        return db;
    }
}
