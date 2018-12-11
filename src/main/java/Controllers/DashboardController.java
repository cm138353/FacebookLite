package Controllers;

import Classes.Friend;
import Classes.Posts;
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
import sample.Main;

import java.util.ArrayList;
import java.util.List;

public class DashboardController {

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

    private Boolean showHiddenPosts = false;

    private Boolean showHiddenFriends = false;

    @FXML
    void initialize() {
        initializeProfile();
        initializePosts();
        initializeFriends();
        addListeners();
    }

    private void initializeProfile(){
        //get and set firstname, lastname,DOB, gender from database from email used to sign in
        firstName.setText("Kenny");
        lastName.setText("Sam");
        DOB.setText("08/30/1997");
        gender.setText("Male");
        status.setText("status here");
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
                ObservableList<Posts> postList;
                postList = posts.getSelectionModel().getSelectedItems();
                //add hidden posts to hiddenpostslist
                if(!showHiddenPosts) {
                    if (!postList.isEmpty()) {
                        for (Posts p : postList) {
                            //db.getposts(p.getID).setHidden(true)
                            p.setHidden(true);
                            hiddenPosts.getItems().add(p);
                            System.out.println(p.toString() + " is hidden");
                        }
                        //remove hidden posts from postlist
                        posts.getItems().removeAll(postList);
                    } else {
                        System.out.println("no posts to hide selected");
                    }
                }else {
                    if (!postList.isEmpty()) {
                        for (Posts p : postList) {
                            //db.getposts(p.getID).setHidden(false)
                            p.setHidden(false);
                            unhiddenPosts.getItems().add(p);
                            System.out.println(p.toString() + " is unhidden");
                        }
                        //remove unhidden posts from postlist
                        posts.getItems().removeAll(postList);
                    } else {
                        System.out.println("no posts to unhide selected");
                    }
                }
            }
        });

        addPost.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //Main.getPrimaryStage().setScene(Main.AddPostsPage());
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
                ObservableList<Friend> friendsToHide;
                friendsToHide = friendsList.getSelectionModel().getSelectedItems();
                //add hidden posts to hiddenpostslist

                if (!showHiddenFriends) {
                    if (!friendsToHide.isEmpty()) {
                        for (Friend f : friendsToHide) {
                            //db.getposts(f.getEmail).setHidden(true)
                            //f.setHidden(true);
                            hiddenFriends.getItems().add(f);
                            System.out.println(f.toString() + " is hidden");
                        }
                        friendsList.getItems().removeAll(friendsToHide);
                    } else {
                        System.out.println("no friends to hide selected");
                    }
                }else {
                    if (!friendsToHide.isEmpty()) {
                        for (Friend f : friendsToHide) {
                            //db.getposts(f.getEmail).setHidden(false)
                            //f.setHidden(false);
                            unhiddenFriends.getItems().add(f);
                            System.out.println(f.toString() + " is unhidden");
                        }
                        friendsList.getItems().removeAll(friendsToHide);
                    } else {
                        System.out.println("no friends to unhide selected");
                    }
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
                if(!showHiddenPosts){
                    posts.setItems(hiddenPosts.getItems());
                    showHidPosts.setText("See Posts");
                    hidePost.setText("Unhide");
                    addPost.setVisible(false);
                    showHiddenPosts = true;
                }else {
                    posts.setItems(unhiddenPosts.getItems());
                    showHidPosts.setText("See Hidden");
                    hidePost.setText("Hide");
                    addPost.setVisible(true);
                    showHiddenPosts = false;
                }
            }
        });

        showHidFriends.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!showHiddenFriends){
                    friendsList.setItems(hiddenFriends.getItems());
                    showHidFriends.setText("See Friends");
                    hideFriends.setText("Unhide");
                    addFriends.setVisible(false);
                    showHiddenFriends = true;
                }else {
                    friendsList.setItems(unhiddenFriends.getItems());
                    showHidFriends.setText("See Hidden");
                    hideFriends.setText("Hide");
                    addFriends.setVisible(true);
                    showHiddenFriends = false;
                }
            }
        });

        openProfile.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ObservableList<Friend> selectedFriends;
                selectedFriends = friendsList.getSelectionModel().getSelectedItems();

                if(selectedFriends.size() == 1) {
                    Main.getPrimaryStage().setScene(Main.getProfilePage());
                    Main.getProfileController().setFirstName(selectedFriends.get(0).getFriendFirstName());
                    Main.getProfileController().setLastName(selectedFriends.get(0).getFriendLastName());
                    System.out.println(selectedFriends.get(0).getProfileEmail());
                    //get DOB, gender ,friendslist, posts, and status from database using selectedFriends.get(0).getProfileEmail();
                    Main.getProfileController().setDOB("08/20/1920");
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
                    Main.getProfileController().setPosts(posts);
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
    }

    public void setStatus(String status){
        this.status.setText(status);
    }

    public void setPosts(List<Posts> posts) {
        this.unhiddenPosts.getItems().clear();
        this.hiddenPosts.getItems().clear();
        for(Posts post : posts) {
            if (!post.getHidden()) {
                this.unhiddenPosts.getItems().add(post);
            }
            else {
                this.hiddenPosts.getItems().add(post);
            }
        }
    }

    public void setFriendsList(List<Friend> friendsList) {
        this.hiddenFriends.getItems().clear();
        this.unhiddenFriends.getItems().clear();
        for(Friend friend: friendsList){
            if(!friend.getHidden()) {
                this.unhiddenFriends.getItems().add(friend);
            }else {
                this.hiddenFriends.getItems().add(friend);
            }
        }
    }
}
