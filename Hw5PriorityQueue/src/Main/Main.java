package Main;

import MinHeap.MyTask;
import MinHeap.TaskManager;

public class Main
{
    public static void main(String args[])
    {
        String fileName = args[0];
        TaskManager<MyTask> taskManager = new TaskManager<MyTask>();
        taskManager.creatingUsers(fileName);
        taskManager.takingTasks();
    }
}