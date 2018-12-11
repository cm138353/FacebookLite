package Controllers;

import Classes.Friend;
import Classes.Posts;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import sample.Main;

import java.util.ArrayList;
import java.util.List;

public class ProfileController {

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
                ObservableList<Friend> selectedFriends;
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
    }

    public void setGender(String gender) {
        this.gender.setText(gender);
    }

    public void setStatus(String status) {
        this.status.setText(status);
    }

    public void setPosts(List<Posts> posts) {
        for(Posts post : posts) {
            if(!post.getHidden())
                this.posts.getItems().add(post);
        }
    }

    public void setFriendsList(List<Friend> friendsList) {
        for(Friend friend: friendsList){
            if(!friend.getHidden())
                this.friendsList.getItems().add(friend);
        }
    }
}
