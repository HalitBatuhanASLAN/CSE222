package MinHeap;

public class MyUser
{
    private Integer ID;
    private Integer priority;

    public MyUser(int ID,int priority)
    {
        this.ID = ID;
        this.priority = priority;
    }
    public int getID(){return this.ID;}
    public int getPriority(){return this.priority;}       
}
