package Controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.Main;

import java.time.format.DateTimeFormatter;

public class RegisterController {

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private DatePicker DOB;

    @FXML
    private ComboBox<String> gender;

    @FXML
    private TextField email;

    @FXML
    private Button registerButton;

    @FXML
    private PasswordField password;

    @FXML
    private Button cancel;


    @FXML
    void initialize() {
        addListeners();
    }

    private void addListeners(){
        cancel.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                Main.getPrimaryStage().setScene(Main.getLoginPage());
            }
        });
        registerButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                String tDOB = "";
                if (DOB.getValue() != null){
                    tDOB = DOB.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                }
                String fName = firstName.getText();
                String lName = lastName.getText();
                String pass = password.getText();
                String mail = email.getText();
                String gen = gender.getValue();
                System.out.println(tDOB + " " + fName + " " + lName + " " + gen + " " + pass + " " + mail);
                //back to login page to sign in
                Main.getPrimaryStage().setScene(Main.getLoginPage());
            }
        });
    }

}
