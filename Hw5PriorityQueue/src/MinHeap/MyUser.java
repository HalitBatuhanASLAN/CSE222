package MinHeap;

/**
 * Represents a user in the task management system
 */
public class MyUser
{
    private Integer ID;
    private Integer priority;

    /**
     * Constructor for MyUser
     * Time Complexity: O(1)
     * @param ID User ID
     * @param priority User priority (lower value means higher priority)
     */
    public MyUser(int ID, int priority)
    {
        this.ID = ID;
        this.priority = priority;
    }
    
    /**
     * Gets the user ID
     * Time Complexity: O(1)
     * @return User ID
     */
    public int getID()
    {
        return this.ID;
    }
    
    /**
     * Gets the user priority
     * Time Complexity: O(1)
     * @return User priority
     */
    public int getPriority()
    {
        return this.priority;
    }       
}
