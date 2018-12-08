package Controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import sample.Main;

import java.time.format.DateTimeFormatter;

public class ForgotController {

    @FXML
    private TextField email;

    @FXML
    private DatePicker DOB;

    @FXML
    private Button confirm;

    @FXML
    private Button cancel;

    @FXML
    void initialize() {
        addListeners();
    }

    private static String tEmail;
    private String tDOB;

    private void addListeners(){
        email.textProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                tEmail = newValue;
            }
        });
        cancel.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                Main.getPrimaryStage().setScene(Main.getLoginPage());
            }
        });
        confirm.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if (DOB.getValue() != null) {
                    tDOB = DOB.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                }
                System.out.println(tEmail + " " + tDOB);
                //if email and DOB is in database
                    //go to next page
                    Main.getPrimaryStage().setScene(Main.getResetPassPage());
                //else
                    //print error message
            }
        });
    }

    protected static String getEmail(){
        return tEmail;
    }

}
