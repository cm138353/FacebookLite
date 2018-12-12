package Classes;

import java.util.concurrent.ThreadLocalRandom;

public class Posts {

    private int id;
    private String content;
    private String dateAdded;

    public Posts(String post, String dateAdded){
        this.id = ThreadLocalRandom.current().nextInt(1, 1000);
        this.content = post;
        this.dateAdded = dateAdded;
    }

    @Override
    public String toString(){
        return ("Posted on " + this.dateAdded + ": \n" + this.content);
    }

    public int getID(){
        return this.id;
    }

    public String getContent() { return  content;}

    public String getDateAdded() {
        return dateAdded;
    }
}