package MinHeap;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class TaskManager<T extends Comparable<T>>
{
    private MinHeap<MyTask> tasks = new MinHeap<>();
    private ArrayList<MyUser> users = new ArrayList<>();

    /*AI used for readining file part*/
    /*
    private MinHeap<MyUser> users;
    public void creatingUsers(String fileName)
    {
        int value,id = 0;
        try
        {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            
            while (scanner.hasNextLine())
            {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty())
                {
                    try
                    {
                        value = Integer.parseInt(line);
                        MyUser user = new MyUser(id,value);
                        users.add(user);
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
    */
    public void creatingUsers(String fileName)
    {
        int value,id = 0;
        try
        {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            
            while (scanner.hasNextLine())
            {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty())
                {
                    try
                    {
                        value = Integer.parseInt(line);
                        MyUser user = new MyUser(id,value);
                        users.add(user);
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
    public void takingTasks()
    {
        Scanner scanner = new Scanner(System.in);
        String command;
        
        System.out.println("\nEnter commands (type 'execute' to process all tasks):");
        
        while(true)
        {
            System.out.print("> ");
            command = scanner.nextLine().trim();
            if(command.equalsIgnoreCase("execute"))
            {
                break;  // execute komutu gelince döngüden çık
            }
            else
            {
                try
                {
                    int userId = Integer.parseInt(command);
                    MyTask task = new MyTask(users.get(userId), userId);
                    tasks.add(task);
                }
                catch(NumberFormatException e)
                {
                    System.out.println("Invalid command. Please enter a user ID or 'execute'.");
                }
            }
        }
        System.out.println("Execute command received. Processing...");
        executeTasks();
        
        scanner.close();
    }

    private void executeTasks()
    {
        MyTask task;
        while(!tasks.isEmpty())
        {
            task = tasks.poll();
            task.toString();
        }
        System.out.println("All tasks are completed");
    }
}