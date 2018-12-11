package Controllers;

import Classes.Profile;
import Classes.User;
import Daos.ProfileDao;
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
import org.bson.Document;
import sample.Main;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RegisterController {

    private static Dao userDao;
    private static Dao profileDao;

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
        profileDao = new ProfileDao();
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

                LocalDate today = LocalDate.now();                          //Today's date
                LocalDate birthday = LocalDate.of(DOB.getValue().getYear(),
                        DOB.getValue().getMonth(), DOB.getValue().getDayOfMonth());  //Birth date

                Period p = Period.between(birthday, today);
                String age = String.valueOf(p.getYears());
                String fName = firstName.getText();
                String lName = lastName.getText();
                String pass = password.getText();
                String mail = email.getText();
                String gen = gender.getValue();
                System.out.println(tDOB + " " + fName + " " + lName + " " + gen + " " + pass + " " + mail);
                List<User> users = userDao.getAll();
                Iterator<User> itr = users.iterator();
                boolean exists = false;
                while(itr.hasNext()) {
                    User user = itr.next();
                    if(user.getEmail().equals(mail)){
                        exists = true;
                    }
                }
                if(!exists){
                    // setting up user credentials in users collection within DB
                    userDao.save(new User(mail, pass));

                    // setting up profile info in profiles collection
                    ArrayList<String> pInfo = new ArrayList<>();
                    Document doc = userDao.find(new User(mail,pass));
                    pInfo.add(fName);
                    pInfo.add(lName);
                    pInfo.add(age);
                    pInfo.add(gen);
                    pInfo.add(doc.get("_id").toString());

                    Profile profile = new Profile(pInfo);

                    profileDao.save(profile);
                    String[] temp = {"add", "kamran"};
                    profileDao.update(profile, temp);

                    temp[0] = "remove";
                    profileDao.update(profile, temp);

                    clear();
                    //back to login page to sign in
                    Main.getPrimaryStage().setScene(Main.getLoginPage());
                }else {
                    //ERROR MESSAGE USER EXISTS ALREADY!!!! todo
                    //todo
                    //todo
                    //todo
                    clear();
                }

            }
        });
    }

}
