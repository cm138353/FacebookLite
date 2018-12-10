package Classes;

import java.util.concurrent.ThreadLocalRandom;

public class Posts {

    private int id;
    private String post;
    private String dateAdded;
    private Boolean hidden;

    public Posts(String post, String dateAdded, boolean hidden){
        this.id = ThreadLocalRandom.current().nextInt(1, 1000);
        this.post = post;
        this.dateAdded = dateAdded;
        this.hidden = hidden;
    }

    @Override
    public String toString(){
        return ("Posted on " + this.dateAdded + ": \n" + this.post);
    }

    public int getID(){
        return this.id;
    }

    public void setHidden(boolean hidden){
        this.hidden = hidden;
    }

    public Boolean getHidden(){
        return this.hidden;
    }

}
