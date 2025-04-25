package MinHeap;

public class MyTask implements Comparable<MyTask>
{
    private MyUser user;
    private Integer id;
    
    public MyTask(MyUser user,int id)
    {
        this.user = user;
        this.id = id;
    }
    public String toString()
    {
        return "Request " + this.id + " user " + user.getID();  
    }

    public int compareTo(MyTask other)
    {
        /*now our user is more important */
        if(this.user.getPriority() < other.user.getPriority())
            return -1;
        else if(this.user.getPriority() > other.user.getPriority())
            return 1;
        else
            return 0;
    }
    
}
