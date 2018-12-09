package Daos;

import Interface.Dao;
import Classes.Profile;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import sample.DbManager;

import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class ProfileDao implements Dao<Profile> {

    MongoDatabase db = DbManager.getDb("FBDB");
    MongoCollection usersCollection = db.getCollection("users");



    public ProfileDao(){

    }

    @Override
    public List<Profile> getAll() {
        return null;
    }

    @Override
    public void save(Profile profile) {

    }

    @Override
    public void update(Profile profile, String[] params) {

    }

    @Override
    public void delete(Profile profile) {

    }


}


