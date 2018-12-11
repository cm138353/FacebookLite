package Controllers;

import Classes.Posts;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import sample.Main;

import java.time.format.DateTimeFormatter;

public class PostController {

    @FXML
    private DatePicker dateBox;

    @FXML
    private TextArea postText;

    @FXML
    private Button postButton;

    @FXML
    private Button cancelButton;

    @FXML
    private void initialize(){
        addListeners();
    }

    private void addListeners(){
        postButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(dateBox.getValue()!= null && !postText.getText().isEmpty()) {
                    Posts post1 = new Posts(postText.getText(), dateBox.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")), false);
                    //add post to database here
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

}
