package Controllers;

import Classes.Posts;
import Classes.Profile;
import Daos.ProfileDao;
import Interface.Dao;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import sample.Main;

import java.time.format.DateTimeFormatter;

public class PostController {

    private static Dao profileDao;

    @FXML
    private DatePicker dateBox;

    @FXML
    private TextArea postText;

    @FXML
    private Button postButton;

    @FXML
    private Button cancelButton;

    private Profile profile;

    @FXML
    private void initialize(){
        profileDao = new ProfileDao();
        addListeners();
    }

    private void addListeners(){
        postButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(dateBox.getValue()!= null && !postText.getText().isEmpty()) {
                    Posts post1 = new Posts(postText.getText(), dateBox.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
                    //add post to database here
                    String postItem = postText.getText() + ", " + dateBox.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                    profileDao.update(profile,new String[]{"posts","add", postItem});

                    Main.getDashboardController().addPost(post1);
                    dateBox.setValue(null);
                    postText.setText(null);
                    Main.getPrimaryStage().setScene(Main.getDashboardPage());
                }else {
                    System.out.println("non valid post");
                }
            }
        });

        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dateBox.setValue(null);
                postText.setText(null);
                Main.getPrimaryStage().setScene(Main.getDashboardPage());
            }
        });
    }

    public void setProfile(Profile profile){
        this.profile = profile;
    }

}
