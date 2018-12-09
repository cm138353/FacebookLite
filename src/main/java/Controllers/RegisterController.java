package Controllers;

import Classes.User;
import Daos.UserDao;
import Interface.Dao;
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

    private static Dao userDao;

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
        userDao = new UserDao();
    }

    public void clear(){
        firstName.setText(null);
        lastName.setText(null);
        email.setText(null);
        password.setText(null);
        DOB.setValue(null);
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
                userDao.save(new User(mail, pass));
                clear();
                Main.getPrimaryStage().setScene(Main.getLoginPage());
            }
        });
    }

}
