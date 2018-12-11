package Classes;

public class User {
    private String email;
    private String password;

    public User(String e, String p) {
        this.email = e;
        this.password = p;
    }


    public String getEmail() {

        return email;
    }

    public String getPassword() {

        return password;
    }
}