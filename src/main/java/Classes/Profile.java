package Classes;

//import sample.Friends;

import java.util.ArrayList;
import java.util.Iterator;

public class Profile {
    private String first;
    private String last;
    private String age;
    private String gender;
    private String credId;
    private ArrayList<String> friends;

    public Profile(ArrayList<String> info){
        Iterator<String> itr = info.iterator();

        this.first = itr.next();
        this.last = itr.next();
        this.age = itr.next();
        this.gender = itr.next();
        this.credId = itr.next();
        this.friends = new ArrayList<>();
    }

    public String getFirst(){ return first; }
    public void setFirst(String f){ first = f; }

    public String getLast(){ return last; }
    public void setLast(String l){ last = l; }

    public String getAge(){ return age; }
    public void setAge(String d){ age = d; }

    public String getGender(){ return gender; }
    public void setGender(String g){ gender = g; }

    public String getCredId(){ return credId; }
    public void setCredId(String c){ credId = c; }

}