package sample;

import Classes.Profile;
import Classes.User;
import Controllers.AddFriendController;
import Controllers.DashboardController;
import Controllers.ForgotController;
import Controllers.LoginController;
import Controllers.PostController;
import Controllers.ProfileController;
import Controllers.RegisterController;
import Controllers.ResetController;
import Controllers.UpdateProfileController;
import Daos.ProfileDao;
import Daos.UserDao;
import Interface.Dao;
import com.mongodb.client.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.bson.Document;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

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
    private static Scene addPostPage;
    private static Scene updateProfPage;
    private static Scene addFriendPage;
    private static LoginController loginController;
    private static ForgotController forgotController;
    private static DashboardController dashboardController;
    private static RegisterController registerController;
    private static ResetController resetController;
    private static ProfileController profileController;
    private static PostController postController;
    private static UpdateProfileController updateProfileController;
    private static AddFriendController addFriendController;

    private static Dao userDao;
    private static Dao profileDao;

    @Override
    public void start(Stage primaryStage) throws Exception{

        //DbManager.clearDb();
        /*MongoDatabase db = DbManager.getDb("FBDB");
        db.drop();*/
        loadPages();

        primaryStage.setTitle("FacebookLite");
        primaryStage.setScene(login);
        this.primaryStage = primaryStage;


        primaryStage.show();

        /*String s = "kennysam21@yahoo.com, Jim, Yu, jim@gmail.com";
        List<String> items = Arrays.asList(s.split("\\s*,\\s*"));

        for (String item : items){
            System.out.println(item);
        }*/

        /*userDao = new UserDao();
        profileDao = new ProfileDao();
        printAll();*/




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

    public static Scene getAddPostPage() {
        return addPostPage;
    }

    public static Scene getUpdateProfPage() {
        return updateProfPage;
    }

    public static Scene getAddFriendPage() {
        return addFriendPage;
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

    public static PostController getPostController() {
        return postController;
    }

    public static UpdateProfileController getUpdateProfileController() {
        return updateProfileController;
    }

    public static AddFriendController getAddFriendController() {
        return addFriendController;
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
        addPostPage = new Scene(getScene("AddPost.fxml"));
        postController = loader.getController();
        updateProfPage = new Scene(getScene("UpdateProfile.fxml"));
        updateProfileController = loader.getController();
        addFriendPage = new Scene(getScene("AddFriend.fxml"));
        addFriendController = loader.getController();
    }

    private Parent getScene(String fxml) throws IOException {
        loader = new FXMLLoader(getClass().getClassLoader().getResource(fxml));
        root = loader.load();
        return root;
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void printAll(){
        List<User> users = userDao.getAll();
        Iterator<User> itr = users.iterator();
        while(itr.hasNext()) {
            User user = itr.next();
            //printing all emails and passwords in user database
            System.out.println(user.getEmail() + " " + user.getPassword());
            Document doc = (Document) userDao.find(user.getEmail());
            if(doc != null) {
                //gets user id
                String id = doc.get("_id").toString();
                //gets all profiles
                List<Profile> profiles = profileDao.getAll();
                Iterator<Profile> itr2 = profiles.iterator();
                while (itr2.hasNext()) {
                    Profile profile = itr2.next();
                    //System.out.println(profile.toString());
                    //System.out.println(profile.getCredId() + " " + id);
                    if (profile.getCredId().equals(id)) {

                        //profileDao.update(profile, new String[]{"posts","show"});
                        System.out.println(profile.getFriendsList().get(0));
                        break;
                    }
                }
            }
        }
    }
}
