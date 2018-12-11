package Controllers;

import Classes.Friend;
import Classes.Posts;
import Classes.Profile;
import Classes.User;
import Daos.ProfileDao;
import Daos.UserDao;
import Interface.Dao;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import org.bson.Document;
import sample.Main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProfileController {

    private static Dao userDao;
    private static Dao profileDao;

    @FXML
    private Label firstName;

    @FXML
    private Label lastName;

    @FXML
    private Label gender;

    @FXML
    private TextArea status;

    @FXML
    private Label DOB;

    @FXML
    private ListView<Posts> posts;

    @FXML
    private ListView<Friend> friendsList;

    @FXML
    private Button openProfile;

    @FXML
    private Button homeButton;

    private Boolean hidePostsBool = false;

    private Boolean hideFriendsBool = false;

    private Boolean hideDOB = false;

    private Boolean hideStatus = false;

    @FXML
    void initialize() {
        userDao = new UserDao();
        profileDao = new ProfileDao();
        initializeProfile();
        initializePosts();
        initializeFriends();
        addListeners();
    }

    private void initializeProfile(){
        //get and set firstname, lastname,DOB, gender from database from email used to sign in
        /*firstName.setText("Kenny");
        lastName.setText("Sam");
        DOB.setText("08/30/1997");
        gender.setText("Male");
        status.setText("status here");*/
        status.setEditable(false);

    }

    private void initializePosts(){
        posts.getItems().clear();
        posts.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    private void initializeFriends(){
        friendsList.getItems().clear();
        friendsList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    private void addListeners(){
        openProfile.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                /*ObservableList<Friend> selectedFriends;
                selectedFriends = friendsList.getSelectionModel().getSelectedItems();

                if(selectedFriends.size() == 1) {
                    setFirstName(selectedFriends.get(0).getFriendFirstName());
                    setLastName(selectedFriends.get(0).getFriendLastName());
                    System.out.println(selectedFriends.get(0).getProfileEmail());
                    //get DOB, gender ,friendslist, posts, and status from database using selectedFriends.get(0).getProfileEmail();
                    setDOB("01/01/1990");
                    setGender("Male");
                    setStatus("Page 2");
                    friendsList.getItems().clear();
                    posts.getItems().clear();
                }else {
                    System.out.println("error");
                }*/
                ObservableList<Friend> selectedFriends;
                selectedFriends = friendsList.getSelectionModel().getSelectedItems();

                if(selectedFriends.size() == 1) {
                    System.out.println(selectedFriends.get(0).getProfileEmail());
                    List<User> users = userDao.getAll();
                    Iterator<User> itr = users.iterator();
                    while(itr.hasNext()) {
                        User user = itr.next();
                        Document doc = (Document) userDao.find(selectedFriends.get(0).getFriendEmail());
                        //System.out.println(doc.toString());
                        if (doc != null) {
                            System.out.println(doc.toString());
                            if (doc.get("email").toString().equals(selectedFriends.get(0).getFriendEmail())){
                                System.out.println("Success");
                                String id = doc.get("_id").toString();
                                List<Profile> profiles = profileDao.getAll();
                                Iterator<Profile> itr2 = profiles.iterator();
                                while (itr2.hasNext()) {
                                    Profile profile = itr2.next();
                                    System.out.println(profile.toString());
                                    System.out.println(profile.getCredId() + " " + id);
                                    if (profile.getCredId().equals(id)) {
                                        System.out.println("equal id");
                                        setHideDOB(true);
                                        setHideFriendsBool(false);
                                        setHideStatus(false);
                                        setHidePostsBool(false);
                                        setFirstName(profile.getFirst());
                                        setLastName(profile.getLast());
                                        setStatus("lmao");
                                        setDOB("10/12/1994");
                                        setPosts(profile.getPostsList());
                                        setFriendsList(profile.getFriendsList());
                                        break;
                                    }
                                }
                                break;
                            }
                        }
                    }
                }else {
                    System.out.println("error");
                }
            }
        });

        homeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Main.getPrimaryStage().setScene(Main.getDashboardPage());
            }
        });
    }

    public void setFirstName(String firstName) {
        this.firstName.setText(firstName);
    }

    public void setLastName(String lastName) {
        this.lastName.setText(lastName);
    }

    public void setDOB(String DOB) {
        this.DOB.setText(DOB);
        if(hideDOB)
            this.DOB.setVisible(false);
    }

    public void setGender(String gender) {
        this.gender.setText(gender);
    }

    public void setStatus(String status) {
        this.status.setText(status);
        if(hideStatus)
            this.status.setVisible(false);
    }

    public void setPosts(List<Posts> posts) {
        this.posts.getItems().clear();
        for(Posts post : posts) {
            if(!hidePostsBool)
                this.posts.getItems().add(post);
        }
    }

    public void setFriendsList(List<Friend> friendsList) {
        this.friendsList.getItems().clear();
        for(Friend friend: friendsList){
            if(!hideFriendsBool)
                this.friendsList.getItems().add(friend);
        }
    }

    public void setHideStatus(Boolean hideStatus) {
        this.hideStatus = hideStatus;
    }

    public void setHideDOB(Boolean hideDOB) {
        this.hideDOB = hideDOB;
    }

    public void setHideFriendsBool(Boolean hideFriendsBool) {
        this.hideFriendsBool = hideFriendsBool;
    }

    public void setHidePostsBool(Boolean hidePostsBool) {
        this.hidePostsBool = hidePostsBool;
    }
}
