package Daos;

import Classes.User;
import Interface.Dao;
import Classes.Profile;
import com.mongodb.Block;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import sample.DbManager;

import java.time.LocalDate;
import java.time.LocalTime;
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
            System.out.println(doc.keySet());
            Document anotherDoc = (Document) doc.get("age");
            /*
            ArrayList<String> profInfo = new ArrayList<>();
            profInfo.add(doc.getString("first"));
            profInfo.add(doc.getString("last"));
            profInfo.add(doc.getInteger("age").toString());
            profInfo.add(doc.getString("gender"));
            profInfo.add(doc.get("credId").toString());
            */
            String s;
            try{
                s = anotherDoc.get("age").toString();
            }catch (Exception e){
                s = "-99";
            }
            try{
                Integer i = Integer.parseInt(s);
            }catch (Exception e){
                s = "-99";
            }
            profiles.add(new Profile(doc.get("first").toString(),
                    doc.get("last").toString(),
                    s,
                    doc.get("gender").toString(),
                    doc.get("credId").toString()));
        }


        return profiles;
    }

    @Override //KYLE: Git Blame who wrote this class!!!!!
    public Document find(String s) {
        return (Document) profilesCollection.find(eq("credId", s));
    }

    public Document find(String k, String s) {
        return (Document) profilesCollection.find(eq(k, s));
    }

    public Profile find(User user) {

        //ERROR there are multiple of the same identically keys
        /*
        Document doc = (Document) profilesCollection.find(eq("credId", user.getEmail()));
        Document anotherDoc = (Document) doc.get("age");
        String s;
        try{
            s = anotherDoc.get("age").toString();
        }catch (Exception e){
            s = "-99";
        }
        return new Profile(doc.getString("first"),
                doc.getString("last"),
                doc.getString(s),
                doc.getString("gender"),
                doc.get("credId").toString());
                */
        return getAll().get(0); //Cheap by pass
    }

    @Override
    public void save(Profile profile) {

        //create docs
        Document profileDoc = new Document();
        Document ageDoc = new Document();
        Document friendsDoc = new Document();
        Document statusDoc = new Document();
        ArrayList<Document> postsDocArray = new ArrayList<>();

        //friend list to manipulate
        ArrayList<String> friendIdArray = new ArrayList<>();


        profileDoc.put("first", profile.getFirst());
        profileDoc.put("last", profile.getLast());

        ageDoc.put("age", profile.getAge());
        ageDoc.put("isHidden", false);
        profileDoc.put("age", ageDoc);

        profileDoc.put("gender", profile.getGender());
        profileDoc.put("credId", profile.getCredId());

        friendsDoc.put("list", friendIdArray);
        friendsDoc.put("isHidden", false);
        profileDoc.put("friends",  friendsDoc);

        statusDoc.put("content", "");
        statusDoc.put("isHidden", false);
        profileDoc.put("status", statusDoc);

        profileDoc.put("posts", postsDocArray);
        profileDoc.put("isPostsHidden", false);
        profilesCollection.insertOne(profileDoc);

    }

    @Override
    public void update(Profile profile, String[] params) {
        Document profileDoc = (Document) profilesCollection.find(eq("credId", profile.getCredId())).first();;

        if(params[0].equals("friends")){

            Document friendsDoc;
            ArrayList<String> tempFriendArray;
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
            ArrayList<Document> postsDocArray = (ArrayList<Document>) profileDoc.get("posts");
            Document postDoc;

            if (params[1].equals("add")){
                postDoc = new Document();
                LocalDate currentDate = LocalDate.now();
                LocalTime currentTime = LocalTime.now();

                postDoc.put("content", params[2]);
                postDoc.put("date", currentDate);
                postDoc.put("time", currentTime);
                postsDocArray.add(postDoc);


                profilesCollection.updateOne(
                        eq("credId", profile.getCredId()),
                        new Document("$set", new Document("posts", postsDocArray)));

            }
            if (params[1].equals("remove")){

                postDoc = postsDocArray.get(Integer.valueOf(params[2]));
                postsDocArray.remove(postDoc);
                profilesCollection.updateOne(
                        eq("credId", profile.getCredId()),
                        new Document("$set", new Document("posts", postsDocArray)));
            }
            if (params[1].equals("hide")){
                profilesCollection.updateOne(
                        eq("credId", profile.getCredId()),
                        new Document("$set", new Document("isPostsHidden", true)));
            }
            if (params[1].equals("show")){
                profilesCollection.updateOne(
                eq("credId", profile.getCredId()),
                        new Document("$set", new Document("isPostsHidden", false)));
            }

        } else if(params[0].equals("age")){

            if (params[1].equals("update")){
                profilesCollection.updateOne(
                        eq("credId", profile.getCredId()),
                        new Document("$set", new Document("age.age", params[2]))
                );
            }
            if (params[1].equals("hide")){
                profilesCollection.updateOne(
                        eq("credId", profile.getCredId()),
                        new Document("$set", new Document("age.isHidden", true))
                );
            }
            if (params[1].equals("show")){
                profilesCollection.updateOne(
                        eq("credId", profile.getCredId()),
                        new Document("$set", new Document("age.isHidden", false))
                );
            }

        } else if(params[0].equals("status")){

            if (params[1].equals("update")){
                profilesCollection.updateOne(
                        eq("credId", profile.getCredId()),
                        new Document("$set", new Document("status.content", params[2]))
                );
            }
            if (params[1].equals("hide")){
                profilesCollection.updateOne(
                        eq("credId", profile.getCredId()),
                        new Document("$set", new Document("status.isHidden", true))
                );
            }
            if (params[1].equals("show")){
                profilesCollection.updateOne(
                        eq("credId", profile.getCredId()),
                        new Document("$set", new Document("status.isHidden", false))
                );
            }

        }





    }

    @Override
    public void delete(Profile profile) {
        profilesCollection.deleteOne(eq("credId",profile.getCredId()));
    }


}


