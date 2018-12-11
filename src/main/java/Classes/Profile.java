package Classes;

//import sample.Friends;

import java.util.ArrayList;
import java.util.Iterator;

public class Profile {
    private String first;
    private String last;
    private Integer age;
    private String gender;
    private String credId;

    /*  GIT Blame
    public Profile(ArrayList<String> info){
        Iterator<String> itr = info.iterator();

        this.first = itr.next();
        this.last = itr.next();
        this.age = itr.next();
        this.gender = itr.next();
        this.credId = itr.next();

    }*/

    public Profile(String first, String last, Integer age, String gender, String credId){
        this.first = first;
        this.last = last;
        this.age = age;
        this.gender = gender;
        this.credId = credId;
    }

    public Profile(String first, String last, String age, String gender, String credId){
        this.first = first;
        this.last = last;
        this.age = Integer.parseInt(age);
        this.gender = gender;
        this.credId = credId;
    }

    public String getFirst(){ return first; }
    public void setFirst(String f){ first = f; }

    public String getLast(){ return last; }
    public void setLast(String l){ last = l; }

    public Integer getAge(){ return age; }
    public void setAge(String d){ age = Integer.parseInt(d); }

    public String getGender(){ return gender; }
    public void setGender(String g){ gender = g; }

    public String getCredId(){ return credId; }
    public void setCredId(String c){ credId = c; }

    @Override
    public String toString() {
        return "Profile: [" + first + ", "+ last + ", "+ age + ", "+ gender + ", "+ credId + "]";
    }
}