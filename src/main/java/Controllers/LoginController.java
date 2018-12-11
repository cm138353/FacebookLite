package Controllers;

import Classes.Friend;
import Classes.Posts;
import Classes.User;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.Main;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LoginController {

    @FXML
    private TextField email;

    @FXML
    private Button register;

    @FXML
    private Button logIn;

    @FXML
    private Button resetPassword;

    @FXML
    private PasswordField password;

    @FXML
    void initialize() {
        addListeners();
    }

    private String tEmail;
    private String tPass;

    public void clear(){
        email.setText(null);
        password.setText(null);
    }

    private void addListeners(){
        email.textProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                tEmail = newValue;
            }
        });
        password.textProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                tPass = newValue;
            }
        });
        logIn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                clear();
                //if tEmail and tPass are in the database;
                    //pass all the returned values for firstname, lastname, dob, status, gender, friendslist, and postslist to dashboardcontroller
                    Main.getPrimaryStage().setScene(Main.getDashboardPage());
                    Main.getDashboardController().setFirstName("John");
                    Main.getDashboardController().setLastName("McCain");
                    Main.getDashboardController().setDOB("12/12/2001");
                    Main.getDashboardController().setStatus("I'm at home");
                    Main.getDashboardController().setGender("Male");

                    Friend friend1 = new Friend("John@gmail.com", "Ken", "Yu","ken@gmail.com",false);
                    Friend friend2 = new Friend("John@gmail.com", "Kevin", "Yu","kevin@gmail.com",false);
                    ArrayList<Friend> friends = new ArrayList<>();
                    friends.add(friend1);
                    friends.add(friend2);
                    Main.getDashboardController().setFriendsList(friends);

                    Posts post1 = new Posts("whats up dude","12/10/2018",false);
                    Posts post2 = new Posts("hey" ,"12/10/2018",false);
                    ArrayList<Posts> posts = new ArrayList<>();
                    posts.add(post1);
                    posts.add(post2);
                    Main.getDashboardController().setPosts(posts);
            }
        });
        resetPassword.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println(tEmail + " " + tPass);
                //get values from email and password and pass to database
                //if tEmail and tPass are in database{
                    //change scene to dashboard
                    clear();
                    Main.getPrimaryStage().setScene(Main.getForgotPage());
                //}else{ print error }
            }
        });
        register.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                clear();
                Main.getPrimaryStage().setScene(Main.getRegPage());
            }
        });

    }
}
