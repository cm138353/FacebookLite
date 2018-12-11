package sample;

import Classes.Profile;
import Classes.User;
import Controllers.DashboardController;
import Controllers.ForgotController;
import Controllers.LoginController;
import Controllers.ProfileController;
import Controllers.RegisterController;
import Controllers.ResetController;
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
    FXMLLoader loader;
    Parent root;
    private static Stage primaryStage;
    private static Scene login;
    private static Scene forgotPass;
    private static Scene dashboard;
    private static Scene registerAcc;
    private static Scene resetPass;
    private static Scene profilePage;
    private static LoginController loginController;
    private static ForgotController forgotController;
    private static DashboardController dashboardController;
    private static RegisterController registerController;
    private static ResetController resetController;
    private static ProfileController profileController;

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

    public static Scene getDashboardPage(){
        return dashboard;
    }

    public static Scene getProfilePage(){
        return profilePage;
    }

    public static ProfileController getProfileController(){
        return profileController;
    }

    public static DashboardController getDashboardController() {
        return dashboardController;
    }

    public static ForgotController getForgotController() {
        return forgotController;
    }

    public static RegisterController getRegisterController() {
        return registerController;
    }

    public static ResetController getResetController() {
        return resetController;
    }

    public static LoginController getLoginController() {
        return loginController;
    }

    private void loadPages() throws IOException {
        /*login = new Scene((Parent)FXMLLoader.load(getClass().getClassLoader().getResource("LoginPage.fxml")));
        forgotPass = new Scene((Parent)FXMLLoader.load(getClass().getClassLoader().getResource("ForgotPassword.fxml")));
        registerAcc = new Scene((Parent)FXMLLoader.load(getClass().getClassLoader().getResource("RegisterPage.fxml")));
        resetPass = new Scene((Parent)FXMLLoader.load(getClass().getClassLoader().getResource("ResetPassword.fxml")));
        dashboard = new Scene((Parent)FXMLLoader.load(getClass().getClassLoader().getResource("Dashboard.fxml")));*/
        login = new Scene(getScene("LoginPage.fxml"));
        loginController = loader.getController();
        forgotPass =  new Scene(getScene("ForgotPassword.fxml"));
        forgotController = loader.getController();
        registerAcc =  new Scene(getScene("RegisterPage.fxml"));
        registerController = loader.getController();
        resetPass =  new Scene(getScene("ResetPassword.fxml"));
        resetController = loader.getController();
        dashboard =  new Scene(getScene("Dashboard.fxml"));
        dashboardController = loader.getController();
        profilePage =  new Scene(getScene("FriendProfile.fxml"));
        profileController = loader.getController();
    }

    private Parent getScene(String fxml) throws IOException {
        loader = new FXMLLoader(getClass().getClassLoader().getResource(fxml));
        root = loader.load();
        return root;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
