package Main;

import MinHeap.MyTask;
import MinHeap.TaskManager;

/**
 * Main class that serves as the entry point for the task management application
 */
public class Main
{
    /**
     * Main method that initializes the TaskManager and starts the application
     * Time Complexity: Depends on the TaskManager methods called
     * - creatingUsers: O(n) where n is the number of lines in the input file
     * - takingTasks: O(m log m) where m is the number of tasks added
     * 
     * @param args Command line arguments, where args[0] should be the filename containing user priorities
     */
    public static void main(String args[])
    {
        String fileName = args[0];
        TaskManager<MyTask> taskManager = new TaskManager<MyTask>();
        taskManager.creatingUsers(fileName);
        taskManager.takingTasks();
    }
}