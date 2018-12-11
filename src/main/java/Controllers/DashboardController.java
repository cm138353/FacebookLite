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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import org.bson.Document;
import sample.Main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DashboardController {

    private static Dao userDao;
    private static Dao profileDao;

    @FXML
    private Label firstName;

    @FXML
    private Button hideItem;

    @FXML
    private ComboBox<String> toHide;

    @FXML
    private Label lastName;

    @FXML
    private Label gender;

    @FXML
    private TextArea status;

    @FXML
    private Button editProfile;

    @FXML
    private Button updateStatus;

    @FXML
    private Label DOB;

    @FXML
    private Button showHidPosts;

    @FXML
    private ListView<Posts> posts;

    @FXML
    private Button addPost;

    @FXML
    private Button deletePost;

    @FXML
    private Button hidePost;

    @FXML
    private Button showHidFriends;

    @FXML
    private ListView<Friend> friendsList;

    @FXML
    private Button addFriends;

    @FXML
    private Button deleteFriends;

    @FXML
    private Button hideFriends;

    @FXML
    private Button openProfile;

    @FXML
    private Button logoutButton;


    private ListView<Posts> hiddenPosts = new ListView<>();

    private ListView<Posts> unhiddenPosts = new ListView<>();

    private ListView<Friend> hiddenFriends = new ListView<>();

    private ListView<Friend> unhiddenFriends = new ListView<>();

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
        posts.setItems(unhiddenPosts.getItems());
        posts.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    private void initializeFriends(){
        friendsList.getItems().clear();
        friendsList.setItems(unhiddenFriends.getItems());
        friendsList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    private void addListeners(){
        deletePost.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ObservableList<Posts> postList;
                postList = posts.getSelectionModel().getSelectedItems();

                for (Posts p: postList) {
                    System.out.println(p.getID());
                    //remove posts from database by p.getID();
                }
                //remove posts from postlist
                posts.getItems().removeAll(postList);
            }
        });

        hidePost.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if(!hidePostsBool) {
                    for (Posts p : unhiddenPosts.getItems()) {
                        hiddenPosts.getItems().add(p);
                        System.out.println(p.toString() + " is hidden");
                    }
                    //remove hidden posts from postlist
                    posts.getItems().clear();
                }else {
                    for (Posts p : hiddenPosts.getItems()) {
                        unhiddenPosts.getItems().add(p);
                        System.out.println(p.toString() + " is unhidden");
                    }
                    //remove unhidden posts from postlist
                    posts.getItems().clear();
                }
            }
        });

        addPost.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Main.getPrimaryStage().setScene(Main.getAddPostPage());
            }
        });

        deleteFriends.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ObservableList<Friend> removedFriends;
                removedFriends = friendsList.getSelectionModel().getSelectedItems();

                for (Friend f: removedFriends) {
                    System.out.println(f.getFriendEmail());
                    //remove friends from friends table by f.getEmail();
                }
                //remove friends from friendslist
                friendsList.getItems().removeAll(removedFriends);
            }
        });
        hideFriends.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if(!hideFriendsBool){
                    for (Friend f : unhiddenFriends.getItems()) {
                        hiddenFriends.getItems().add(f);
                        System.out.println(f.toString() + " is hidden");
                    }
                    friendsList.getItems().clear();
                }else {
                    for (Friend f : hiddenFriends.getItems()) {
                        unhiddenFriends.getItems().add(f);
                        System.out.println(f.toString() + " is unhidden");
                    }
                    friendsList.getItems().clear();
                }
            }
        });
        status.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                status.setEditable(true);
            }
        });

        updateStatus.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //save status to database
                status.setEditable(false);
            }
        });

        showHidPosts.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!hidePostsBool){
                    posts.setItems(hiddenPosts.getItems());
                    showHidPosts.setText("See Posts");
                    hidePost.setText("Unhide");
                    addPost.setVisible(false);
                    hidePostsBool = true;
                }else {
                    posts.setItems(unhiddenPosts.getItems());
                    showHidPosts.setText("See Hidden");
                    hidePost.setText("Hide");
                    addPost.setVisible(true);
                    hidePostsBool = false;
                }
            }
        });

        showHidFriends.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!hideFriendsBool){
                    friendsList.setItems(hiddenFriends.getItems());
                    showHidFriends.setText("See Friends");
                    hideFriends.setText("Unhide");
                    addFriends.setVisible(false);
                    hideFriendsBool = true;
                }else {
                    friendsList.setItems(unhiddenFriends.getItems());
                    showHidFriends.setText("See Hidden");
                    hideFriends.setText("Hide");
                    addFriends.setVisible(true);
                    hideFriendsBool = false;
                }
            }
        });

        openProfile.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ObservableList<Friend> selectedFriends;
                selectedFriends = friendsList.getSelectionModel().getSelectedItems();

                Friend friend1 = new Friend("Jim@gmail.com", "Earl", "Yu","Michael@gmail.com",false);
                Friend friend2 = new Friend("Jim@gmail.com", "Michael", "Yu","kyle@gmail.com",false);
                ArrayList<Friend> friends = new ArrayList<>();
                friends.add(friend1);
                friends.add(friend2);

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
                                        Main.getPrimaryStage().setScene(Main.getProfilePage());
                                        Main.getProfileController().setHideStatus(profile.getHideStatus());
                                        Main.getProfileController().setHideFriendsBool(profile.getHideFriends());
                                        Main.getProfileController().setHideDOB(profile.getHideAge());
                                        Main.getProfileController().setFirstName(profile.getFirst());
                                        Main.getProfileController().setLastName(profile.getLast());
                                        Main.getProfileController().setFriendsList(friends);
                                        Main.getProfileController().setStatus(profile.getStatusContent());
                                        Main.getProfileController().setPosts(profile.getPostsList());
                                        Main.getProfileController().setGender(profile.getGender());
                                        Main.getProfileController().setDOB(profile.getAge());
                                        break;
                                    }
                                }
                                break;
                            }
                        }
                    }
                    //get DOB, gender ,friendslist, posts, and status from database using selectedFriends.get(0).getProfileEmail();
                    /*Main.getProfileController().setDOB("08/20/1920");
                    Main.getProfileController().setGender("Male");
                    Main.getProfileController().setStatus("Doing nothing");

                    Friend friend1 = new Friend("Jim@gmail.com", "Earl", "Yu","Michael@gmail.com",false);
                    Friend friend2 = new Friend("Jim@gmail.com", "Michael", "Yu","kyle@gmail.com",false);
                    ArrayList<Friend> friends = new ArrayList<>();
                    friends.add(friend1);
                    friends.add(friend2);
                    Main.getProfileController().setFriendsList(friends);


                    Posts post1 = new Posts("hi there","12/10/2018",false);
                    Posts post2 = new Posts("hey" ,"12/10/2018",false);
                    ArrayList<Posts> posts = new ArrayList<>();
                    posts.add(post1);
                    posts.add(post2);
                    Main.getProfileController().setPosts(posts);*/

                }else {
                    System.out.println("error");
                }
            }
        });

        logoutButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Main.getPrimaryStage().setScene(Main.getLoginPage());
            }
        });

        editProfile.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Main.getPrimaryStage().setScene(Main.getUpdateProfPage());
            }
        });

        hideItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //replace hideDOB and hideStatus with the value returned from the database that says to hide the DOB or Status
                System.out.println(toHide.getValue());
                if(!hideDOB && toHide.getValue().equals("Age")){
                    //set boolean for hiding DOB for profile to true in database
                    System.out.println(hideDOB + " " + toHide.getValue());
                    DOB.setVisible(false);
                    hideDOB = true;
                }else if(hideDOB && toHide.getValue().equals("Age")){
                    //set boolean for hiding DOB for profile to false in database
                    System.out.println(hideDOB + " " + toHide.getValue());
                    DOB.setVisible(true);
                    hideDOB = false;
                }

                if(!hideStatus && toHide.getValue().equals("Status")){
                    //set bool for hiding status for profile to true
                    System.out.println(hideStatus + " " + toHide.getValue());
                    status.setVisible(false);
                    updateStatus.setVisible(false);
                    hideStatus = true;
                }else if (hideStatus && toHide.getValue().equals("Status")){
                    //set bool for hiding status for profile to false
                    System.out.println(hideStatus + " " + toHide.getValue());
                    status.setVisible(true);
                    updateStatus.setVisible(true);
                    hideStatus = false;
                }
            }
        });
    }


    public void setFirstName(String firstName) {
        this.firstName.setText(firstName);
    }

    public void setLastName(String lastName) {
        this.lastName.setText(lastName);
    }

    public void setGender(String gender) {
        this.gender.setText(gender);
    }

    public void setDOB(String DOB){
        this.DOB.setText(DOB);
        if(hideDOB) {
            this.DOB.setVisible(false);
        }
    }

    public void setStatus(String status){
        this.status.setText(status);
        if(hideStatus){
            this.status.setVisible(false);
            this.updateStatus.setVisible(false);
        }
    }

    public void setPosts(List<Posts> posts) {
        this.unhiddenPosts.getItems().clear();
        this.hiddenPosts.getItems().clear();
        for(Posts post : posts) {
            if (!hidePostsBool) {
                this.unhiddenPosts.getItems().add(post);
            }
            else {
                this.hiddenPosts.getItems().add(post);
            }
            //this.posts.getItems().add(post);
        }
    }

    public void setFriendsList(List<Friend> friendsList) {
        this.hiddenFriends.getItems().clear();
        this.unhiddenFriends.getItems().clear();
        for(Friend friend: friendsList){
            if(!hideFriendsBool) {
                this.unhiddenFriends.getItems().add(friend);
            }else {
                this.hiddenFriends.getItems().add(friend);
            }
            //this.friendsList.getItems().add(friend);
        }
    }

    public void addPost(Posts post){
        posts.getItems().add(post);
    }

    public void setHidePosts(Boolean hidePostsBool){
        this.hidePostsBool = hidePostsBool;
    }

    public void setHideFriends(Boolean hideFriendsBool){
        this.hideFriendsBool = hideFriendsBool;
    }

    public void setHideStatus(Boolean hideStatus) {
        this.hideStatus = hideStatus;
    }

    public void setHideAge(Boolean hideDOB){
        this.hideDOB = hideDOB;
    }
}
