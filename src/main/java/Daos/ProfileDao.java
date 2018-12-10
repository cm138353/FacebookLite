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
        Document doc = (Document) profilesCollection.find(eq("credId", profile.getCredId())).first();
        return doc;
    }


    @Override
    public void save(Profile profile) {
        Document profileDoc = new Document();
        profileDoc.put("first", profile.getFirst());
        profileDoc.put("last", profile.getLast());
        profileDoc.put("age", profile.getAge());
        profileDoc.put("gender", profile.getGender());
        profileDoc.put("credId", profile.getCredId());
        profilesCollection.insertOne(profileDoc);

    }

    @Override
    public void update(Profile profile, String[] params) {
        profilesCollection.updateOne(
                eq("age", profile.getAge()),
                new Document("$set", new Document("age", params[0])));
    }

    @Override
    public void delete(Profile profile) {
        profilesCollection.deleteOne(eq("credId",profile.getCredId()));
    }


}


