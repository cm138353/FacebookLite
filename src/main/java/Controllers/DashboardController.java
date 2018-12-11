package Controllers;

import Classes.Friend;
import Classes.Post;
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
    private ListView<Post> posts;

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


    private ListView<Post> hiddenPosts = new ListView<>();

    private ListView<Post> unhiddenPosts = new ListView<>();

    private ListView<Friend> hiddenFriends = new ListView<>();

    private ListView<Friend> unhiddenFriends = new ListView<>();

    private Boolean showHiddenPosts = false;

    private Boolean showHiddenFriends = false;

    private Boolean hideDOB = false;

    private Boolean hideStatus = false;

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
                ObservableList<Post> postList;
                postList = posts.getSelectionModel().getSelectedItems();

                for (Post p: postList) {
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
                ObservableList<Post> postList;
                postList = posts.getSelectionModel().getSelectedItems();
                //add hidden posts to hiddenpostslist
                if(!showHiddenPosts) {
                    if (!postList.isEmpty()) {
                        for (Post p : postList) {
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
                        for (Post p : postList) {
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
                    showHidPosts.setText("See Post");
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


                    Post post1 = new Post("hi there","12/10/2018",false);
                    Post post2 = new Post("hey" ,"12/10/2018",false);
                    ArrayList<Post> posts = new ArrayList<>();
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
                    hideStatus = true;
                }else if (hideStatus && toHide.getValue().equals("Status")){
                    //set bool for hiding status for profile to false
                    System.out.println(hideStatus + " " + toHide.getValue());
                    status.setVisible(true);
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
    }

    public void setStatus(String status){
        this.status.setText(status);
    }

    public void setPosts(List<Post> posts) {
        this.unhiddenPosts.getItems().clear();
        this.hiddenPosts.getItems().clear();
        for(Post post : posts) {
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

    public void addPost(Post post){
        posts.getItems().add(post);
    }
}
