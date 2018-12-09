package sample;

import Classes.Profile;
import Classes.User;
import com.mongodb.client.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

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


        loadPages();

        primaryStage.setTitle("FacebookLite");
        primaryStage.setScene(login);
        this.primaryStage = primaryStage;


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
