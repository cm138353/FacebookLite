package Controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
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
    void initialize()
    {
        addListeners();
    }

    public void clear(){
        email.setText(null);
        DOB.setValue(null);
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
                clear();
                Main.getPrimaryStage().setScene(Main.getLoginPage());
            }
        });
        confirm.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if (DOB.getValue() != null) {
                    tDOB = DOB.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                }
                System.out.println(tEmail + " " + tDOB);
                //if tEmail and tDOB is in database
                    //go to next page
                    clear();
                    Main.getPrimaryStage().setScene(Main.getResetPassPage());
                    Main.getResetController().setEmail(tEmail);
                //else
                    //print error message
            }
        });
    }
}
