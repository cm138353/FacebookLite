package Classes;

public class User
{
    private String email;
    private String password;
    private String fName;
    private String lName;

    public User(String e, String p) {
        this.email = e;
        this.password = p;
    }

    public User(String fName, String lName, String e, String p){
        this.email = e;
        this.password = p;
        this.lName = lName;
        this.fName = fName;
    }

    public User(String fName, String lName, String e){
        this.email = e;
        this.lName = lName;
        this.fName = fName;
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

    public String getfName(){
        return fName;
    }

    public void setfName(String fName){
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    @Override
    public String toString(){
        return (fName + " " + lName);
    }

}