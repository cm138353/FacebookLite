package Controllers;

import Classes.Friend;
import Classes.Post;
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
                //clear();
                //if tEmail and tPass are in the database;
                    //pass all the returned values for firstname, lastname, dob, status, gender, friendslist, and postslist to dashboardcontroller
                    User user = new User("a","a","a","a");
                    Main.getPrimaryStage().setScene(Main.getDashboardPage(user));
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
