package sample;

import collections.Profile;
import collections.User;
import com.mongodb.client.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.bson.Document;

import java.io.IOException;

public class Main extends Application {

    private MongoClient mongoClient;
    private MongoDatabase people;
    private MongoDatabase wall;
    private MongoCollection<Document> profiles;
    private MongoCollection<Document> users;

    private User user;
    private Profile profile;
    private static Stage primaryStage;
    public static Scene login;
    public static Scene forgotPass;
    public static Scene dashboard;
    public static Scene registerAcc;
    public static Scene resetPass;

    @Override
    public void start(Stage primaryStage) throws Exception{
        mongoClient =  MongoClients.create();
        people = mongoClient.getDatabase("people");
        wall = mongoClient.getDatabase("wall");
        profiles = people.getCollection("profiles");
        users = people.getCollection("users");

        loadPages();

        primaryStage.setTitle("FacebookLite");
        primaryStage.setScene(login);
        this.primaryStage = primaryStage;







        //===========================================
        /*
        Block<Document> printBlock = new Block<Document>() {

            public void apply(final Document document) {
                System.out.println(document.toJson());
            }
        };
        users.find().forEach(printBlock);
        */
        //============================================

        MongoCursor<Document> cursor = users.find().iterator();
        try {
            while (cursor.hasNext()) {
                System.out.println(cursor.next().toJson());
            }
        } finally {
            cursor.close();
        }


        primaryStage.show();


    }

    public static Stage getPrimaryStage(){
        return primaryStage;
    }

    public static Scene getForgotPage(){
        return forgotPass;
    }

    public static Scene getRegPage(){
        return registerAcc;
    }

    public static Scene getLoginPage(){
        return login;
    }

    public static Scene getResetPassPage(){
        return resetPass;
    }

    private void loadPages() throws IOException {
        login = new Scene((Parent)FXMLLoader.load(getClass().getClassLoader().getResource("LoginPage.fxml")));
        forgotPass = new Scene((Parent)FXMLLoader.load(getClass().getClassLoader().getResource("ForgotPassword.fxml")));
        registerAcc = new Scene((Parent)FXMLLoader.load(getClass().getClassLoader().getResource("RegisterPage.fxml")));
        resetPass = new Scene((Parent)FXMLLoader.load(getClass().getClassLoader().getResource("ResetPassword.fxml")));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
