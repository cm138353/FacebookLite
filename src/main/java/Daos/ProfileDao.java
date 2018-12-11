package Daos;

import Interface.Dao;
import Classes.Profile;
import com.mongodb.Block;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.BsonArray;
import org.bson.Document;
import sample.DbManager;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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
        Document friendsDoc = new Document();
        Document statusDoc = new Document();
        Document postsDoc = new Document();
        ArrayList<String> friendIdArray = new ArrayList<>();

        profileDoc.put("first", profile.getFirst());
        profileDoc.put("last", profile.getLast());
        profileDoc.put("age", profile.getAge());
        profileDoc.put("gender", profile.getGender());
        profileDoc.put("credId", profile.getCredId());

        friendsDoc.put("list", friendIdArray);
        friendsDoc.put("isHidden", false);
        profileDoc.put("friends",  friendsDoc);

        statusDoc.put("content", "");
        statusDoc.put("isHidden", false);
        profileDoc.put("status", statusDoc);


        postsDoc.put("post", new Document());
        profileDoc.put("isPostsHidden", false);
        profileDoc.put("posts", postsDoc);
        profilesCollection.insertOne(profileDoc);

    }

    @Override
    public void update(Profile profile, String[] params) {
        Document profileDoc;

        if(params[0].equals("friends")){

            Document friendsDoc;
            ArrayList<String> tempFriendArray;
            profileDoc = (Document) profilesCollection.find(eq("credId", profile.getCredId())).first();
            friendsDoc = (Document) profileDoc.get("friends");
            tempFriendArray = (ArrayList<String>) friendsDoc.get("list");
            if (params[1].equals("add")){

                tempFriendArray.add(params[2]);
                profilesCollection.updateOne(
                        eq("credId", profile.getCredId()),
                        new Document("$set", new Document("friends.list",tempFriendArray))
                );
            }
            else if (params[1].equals("remove")){

                tempFriendArray.remove(params[2]);
                profilesCollection.updateOne(
                        eq("credId", profile.getCredId()),
                        new Document("$set", new Document("friends.list", tempFriendArray))
                );
            }
            else if (params[1].equals("hide")){
                profilesCollection.updateOne(
                        eq("credId", profile.getCredId()),
                        new Document("$set", new Document("friends.isHidden", true))
                );
            }
            else if (params[1].equals("show")){
                profilesCollection.updateOne(
                        eq("credId", profile.getCredId()),
                        new Document("$set", new Document("friends.isHidden", false))
                );
            }
        } else if(params[0].equals("posts")){

        } else if(params[0].equals("age")){

        } else if(params[0].equals("status")){

        }





    }

    @Override
    public void delete(Profile profile) {
        profilesCollection.deleteOne(eq("credId",profile.getCredId()));
    }


}


