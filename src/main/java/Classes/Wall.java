package Classes;

//import sample.IDisplay;
//import sample.Stack;
//import sample.Util;

public class Wall
{
    //private Stack posts;
    //private String[] posts;
    //private int index;  // keep track of posts --- set to -1 to prevent crashes and know when array is empty because position zero is used by user for posts
    private boolean isPostsVis;
    private int NOPosts; // number of posts
    
    public Wall()
    {
      //  posts = new Stack(10);
        //index = -1;
        isPostsVis = true;
        //Util.init(posts);
        NOPosts = 0;
    }
    /*
    public boolean isEmpty()
    {
        return index == -1;
    }
    
    public boolean isFull()
    {
        return index >= (posts.length -1); // can also remove = and (cont below)
    }
    */
    public void addPost(String post)
    {
        /*
        if (!isFull())
        {
            posts[++index] = post;  // do index++ \n posts[index] = post;
        }
        */
        //posts.push(post);
        NOPosts++;
    }
    
    public void deleteAllPosts()
    {
        /*
        Util.init(posts);
        index = -1;
        */
        //posts.clear();
        NOPosts = 0;
    }
    
    public void deleteLastPost()
    {
        /*
        if (!isEmpty())
        {
            posts[index--] = "";
        }
        */
        //posts.pop();
        NOPosts--;
    }
    
    public void display()
    {
        //Util.print("Posts:");
        if (isPostsVis) //&& !isEmpty())
        {
            /*
            for (int x = 0 ; x <= index ; x++)
            {
                Util.print(posts[x]);
            }
            */
            //Util.print(posts.getElements());
        }
        else
        {
            //Util.print("No visible posts to Display");
        }
    }
    
    public void toggleVis()
    {
        isPostsVis = !isPostsVis;
    }
    
    public int getNOPosts()
    {
        return NOPosts;
    }
    
   //public String getFormattedPosts(int z)
    {
        //return posts.getElements()[z];
    }
    
    public boolean postsVis()
    {
        return isPostsVis;
    }
}

