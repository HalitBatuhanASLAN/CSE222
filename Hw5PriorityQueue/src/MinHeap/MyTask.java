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
        return Integer.compare(this.user.getPriority(), other.user.getPriority());
    }

    
}
