package Main;

import HwSystem.*;
import HwSystem.Protocols.*;
import java.util.Scanner;
import java.util.List;
import java.util.Queue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.io.File;

/**
 * Main class that serves as the entry point for the hardware system application.
 * It handles system configuration, user commands, and interaction with hardware devices.
 */

public class Main
{
    /**
     * Main method that initializes the hardware system from a configuration file
     * and starts the command interface.
     * 
     * @param args Command line arguments, where args[0] is the configuration file path
     */
    public static void main(String args[])
    {
        String fileName = args[0];
        String logPath = args[1];
        File logDir = new File(logPath);
        if(!logDir.exists())
            logDir.mkdirs();
        
        HwSystem hwSystem = fileReadining(fileName,logDir);
        System.out.println();
        commands(hwSystem);
    }

    /**
     * Processes user commands through an interactive command-line interface.
     * Supports various commands for hardware control including device management,
     * sensor reading, display control, wireless communication, and motor control.
     * 
     * @param hwSystem The hardware system instance to operate on
     */

     public static void commands(HwSystem hwSystem)
     {
         List<String> command = new LinkedList<>();
         Scanner scanner = new Scanner(System.in);
         System.out.print("Commands;\n");
         String commandString = scanner.nextLine();
         
         List<String> commandParts = new LinkedList<>(Arrays.asList(commandString.split(" ")));
                 
         while(!commandParts.get(0).equals("exit"))
         {
             command.add(commandString);
             commandString = scanner.nextLine();
             commandParts = new LinkedList<>(Arrays.asList(commandString.split(" ")));
         }
         command.add(commandString);//to keep exit linkedList
         Iterator<String> iterator = command.iterator();
         System.out.println();
         while(iterator.hasNext())
         {
             commandString = iterator.next();
             commandParts = new LinkedList<>(Arrays.asList(commandString.split(" ")));
             int portId;
             int devId;
             String devType;
             switch(commandParts.get(0))
             {
                 case "turnON":
                     portId = Integer.parseInt(commandParts.get(1));
                     hwSystem.turnOn(portId);
                     break;
                 case "turnOFF":
                     portId = Integer.parseInt(commandParts.get(1));
                     hwSystem.turnOff(portId);
                     break;
                 case "addDev":
                     String devNameString = commandParts.get(1);
                     portId = Integer.parseInt(commandParts.get(2));
                     devId = Integer.parseInt(commandParts.get(3));
                     hwSystem.addDev(devNameString, portId, devId);
                     break;
                 case "rmDev":
                     portId = Integer.parseInt(commandParts.get(1));
                     hwSystem.rmDev(portId);
                     break;
                 case "list":
                     if(commandParts.get(1).equals("ports"))
                         hwSystem.listPorts();
                     else if(commandParts.get(1).equals("Sensor") || commandParts.get(1).equals("MotorDriver")
                         || commandParts.get(1).equals("WirelessIO") || commandParts.get(1).equals("Display"))
                     {
                         devType = commandParts.get(1);
                         hwSystem.listDevType(devType);
                     }
                     else
                         System.err.println("Please enter a valid device name or enter 'ports'!!!");
                     break;
                 case "readSensor":
                     devId = Integer.parseInt(commandParts.get(1));
                     hwSystem.readSensor(devId);
                     break;
                 case "printDisplay":
                     devId = Integer.parseInt(commandParts.get(1));
                     String data = "";
                     Iterator<String> partsIterator2 = commandParts.listIterator(2);
                     while (partsIterator2.hasNext())
                     {
                         data += partsIterator2.next();
                         if (partsIterator2.hasNext())
                             data += " ";
                     }
                     
                     /*for (int i = 2; i < commandParts.size(); i++)
                     {
                         data += commandParts.get(i);
                         if (i < commandParts.size() - 1)
                             data += " ";
                     }*/
                     hwSystem.printDisplay(devId,data);
                     break;
                 case "readWireless":
                     devId = Integer.parseInt(commandParts.get(1));
                     hwSystem.readWireless(devId);
                     break;
                 case "writeWireless":
                     devId = Integer.parseInt(commandParts.get(1));
                     String data2 = "";
                     Iterator<String> partsIterator = commandParts.listIterator(2);
                     while (partsIterator.hasNext())
                     {
                         data2 += partsIterator.next();
                         if (partsIterator.hasNext())
                             data2 += " ";
                     }
                     /*for (int i = 2; i < commandParts.size(); i++)
                     {
                         data2 += commandParts.get(i);
                         if (i < commandParts.size() - 1)
                             data2 += " ";
                     }*/
                     hwSystem.writeWireless(devId,data2);
                     break;
                 case "setMotorSpeed":
                     devId = Integer.parseInt(commandParts.get(1));
                     int speed = Integer.parseInt(commandParts.get(2));
                     hwSystem.setMotorSpeed(devId,speed);
                     break;
                 case "exit":
                     System.out.println("Exitting...");
                     break;
                 default:
                     System.err.println("Please enter a valid command!!!");
                     break;
             }
             iterator.remove();
         }
         scanner.close();
         hwSystem.closeProtocols();
     }
     



    /**
     * Reads the configuration file and initializes the hardware system accordingly.
     * Parses information about communication protocols and device counts.
     * 
     * @param fileName Path to the configuration file
     * @return Initialized HwSystem instance with configured protocols and devices
     */
    public static HwSystem fileReadining(String fileName,File logPath)
    {
        
        HwSystem hwsystem = new HwSystem();
        try
        {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine())
            {
                String line = scanner.nextLine();
                if(line.startsWith("Port Configuration: "))
                {
                    int portID = 0;
                    String protocolsPart = line.substring(line.indexOf(":") + 1).trim();
                    Queue<String>protocolNames = new LinkedList<>(Arrays.asList(protocolsPart.split(",")));
                    //String[] protocolNames = protocolsPart.split(",");
                    ArrayList<String> protocols = new ArrayList<>();
                    String protocol = protocolNames.poll();
                    while(protocol != null)
                    {
                        protocols.add(protocol);
                        protocol = protocolNames.poll();
                    }
                    Iterator<String> iterator = protocols.iterator();
                    while(iterator.hasNext())
                    {
                        String protocolName = iterator.next();
                        if(protocolName.equals("I2C"))
                            hwsystem.setProtocol(new I2C(portID,logPath));
                        else if(protocolName.equals("OneWire"))
                            hwsystem.setProtocol(new OneWire(portID,logPath));
                        else if(protocolName.equals("SPI"))
                            hwsystem.setProtocol(new SPI(portID,logPath));
                        else if(protocolName.equals("UART"))
                            hwsystem.setProtocol(new UART(portID,logPath));
                        portID++;
                    }
                }
                else if(line.startsWith("# of sensors:"))
                {
                    int num = Integer.parseInt(line.substring(line.indexOf(":") + 1).trim());
                    num = num < 0 ? 0 : num;
                    hwsystem.setSensorsNumber(num);
                }
                else if(line.startsWith("# of displays:"))
                {
                    int num = Integer.parseInt(line.substring(line.indexOf(":") + 1).trim());
                    num = num < 0 ? 0 : num;
                    hwsystem.setDisplaysNumber(num);
                }
                else if(line.startsWith("# of wireless adapters:"))
                {
                    int num = Integer.parseInt(line.substring(line.indexOf(":") + 1).trim());
                    num = num < 0 ? 0 : num;
                    hwsystem.setWirelessIOsNumber(num);
                }
                else if(line.startsWith("# of motor drivers:"))
                {
                    int num = Integer.parseInt(line.substring(line.indexOf(":") + 1).trim());
                    num = num < 0 ? 0 : num;
                    hwsystem.setMotorDriversNumber(num);
                }
            }
            scanner.close();
        }
        catch(Exception e)
        {
            System.err.println("Problem occured during readining file : " + e.getMessage());
        }
        /*initially sets devices arrayList as null*/
        hwsystem.setDevices();
        /*initiallt assigns -1 to all devices ports*/
        hwsystem.setPortIdOfDevices();
        return hwsystem;
    }
}
