package Controllers;

import Classes.Friend;
import Classes.Posts;
import Classes.Profile;
import Classes.User;
import Daos.ProfileDao;
import Daos.UserDao;
import Interface.Dao;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.bson.Document;
import sample.Main;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

public class LoginController {

    private Dao userDao;
    private Dao profileDao;

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
        userDao = new UserDao();
        profileDao = new ProfileDao();
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
                Friend friend1 = new Friend("John@gmail.com", "Ken", "Yu","ken@gmail.com",false);
                Friend friend2 = new Friend("John@gmail.com", "Kevin", "Yu","kevin@gmail.com",false);
                ArrayList<Friend> friends = new ArrayList<>();
                friends.add(friend1);
                friends.add(friend2);


                Posts post1 = new Posts("whats up dude","12/10/2018",false);
                Posts post2 = new Posts("hey" ,"12/10/2018",false);
                ArrayList<Posts> posts = new ArrayList<>();
                posts.add(post1);
                posts.add(post2);


                List<User> users = userDao.getAll();
                Iterator<User> itr = users.iterator();
                User foundUser = new User("","");
                System.out.println(tEmail + " | " + tPass + "\n");
                while(itr.hasNext()) {
                    User user = itr.next();
                    Document doc = (Document) userDao.find(tEmail);
                    System.out.println(doc.toString());
                    if(doc != null) {
                        System.out.println(doc.toString());

                        if (doc.get("email").toString().equals(tEmail) && doc.get("password").toString().equals(tPass)) {
                            System.out.println("Success");
                            clear();
                            String id = doc.get("_id").toString();
                            List<Profile> profiles = profileDao.getAll();
                            Iterator<Profile> itr2 = profiles.iterator();
                            while (itr2.hasNext()){
                                Profile profile = itr2.next();
                                System.out.println(profile.toString());
                                System.out.println(profile.getCredId() + " " + id);
                                if(profile.getCredId().equals(id)){
                                    System.out.println("equal id");
                                    Main.getDashboardController().setHidePosts(profile.getHidePosts());
                                    Main.getDashboardController().setHideStatus(profile.getHideStatus());
                                    Main.getDashboardController().setHideFriends(profile.getHideFriends());
                                    Main.getDashboardController().setHideAge(profile.getHideAge());
                                    Main.getDashboardController().setFriendsList(friends);
                                    Main.getDashboardController().setPosts(posts);
                                    Main.getDashboardController().setStatus(profile.getStatusContent());
                                    Main.getDashboardController().setFirstName(profile.getFirst());
                                    Main.getDashboardController().setLastName(profile.getLast());
                                    Main.getDashboardController().setGender(profile.getGender());
                                    Main.getDashboardController().setDOB(profile.getAge());
                                    Main.getPrimaryStage().setScene(Main.getDashboardPage());
                                    break;
                                }
                            }
                            break;
                        }
                    }
                }

            }
        });
        resetPassword.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println(tEmail + " ? " + tPass);
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
