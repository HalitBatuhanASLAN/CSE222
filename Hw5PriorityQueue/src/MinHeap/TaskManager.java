package MinHeap;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Manages tasks and users in a priority-based system
 * @param <T> Type that extends Comparable (Note: This generic parameter is not used in the class)
 */
public class TaskManager<T extends Comparable<T>>
{
    private MyPriorityQueue<MyTask> tasks = new MinHeap<>();
    private ArrayList<MyUser> users = new ArrayList<>();

    /**
     * Creates users from a file where each line represents a user's priority
     * Time Complexity: O(n) where n is the number of lines in the file
     * @param fileName The name of the file containing user priorities
     */
    public void creatingUsers(String fileName)
    {
        try
        {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            int id = 0;
            while (scanner.hasNextLine())
            {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty())
                {
                    try
                    {
                        int value = Integer.parseInt(line);
                        if(value < 0)
                            System.err.println("Priority can not be cmaller than 0");
                        else
                        {
                            MyUser user = new MyUser(id,value);
                            users.add(user);
                            id++;
                        }
                    }catch(NumberFormatException e) {
                        System.err.println("No integer number in line: " + line);
                    }
                }
            }
            scanner.close();
        }catch(FileNotFoundException e) {
            System.err.println("File could not find: " + e.getMessage());
        }
    }
    
    /**
     * Takes task inputs from the user via command line
     * Time Complexity: O(m log m) where m is the number of tasks added
     * (Each task addition to the heap is O(log m))
     */
    public void takingTasks()
    {
        Scanner scanner = new Scanner(System.in);
        String command;
        
        System.out.println("\nEnter commands (type 'execute' to process all tasks)");
        System.out.print("User id can be between 0 and ");
        System.out.println(users.size()-1);
        int line = 0;
        while(true)
        {
            System.out.print("> ");
            command = scanner.nextLine().trim();
            if(command.equalsIgnoreCase("execute"))
            {
                break;
            }
            else
            {
                try
                {
                    int userId = Integer.parseInt(command);
                    if(userId < 0 || userId >= users.size())
                        System.err.println("User id must be between 0 and " + users.size());
                    else
                    {
                        MyUser user = users.get(userId);
                        MyTask task = new MyTask(user,line);
                        tasks.add(task);
                        line++;
                    }
                }
                catch(NumberFormatException e)
                {
                    System.err.println("Invalid command. Please enter a user ID or 'execute'.");
                }
            }
        }
        System.out.println("Execute command received. Processing...");
        scanner.close();
        executeTasks();
    }

    /**
     * Executes all tasks in priority order
     * Time Complexity: O(m log m) where m is the number of tasks
     * (Each poll operation from the heap is O(log m))
     */
    private void executeTasks()
    {
        MyTask task;
        while(!tasks.isEmpty())
        {
            task = tasks.poll();
            System.out.println(task.toString());
        }
        System.out.println("All tasks are completed");
    }
}