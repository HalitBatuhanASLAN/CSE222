package Main;

import HwSystem.*;
import HwSystem.Devices.*;
import HwSystem.Devices.Displays.*;
import HwSystem.Devices.MotorDrivers.*;
import HwSystem.Devices.Sensors.*;
import HwSystem.Devices.WirelessIOs.*;
import HwSystem.Protocols.*;
import java.util.Scanner;

import java.io.File;

public class Main
{
    public static void main(String args[])
    {
        String fileName = "/home/halitbatuhanaslan/222Data/Hw1Ports/src/Main/configuration.txt";
        
        HwSystem hwSystem = fileReadining(fileName);
        System.out.println(hwSystem.getWirelessIOsNumber());
        commands(hwSystem);
    }

    public static void commands(HwSystem hwSystem)
    {
        Boolean flag = true;
        Scanner scanner = new Scanner(System.in);
        while(flag)
        {
            System.out.print("Command: ");
            String commandString = scanner.nextLine();
            String[] commandParts = commandString.split(" ");
            int portId;
            int devId;
            String devType;
            switch(commandParts[0])
            {
                case "turnOn":
                    portId = Integer.parseInt(commandParts[1]);
                    hwSystem.turnOn(portId);
                    break;
                case "turnOff":
                    portId = Integer.parseInt(commandParts[1]);
                    hwSystem.turnOff(portId);
                    break;
                case "addDev":
                    String devNameString = commandParts[1];
                    portId = Integer.parseInt(commandParts[2]);
                    devId = Integer.parseInt(commandParts[3]);
                    hwSystem.addDev(devNameString, portId, devId);
                    break;
                case "rmDev":
                    portId = Integer.parseInt(commandParts[1]);
                    hwSystem.rmDev(portId);
                    break;
                case "list":
                    if(commandParts[1].equals("ports"))
                        hwSystem.listPorts();
                    else
                    {
                        devType = commandParts[1];
                        hwSystem.listDevType(devType);
                    }
                    break;
                case "readSensor":
                    devId = Integer.parseInt(commandParts[1]);
                    hwSystem.readSensor(devId);
                    break;
                case "printDisplay":
                    devId = Integer.parseInt(commandParts[1]);
                    hwSystem.printDisplay(devId);
                    break;
                case "readWireless":
                    devId = Integer.parseInt(commandParts[1]);
                    hwSystem.readWireless(devId);
                    break;
                case "writeWireless":
                    devId = Integer.parseInt(commandParts[1]);
                    hwSystem.writeWireless(devId);
                    break;
                case "setMotorSpeed":
                    devId = Integer.parseInt(commandParts[1]);
                    hwSystem.setMotorSpeed(devId,1907);
                    break;
                case "exit":
                    flag = false;
                    break;
                default:
                    break;
            }
        }
        scanner.close();
        System.out.println("Thanks for using our system :)");
    }

    public static HwSystem fileReadining(String fileName)
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
                    String protocolsPart = line.substring(line.indexOf(":") + 1).trim();
                    String[] protocolNames = protocolsPart.split(",");
                    for (String protocol : protocolNames)
                    {    
                        if(protocol.equals("I2C"))
                            hwsystem.setProtocol(new I2C());
                        else if(protocol.equals("OneWire"))
                            hwsystem.setProtocol(new OneWire());
                        else if(protocol.equals("SPI"))
                            hwsystem.setProtocol(new SPI());
                        else if(protocol.equals("UART"))
                            hwsystem.setProtocol(new UART());
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
                else if(line.startsWith("# of motor drivers:"))
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
            System.out.println("Problem occured during readining file : " + e.getMessage());
        }
        hwsystem.setDevices();
        return hwsystem;
    }
}
