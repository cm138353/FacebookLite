package Controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import sample.Main;

import java.time.format.DateTimeFormatter;

public class UpdateProfileController {

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private DatePicker DOB;

    @FXML
    private ComboBox<String> gender;

    @FXML
    private Button saveButton;

    @FXML
    private Button cancel;

    @FXML
    private void initialize(){
        clear();
        addListeners();
    }

    private void clear(){
        DOB.setValue(null);
        firstName.setText(null);
        lastName.setText(null);
    }

    private void addListeners(){
        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //overwrite old first name, lastname, DOB, or gender from database here
                Main.getDashboardController().setFirstName(firstName.getText());
                Main.getDashboardController().setLastName(lastName.getText());
                Main.getDashboardController().setDOB(DOB.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
                Main.getDashboardController().setGender(gender.getValue());
                clear();
                Main.getPrimaryStage().setScene(Main.getDashboardPage());
            }
        });

        cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                clear();
                Main.getPrimaryStage().setScene(Main.getDashboardPage());
            }
        });

    }

}
