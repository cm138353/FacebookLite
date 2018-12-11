package Classes;

import java.util.concurrent.ThreadLocalRandom;

public class Posts {

    private int id;
    private String content;
    private String dateAdded;
    private Boolean hidden;

    public Posts(String post, String dateAdded, boolean hidden){
        this.id = ThreadLocalRandom.current().nextInt(1, 1000);
        this.content = post;
        this.dateAdded = dateAdded;
        this.hidden = hidden;
    }

    @Override
    public String toString(){
        return ("Posted on " + this.dateAdded + ": \n" + this.content);
    }

    public int getID(){
        return this.id;
    }

    public String getContent() { return  content;}

    public void setHidden(boolean hidden){
        this.hidden = hidden;
    }

    public Boolean getHidden(){
        return this.hidden;
    }

}