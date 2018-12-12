import Classes.Profile;
import Classes.User;
import Models.TheDataBase;
import org.bson.Document;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestDb {

    TheDataBase db = new TheDataBase();
    User defaultUser = new User("a", "a");


    @Test
    public void TestAdd(){
        try {
            db.setUser(new User("a", "a", "a", "a"));
            assertTrue(true);
        }catch (Exception e){
            assertTrue(false);
        }
    }

    @Test
    public void TestSetProfile(){
        try {
            db.setProfile(defaultUser, "a","a",10, "a");
            assertNotNull(db.getProfile(defaultUser));
        }catch (Exception e){
            assertTrue(false);
        }
    }

    @Test
    public void TestGetProfile(){
        try {
            Profile profile = db.getProfile(defaultUser);
            assertNotSame(profile, db.failedProfile);
        }catch (Exception e){
            assertTrue(false);
        }
    }

    @Test
    public void TestGetUser(){
        try {
            User user = db.getUser(defaultUser.getEmail());
            assertNotSame(user, db.failedUser);
        }catch (Exception e){
            assertTrue(false);
        }
    }

    /*
    public static void main() {
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
    */
}
