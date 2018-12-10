package Controllers;

import Classes.Post;
import Classes.User;
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
    private ListView<User> friendsList;

    @FXML
    private Button addFriends;

    @FXML
    private Button deleteFriends;

    @FXML
    private Button hideFriends;

    @FXML
    private Button openProfile;

    private ListView<Post> hiddenPosts = new ListView<>();

    private ListView<User> hiddenFriends = new ListView<>();

    @FXML
    void initialize() {
        setProfile();
        setPosts();
        setFriends();
        addListeners();
    }

    private void setProfile(){
        //get and set firstname, lastname,DOB, gender from database from email used to sign in
        firstName.setText("Kenny");
        lastName.setText("Sam");
        DOB.setText("08/30/1997");
        gender.setText("Male");
        status.setText("status here");
        status.setEditable(false);

    }

    private void setPosts(){
        //create new posts from database and add posts to posts listview using forloop
        //add hidden posts
        Post post1 = new Post("whats up dudes","12/9/2018",false);
        Post post2 = new Post("hey" ,"12/9/2018",false);
        posts.getItems().addAll(post1,post2);
        posts.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        /*
            ArrayList<Post> postsarray = new ArrayList<Post>();
            get all posts from database as arraylist of posts and save to postsarray

            for(Post p : postsarray){
                if(!p.getHidden){
                    posts.getItems().add(p);
                }else{
                    hiddenposts.getItems.add(p)
                }
            }

            posts.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        * */
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
                if(!postList.isEmpty()) {
                    for(Post p : postList){
                        //db.getposts(p.getID).setHidden(true)
                        p.setHidden(true);
                        hiddenPosts.getItems().add(p);
                        System.out.println(p.toString() + " is hidden");
                    }
                    //remove hidden posts from postlist
                    posts.getItems().removeAll(postList);
                }else{
                    System.out.println("no posts to hide selected");
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
                ObservableList<User> removedFriends;
                removedFriends = friendsList.getSelectionModel().getSelectedItems();

                for (User f: removedFriends) {
                    System.out.println(f.getEmail());
                    //remove friends from database by f.getEmail();
                }
                //remove friends from friendslist
                friendsList.getItems().removeAll(removedFriends);
            }
        });
        hideFriends.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ObservableList<User> friendsToHide;
                friendsToHide = friendsList.getSelectionModel().getSelectedItems();
                //add hidden posts to hiddenpostslist
                if(!friendsToHide.isEmpty()) {
                    for(User f : friendsToHide){
                        //db.getposts(f.getEmail).setHidden(true)
                        f.setHidden(true);
                        hiddenFriends.getItems().add(f);
                        System.out.println(f.toString() + " is hidden");
                    }
                    friendsList.getItems().removeAll(friendsToHide);
                }else{
                    System.out.println("no friends to hide selected");
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

        //todo
        /*
        * listeners for, adding friends or posts, hiding profile items, editing profile items, showing hidden posts or friends, and opening a new profile page for friends
        * */

    }

    private void setFriends(){
        User friend1 = new User("Kenny", "Sam", "ken@gmail.com");
        User friend2 = new User("Joey", "Sam", "Joe@gmail.com");
        friendsList.getItems().addAll(friend1,friend2);
        friendsList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        //similar to adding posts, columns in table for friends list should be:
        //email of friend, firstname of friend, last name of friend, firstname of profile, lastname of profile, hidden
    }

}
