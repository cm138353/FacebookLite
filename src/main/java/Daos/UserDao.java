package Daos;


import collections.User;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoDatabase;
import Interface.Dao;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class UserDao implements Dao<User> {



    public UserDao(){

    }

    @Override
    public List<User> getAll() {

        return null;
    }

    @Override
    public void save(User user) {

    }

    @Override
    public void update(User user, String[] params) {

    }

    @Override
    public void delete(User user) {

    }
}
