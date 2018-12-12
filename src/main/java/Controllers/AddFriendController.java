package Controllers;

import Classes.Friend;
import Classes.Profile;
import Classes.User;
import Daos.ProfileDao;
import Daos.UserDao;
import Interface.Dao;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import org.bson.Document;
import sample.Main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class AddFriendController {

    @FXML
    private ListView<Friend> allUsers;

    @FXML
    private Button addButton;

    @FXML
    private Button backButton;

    private static Dao userDao;
    private static Dao profileDao;

    private static Profile profile;

    private static String email;

    @FXML
    void initialize() {
        userDao = new UserDao();
        profileDao = new ProfileDao();
        setFriendsList();
        addListeners();
    }

    private void addListeners(){
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ObservableList<Friend> selectedFriends;
                selectedFriends = allUsers.getSelectionModel().getSelectedItems();
                if(selectedFriends.size() == 1){
                    Main.getDashboardController().addFriend(selectedFriends.get(0));
                    //new
                    profileDao.update(profile,new String[]{"friends","add", selectedFriends.get(0).getProfileEmail() + ", " + selectedFriends.get(0).getFriendFirstName()
                    + ", " + selectedFriends.get(0).getFriendLastName() + ", " + selectedFriends.get(0).getFriendEmail()});
                    //allUsers.getItems().remove(selectedFriends.get(0));
                    Main.getPrimaryStage().setScene(Main.getDashboardPage());
                }
            }
        });

        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Main.getPrimaryStage().setScene(Main.getDashboardPage());
            }
        });
    }

    public void setProfile(Profile profile){
        this.profile = profile;
    }

    private void setFriendsList(){
        List<User> users = userDao.getAll();
        Iterator<User> itr = users.iterator();
        while(itr.hasNext()) {
            User user = itr.next();
            Document doc = (Document) userDao.find(user.getEmail());
            if(doc != null) {
                String id = doc.get("_id").toString();
                List<Profile> profiles = profileDao.getAll();
                Iterator<Profile> itr2 = profiles.iterator();
                while (itr2.hasNext()) {
                    Profile profile2 = itr2.next();
                    if (profile2.getCredId().equals(id)) {
                        Friend friend = new Friend(email,profile2.getFirst(),profile2.getLast(),user.getEmail());
                        allUsers.getItems().add(friend);
                        break;
                    }
                }
            }
        }
    }

    public void setEmail(String email){
        this.email = email;
    }

}
