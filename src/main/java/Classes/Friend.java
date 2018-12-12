package Classes;

public class Friend {

    private String profileEmail;
    private String friendFirstName;
    private String friendLastName;
    private String friendEmail;

    public Friend(String pEmail, String fFirstName, String fLastName, String fEmail){
        this.profileEmail = pEmail;
        this.friendFirstName = fFirstName;
        this.friendLastName = fLastName;
        this.friendEmail = fEmail;
    }

    public void setFriendFirstName(String friendFirstName) {
        this.friendFirstName = friendFirstName;
    }

    public void setFriendLastName(String friendLastName) {
        this.friendLastName = friendLastName;
    }

    public void setProfileEmail(String profileEmail) {
        this.profileEmail = profileEmail;
    }

    public void setFriendEmail(String friendEmail) {
        this.friendEmail = friendEmail;
    }


    public String getFriendFirstName() {
        return friendFirstName;
    }

    public String getFriendLastName() {
        return friendLastName;
    }

    public String getFriendEmail() {
        return friendEmail;
    }

    public String getProfileEmail() {
        return profileEmail;
    }

    @Override
    public String toString() {
        return friendFirstName + " " + friendLastName;
    }
}
