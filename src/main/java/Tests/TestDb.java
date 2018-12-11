package Tests;

import Classes.User;
import Models.TheDataBase;
import org.bson.Document;

public class TestDb {
    public static void main(String[] args) {
        TheDataBase db = new TheDataBase();
        User defaultUser = new User("a", "a");
        //db.userData.save(new User("a", "a", "a", "a"));

        System.out.println(db.userData.getAll().toString());

        //set profile PASS
        //db.setProfile(defaultUser, "a","a","a", "a");
        //System.out.println(db.profileData.getAll().toString());
        //System.out.println(db.getProfile(defaultUser));

        //get friends FAILED
        //Document doc = db.profileData.find("friends", "a");
        //System.out.println(((Document) doc).keySet());
    }
}
