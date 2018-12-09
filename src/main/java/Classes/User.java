package Classes;

public class User
{
    private String email;
    private String password;

    public User(String e, String p) {
        this.email = e;
        this.password = p;
    }

    public void setEmail(String email){

        this.email = email;
    }

    public String getEmail(){

        return email;
    }

    public void setPassword(String password){

        this.password = password;
    }

    public String getPassword(){

        return password;
    }


}