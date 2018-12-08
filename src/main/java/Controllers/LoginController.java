package Controllers;

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
import java.util.ResourceBundle;

public class LoginController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

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

    private String text1;
    private String text2;

    public void clear(){
        email.setText(null);
        password.setText(null);
    }

    private void addListeners(){
        email.textProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                text1 = newValue;
            }
        });
        password.textProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                text2 = newValue;
            }
        });
        logIn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                clear();
                //todo
            }
        });
        resetPassword.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println(text1 + " " + text2);
                //get values from email and password and pass to database
                //if email and password are in database{
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