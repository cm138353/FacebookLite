package Models;

import Classes.Friend;
import Classes.Profile;
import Classes.User;
import Daos.ProfileDao;
import Daos.UserDao;

import java.util.*;

public class TheDataBase {

    private UserDao userData;
    private ProfileDao profileData;

    public final User failedUser = new User( "Null", "Null");
    public final Profile failedProfile = new Profile("Null", "Null", -99, "Null", "Null");
    public final Friend failedfriend = new Friend("Null", "Null", "Null", "Null",  false);

    public TheDataBase(){
        userData = new UserDao();
        profileData = new ProfileDao();
    }

    /***
     * @author KyleAstudillo
     * @param user
     * @return List<>Friend</>
     */
    public List<Friend> getFriends(User user){
        List<Friend> f = new LinkedList<>();
        f.add(failedfriend);
        return f;
    }

    /***
     * @author KyleAstudillo
     * @param profileEmail
     * @return Friend
     */
    public Friend getFriend(User user, String profileEmail){
        return failedfriend;
    }

    /***
     * @author KyleAstudillo
     * @param user
     * @return List<>Friend</>
     */
    public void setFriends(User user, List<Friend> friends){

    }

    /***
     * @author KyleAstudillo
     * @param profileEmail
     * @return Friend
     */
    public void setFriend(User user, String profileEmail){

    }

    public Profile getProfile(User user){
        return profileData.find(user);
    }

    public void setProfile(User user, String firstname, String lastname, Integer age, String gender){
        try {
            profileData.save(new Profile(firstname, lastname, age, gender, user.getEmail()));
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public User getUser(String email){
        try {
            return userData.getAll().get(0);
        }catch (Exception e){
            return failedUser;
        }
    }

    public void setUser(User user){
        userData.save(new User("a", "a", "a", "a"));
    }


}
