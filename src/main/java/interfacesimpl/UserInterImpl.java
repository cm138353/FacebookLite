package interfacesimpl;


import collections.User;
import com.mongodb.MongoCredential;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.internal.MongoClientImpl;
import interfaces.UserInter;
import org.bson.Document;

import java.util.Arrays;

public class UserInterImpl implements UserInter {


    public String validate(User user, MongoDatabase database) {
        boolean result = false;
        Document document;
        try {
            MongoCredential credential = MongoCredential.createCredential(user.getUsername(),
                    database.getName(), user.getPassword());
            document = database.runCommand(new Document("db.auth(\"admin\", \"password\")" , 1));
            System.out.println(document);
        }catch(Exception e){}

        return null;
    }
}
