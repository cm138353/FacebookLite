package Controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import sample.Main;

public class ResetController {

    @FXML
    private PasswordField newPassword;

    @FXML
    private Button confirm;

    @FXML
    private Button cancel;

    @FXML
    void initialize() {
        addListeners();
    }

    private void clear(){
        newPassword.setText(null);
    }

    private void addListeners(){
        confirm.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                //overwrite database with new password by checking email
                String email = ForgotController.getEmail();
                String nPassword = newPassword.getText();
                System.out.println(email + " " + nPassword);
                // db.users.find(email).setPass(nPassword)
                //go back to login page
                clear();
                Main.getPrimaryStage().setScene(Main.getLoginPage());
            }
        });
        cancel.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                clear();
                Main.getPrimaryStage().setScene(Main.getLoginPage());
            }
        });
    }
}
