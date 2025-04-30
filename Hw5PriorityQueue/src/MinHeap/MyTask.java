package MinHeap;

/**
 * Represents a task in the task management system
 * Tasks are comparable based on user priority and task ID
 */
public class MyTask implements Comparable<MyTask>
{
    private MyUser user;
    private Integer id;
    
    /**
     * Constructor for MyTask
     * Time Complexity: O(1)
     * @param user The user associated with this task
     * @param id Task ID
     */
    public MyTask(MyUser user,int id)
    {
        this.user = user;
        this.id = id;
    }
    
    /**
     * Returns a string representation of the task
     * Time Complexity: O(1)
     * @return String in format "Task <id> user <userID>"
     */
    public String toString()
    {
        return "Task " + this.id + " user " + user.getID();  
    }

    /**
     * Compares this task with another task based on user priority and task ID
     * Tasks are ordered first by user priority (lower value means higher priority)
     * If user priorities are equal, tasks are ordered by their IDs (lower ID means higher priority)
     * Time Complexity: O(1)
     * @param other The task to compare with
     * @return -1 if this task has higher priority, 1 if other task has higher priority, 
     *         or the difference between task IDs if user priorities are equal
     */
    public int compareTo(MyTask other)
    {
        //now our user is more important 
        if(this.user.getPriority() < other.user.getPriority())
            return -1;
        else if(this.user.getPriority() > other.user.getPriority())
            return 1;
        else
            return this.id - other.id;
    }
}