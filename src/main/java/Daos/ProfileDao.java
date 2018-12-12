package Daos;

import Classes.Friend;
import Classes.Posts;
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
import java.time.LocalTime;
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

    /*@Override
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
            Profile profile = new Profile();
            Document rand;
            profile.setFirst(doc.getString("first"));
            profile.setLast(doc.getString("last"));
            rand = (Document)doc.get("age");
            profile.setAge(rand.getString("age"));
            profile.setHideAge(rand.getBoolean("isHidden"));
            profile.setGender(doc.getString("gender"));
            profile.setCredId(doc.getString("credId"));
            rand = (Document)doc.get("friends");
            profile.setFriendsList((ArrayList<Friend>) rand.get("list"));
            profile.setHideFriends(rand.getBoolean("isHidden"));
            rand = (Document)doc.get("status");
            profile.setStatusContent(rand.getString("content"));
            profile.setHideStatus(rand.getBoolean("isHidden"));
            profile.setPostsList((ArrayList <Posts>)doc.get("posts"));
            profile.setHidePosts(doc.getBoolean("isPostsHidden"));
            profiles.add(profile);
        }


        return profiles;
    }*/
    //good
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
            Profile profile = new Profile();
            ArrayList<String> profInfo = new ArrayList<>();
            Document rand;
            profile.setFirst(doc.getString("first"));
            profile.setLast(doc.getString("last"));
            rand = (Document)doc.get("age");
            profile.setAge(rand.getString("age"));
            profile.setHideAge(rand.getBoolean("isHidden"));
            profile.setGender(doc.getString("gender"));
            profile.setCredId(doc.getString("credId"));
            rand = (Document)doc.get("friends");
            profile.setFriendsList((ArrayList<String>) rand.get("list"));
            profile.setHideFriends(rand.getBoolean("isHidden"));
            rand = (Document)doc.get("status");
            profile.setStatusContent(rand.getString("content"));
            profile.setHideStatus(rand.getBoolean("isHidden"));
            profile.setPostsList((ArrayList <String>)doc.get("posts"));
            profile.setHidePosts(doc.getBoolean("isPostsHidden"));


            profiles.add(profile);
        }


        return profiles;
    }
    //good
    @Override
    public Document find(String credID) {
        Document doc = (Document) profilesCollection.find(eq("credId", credID));
        return doc;
    }

    //good
    @Override
    public void save(Profile profile) {

        //create docs
        Document profileDoc = new Document();
        Document ageDoc = new Document();
        Document friendsDoc = new Document();
        Document statusDoc = new Document();
        //ArrayList<Document> postsDocArray = new ArrayList<>();

        //friend list to manipulate
        //ArrayList<String> friendIdArray = new ArrayList<>();

        //ArrayList <Posts> postsDocArray = new ArrayList<>();
        //ArrayList <Friend> friendIdArray = new ArrayList<>();


        profileDoc.put("first", profile.getFirst());
        profileDoc.put("last", profile.getLast());

        ageDoc.put("age", profile.getAge());
        ageDoc.put("isHidden", profile.getHideAge());
        profileDoc.put("age", ageDoc);

        profileDoc.put("gender", profile.getGender());
        profileDoc.put("credId", profile.getCredId());

        friendsDoc.put("list", profile.getFriendsList());
        friendsDoc.put("isHidden", profile.getHideFriends());
        profileDoc.put("friends",  friendsDoc);

        statusDoc.put("content", profile.getStatusContent());
        statusDoc.put("isHidden", profile.getHideStatus());
        profileDoc.put("status", statusDoc);

        profileDoc.put("posts", profile.getPostsList());
        profileDoc.put("isPostsHidden", profile.getHidePosts());
        profilesCollection.insertOne(profileDoc);

    }

    //Still need to change to find Arrays of Posts and Arrays
    @Override
    public void update(Profile profile, String[] params) {
        Document profileDoc = (Document) profilesCollection.find(eq("credId", profile.getCredId())).first();;

        if(params[0].equals("friends")){

            Document friendsDoc;
            ArrayList<String> tempFriendArray;
            friendsDoc = (Document) profileDoc.get("friends");
            tempFriendArray = (ArrayList<String>) friendsDoc.get("list");

            //profileDao.update(profile1, new String[]{"friends", "add","kennysam21@yahoo.com, Jim, Yu, jim@gmail.com"});
            //good
            if (params[1].equals("add")){
                System.out.println(params[2]);
                tempFriendArray.add(params[2]);
                profilesCollection.updateOne(
                        eq("credId", profile.getCredId()),
                        new Document("$set", new Document("friends.list",tempFriendArray))
                );
            }
            else if (params[1].equals("remove")){
                Iterator<String> friends = tempFriendArray.iterator();
                while (friends.hasNext()) {
                    String friend = friends.next();
                    if (params[2].equals(friend)) {
                        tempFriendArray.remove(friend);
                        break;
                    }
                }
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
            //good
        } else if(params[0].equals("posts")){
            ArrayList<String> postsDocArray = (ArrayList<String>) profileDoc.get("posts");
            //works
            if (params[1].equals("add")){
                postsDocArray.add(params[2]);
                //System.out.println(params[2]);
                profilesCollection.updateOne(
                        eq("credId", profile.getCredId()),
                        new Document("$set", new Document("posts", postsDocArray)));

            }
            //works
            if (params[1].equals("remove")){

                //postDoc = postsDocArray.get(Integer.valueOf(params[2]));
                //remove first instance
                Iterator<String> posts = postsDocArray.iterator();
                while (posts.hasNext()) {
                    String post = posts.next();
                    if(params[2].equals(post)){
                        postsDocArray.remove(post);
                        System.out.println(post + " was deleted");
                        break;
                    }
                }
                profilesCollection.updateOne(
                        eq("credId", profile.getCredId()),
                        new Document("$set", new Document("posts", postsDocArray)));
            }
            //works
            if (params[1].equals("hide")){
                profilesCollection.updateOne(
                        eq("credId", profile.getCredId()),
                        new Document("$set", new Document("isPostsHidden", true)));
            }
            //works
            if (params[1].equals("show")){
                profilesCollection.updateOne(
                        eq("credId", profile.getCredId()),
                        new Document("$set", new Document("isPostsHidden", false)));
            }
            //good
        } else if(params[0].equals("age")){
            //works
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
            //good
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

        } else if(params[0].equals("gender")){
            if (params[1].equals("update")){
                profilesCollection.updateOne(
                        eq("credId", profile.getCredId()),
                        new Document("$set", new Document("gender", params[2])));
                //System.out.println(params[2]);
            }
        } else if(params[0].equals("first")){
            if (params[1].equals("update")){
                profilesCollection.updateOne(
                        eq("credId", profile.getCredId()),
                        new Document("$set", new Document("first", params[2])));
            }
        }else if(params[0].equals("last")){
            if (params[1].equals("update")){
                profilesCollection.updateOne(
                        eq("credId", profile.getCredId()),
                        new Document("$set", new Document("last", params[2])));
            }
        }

    }

    @Override
    public void delete(Profile profile) {
        profilesCollection.deleteOne(eq("credId",profile.getCredId()));
    }


}