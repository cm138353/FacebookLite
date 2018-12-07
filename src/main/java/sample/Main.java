package sample;

import collections.Profile;
import collections.User;
import collections.Wall;
import com.mongodb.Block;
import com.mongodb.DB;
import com.mongodb.client.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.bson.Document;

import java.util.List;

public class Main extends Application {

    private MongoClient mongoClient;
    private MongoDatabase people;
    private MongoDatabase wall;
    private MongoCollection<Document> profiles;
    private MongoCollection<Document> users;

    private User user;
    private Profile profile;

    @Override
    public void start(Stage primaryStage) throws Exception{
        mongoClient =  MongoClients.create();
        people = mongoClient.getDatabase("people");
        wall = mongoClient.getDatabase("wall");
        profiles = people.getCollection("profiles");
        users = people.getCollection("users");

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Login.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root));







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


    public static void main(String[] args) {
        launch(args);
    }
}
