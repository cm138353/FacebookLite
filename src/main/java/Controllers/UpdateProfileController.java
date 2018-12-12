package Controllers;

import Classes.Profile;
import Daos.ProfileDao;
import Interface.Dao;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import sample.Main;

import java.time.LocalDate;
import java.time.Period;
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

    private Profile profile;

    private static Dao profileDao;

    @FXML
    private void initialize(){
        profileDao = new ProfileDao();
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
                LocalDate today = LocalDate.now();                          //Today's date
                LocalDate birthday = LocalDate.of(DOB.getValue().getYear(),
                        DOB.getValue().getMonth(), DOB.getValue().getDayOfMonth());  //Birth date

                Period p = Period.between(birthday, today);
                String age = String.valueOf(p.getYears());

                Main.getDashboardController().setFirstName(firstName.getText());
                Main.getDashboardController().setLastName(lastName.getText());
                Main.getDashboardController().setDOB(age);
                Main.getDashboardController().setGender(gender.getValue());
                String first = firstName.getText();
                String last =lastName.getText();
                String gend = gender.getValue();

                //new
                profileDao.update(profile,new String[]{"first","update",first});
                profileDao.update(profile,new String[]{"last","update",last});
                profileDao.update(profile,new String[]{"gender","update",gend});
                profileDao.update(profile,new String[]{"age","update",age});
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

    public void setProfile(Profile profile){
        this.profile = profile;
    }

}
