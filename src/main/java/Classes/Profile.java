package Classes;

//import sample.Friends;

import java.util.ArrayList;
import java.util.Iterator;

public class Profile {
    private String first = "";
    private String last = "";
    private String age = "";
    private String gender = "";
    private String credId = "";
    private Boolean hideAge = false;
    private Boolean hideStatus = false;
    private Boolean hideFriends = false;
    private Boolean hidePosts = false;
    private String statusContent = "";
    private ArrayList<String> postsList = new ArrayList<>();
    private ArrayList<String> friendsList = new ArrayList<>();

    public Profile(ArrayList<String> info){
        Iterator<String> itr = info.iterator();

        this.first = itr.next();
        this.last = itr.next();
        this.age = itr.next();
        this.gender = itr.next();
        this.credId = itr.next();

    }

    public Profile(){

    }

    /*public Profile(String f, String l, String a, Boolean hideA, String g, String cId, ArrayList<Friend> fl, Boolean hideFl, String s, Boolean hideS, ArrayList<Posts> p, Boolean hideP){

    }*/

    public String getFirst(){ return first; }
    public void setFirst(String f){ first = f; }

    public String getLast(){ return last; }
    public void setLast(String l){ last = l; }

    public String getAge(){ return age; }
    public void setAge(String d){ age = d; }

    public String getGender(){ return gender; }
    public void setGender(String g){ gender = g; }

    public String getCredId(){ return credId; }
    public void setCredId(String c){ credId = c; }

    public Boolean getHideAge() {
        return hideAge;
    }

    public void setHideAge(Boolean hideAge) {
        this.hideAge = hideAge;
    }

    public String getStatusContent() {
        return statusContent;
    }

    public void setStatusContent(String statusContent) {
        this.statusContent = statusContent;
    }

    public Boolean getHideStatus() {
        return hideStatus;
    }

    public void setHideStatus(Boolean hideStatus) {
        this.hideStatus = hideStatus;
    }

    public Boolean getHideFriends() {
        return hideFriends;
    }

    public void setHideFriends(Boolean hideFriends) {
        this.hideFriends = hideFriends;
    }

    public Boolean getHidePosts() {
        return hidePosts;
    }

    public void setHidePosts(Boolean hidePosts) {
        this.hidePosts = hidePosts;
    }

    public ArrayList<String> getFriendsList() {
        return friendsList;
    }

    public void setFriendsList(ArrayList<String> friendsList) {
        this.friendsList = friendsList;
    }

    public ArrayList<String> getPostsList() {
        return postsList;
    }

    public void setPostsList(ArrayList<String> postsList) {
        this.postsList = postsList;
    }

    @Override
    public String toString() {
        return first + " " + last + " " + age + " " + gender + " " + credId;
    }
}