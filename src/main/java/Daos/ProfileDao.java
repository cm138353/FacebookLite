package Daos;

import Interface.Dao;
import Classes.Profile;
import com.mongodb.Block;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import sample.DbManager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class ProfileDao implements Dao<Profile> {

    MongoDatabase db = DbManager.getDb("FBDB");
    MongoCollection profilesCollection = db.getCollection("profiles");



    public ProfileDao(){

    }

    @Override
    public List<Profile> getAll() {
        List<Profile> profiles = new ArrayList<>();
        ArrayList<Document> profilesDoc = new ArrayList<>();

        Block<Document> storeBlock = new Block<Document>() {
            @Override
            public void apply(final Document document) {
                profilesDoc.add(document);
            }
        };
        profilesCollection.find().forEach(storeBlock);
        Iterator<Document> itr = profilesDoc.iterator();
        while(itr.hasNext()){
            Document doc = itr.next();
            ArrayList<String> profInfo = new ArrayList<>();
            profInfo.add(doc.getString("first"));
            profInfo.add(doc.getString("last"));
            profInfo.add(doc.getString("age"));
            profInfo.add(doc.getString("gender"));
            profInfo.add(doc.get("credId").toString());

            profiles.add(new Profile(profInfo));
        }


        return profiles;
    }

    @Override
    public Document find(Profile profile) {

        return null;
    }


    @Override
    public void save(Profile profile) {
        Document profileDoc = new Document();
    }

    @Override
    public void update(Profile profile, String[] params) {

    }

    @Override
    public void delete(Profile profile) {

    }


}


