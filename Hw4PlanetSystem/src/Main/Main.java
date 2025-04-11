package Main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

import PlanetSystem.Node;
import PlanetSystem.PlanetSystemManager;

/**
 * Main class for the Planetary System Command Interface.
 * Provides a command-line interface for managing planetary systems.
 */
public class Main
{
    /*
     * constructor for default Main class
     */
    public Main(){}

    /**
     * Main entry point of the application.
     * Initializes the command interface.
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String args[])
    {
        readingCommand();

    }
    /**
     * Handles the command-line interface for the planetary system.
     * Processes user commands and manages the planetary system through PlanetSystemManager.
     * 
     * Supported commands:
     * - create PlanetSystem [starName] [temperature] [pressure] [humidity] [radiation]
     * - addPlanet [planetName] [parentName] [temperature] [pressure] [humidity] [radiation]
     * - addSatellite [satelliteName] [parentName] [temperature] [pressure] [humidity] [radiation]
     * - findRadiationAnomalies [threshold]
     * - getPathTo [nodeName]
     * - printMissionReport [nodeName] (optional)
     * - EXIT
     */
    public static void readingCommand()
    {
        PlanetSystemManager planetSystemManager = new PlanetSystemManager();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Planetary System Command Interface");
        System.out.println("Enter commands (type 'EXIT' to quit):");
        
        String commandString;
        ArrayList<String> commandParts;
        Stack<String> path;
        ArrayList<Node> anomalies;

        String starName;
        String planetName;
        String satelliteName;
        String parentName;
        String nodeName;
        double temperature;
        double pressure;
        double humidity;
        double radiation;
        double threshold;
        
        do {
            commandString = scanner.nextLine();
            commandParts = new ArrayList<>(Arrays.asList(commandString.split(" ")));
            
            if (commandParts.isEmpty())
            {
                continue;
            }
            
            switch(commandParts.get(0))
            {
                case "create":
                    if(commandParts.get(1).equals("planetSystem"))
                    {
                        starName = commandParts.get(2);
                        temperature = Double.parseDouble(commandParts.get(3));
                        if(temperature<0)
                        {
                            System.out.println("Error: Star temperature can not be lower than 0.(adding " + starName + ")");
                            break;
                        }
                        pressure = Double.parseDouble(commandParts.get(4));
                        if(pressure<0)
                        {
                            System.out.println("Error: Star pressure can not be lower than 0.(adding " + starName + ")");
                            break;
                        }
                        humidity = Double.parseDouble(commandParts.get(5));
                        if(humidity != 0)
                        {
                            System.out.println("Error: Star humidity must be beween 0.(adding " + starName + ")");
                            break;
                        }
                        radiation = Double.parseDouble(commandParts.get(6));
                        planetSystemManager.createPlanetSystem(starName, temperature, pressure, humidity, radiation);
                        System.out.println();
                    }
                    else
                        System.out.println("Command is missing");
                    break;
                case "addPlanet":
                    planetName = commandParts.get(1);
                    parentName = commandParts.get(2);
                    temperature = Double.parseDouble(commandParts.get(3));
                    if(temperature<0)
                    {
                        System.out.println("Error: Planet temperature can not be lower than 0.(adding " + planetName + ")");
                        break;
                    }
                    pressure = Double.parseDouble(commandParts.get(4));
                    if(pressure<0)
                    {
                        System.out.println("Error: Planet pressure can not be lower than 0.(adding " + planetName + ")");
                        break;
                    }
                    humidity = Double.parseDouble(commandParts.get(5));
                    if(humidity < 0 || humidity > 100)
                    {
                        System.out.println("Error: Planet humidity must be beween 0-100.(adding " + planetName + ")");
                        break;
                    }
                    radiation = Double.parseDouble(commandParts.get(6));
                    planetSystemManager.addPlanet(planetName, parentName, temperature, pressure, humidity, radiation);
                    System.out.println();
                    break;
                    
                case "addSatellite":
                    satelliteName = commandParts.get(1);
                    parentName = commandParts.get(2);
                    temperature = Double.parseDouble(commandParts.get(3));
                    if(temperature<0)
                    {
                        System.out.println("Error: Satellite temperature can not be lower than 0.(adding " + satelliteName + ")");
                        break;
                    }
                    pressure = Double.parseDouble(commandParts.get(4));
                    if(pressure<0)
                    {
                        System.out.println("Error: Satellite pressure can not be lower than 0.(adding " + satelliteName + ")");
                        break;
                    }
                    humidity = Double.parseDouble(commandParts.get(5));
                    if(humidity < 0 || humidity > 100)
                    {
                        System.out.println("Error: Sateliite humidity must be beween 0-100.(adding " + satelliteName + ")");
                        break;
                    }
                    radiation = Double.parseDouble(commandParts.get(6));
                    planetSystemManager.addSatellite(satelliteName, parentName, temperature, pressure, humidity, radiation);
                    System.out.println();
                    break;
                    
                case "findRadiationAnomalies":
                    threshold = Double.parseDouble(commandParts.get(1));
                    anomalies = new ArrayList<>();
                    anomalies = planetSystemManager.findRadiationAnomalies(threshold);
                    if(anomalies == null)
                        System.out.println("No anomalies found of " + threshold);
                    else
                    {
                        System.out.println("Anomalies of " + threshold + ";");
                        for(Node currentNode:anomalies)
                            System.out.println(currentNode.getName() + " with radiation " + currentNode.getRadiationFromNode());
                    }
                    System.out.println();
                    break;
                case "getPathTo":
                    nodeName = commandParts.get(1);
                    System.out.println("Path to " + nodeName);
                    path = planetSystemManager.getPathTo(nodeName);
                    if(path == null)
                        System.out.println("Could not find " + nodeName + " in printing path");
                    else
                    {
                        for(String planets:path)
                        {
                            System.out.println(planets);
                        }
                    }
                    System.out.println();
                    break;
                
                case "printMissionReport":
                    if(commandParts.size() == 2)
                    {
                        nodeName = commandParts.get(1);
                        planetSystemManager.printMissionReport(nodeName);
                    }
                    else
                    {
                        System.out.println("Printing mission report;");
                        planetSystemManager.printMissionReport();
                    }
                    System.out.println();
                    break;
                    
                case "EXIT":
                    System.out.println("Exiting planetary system...");
                    break;
                case "":
                    break;
                default:
                    System.out.println("Unknown command: " + commandParts.get(0));
                    System.out.println();
                    break;
            }
            
        }while(!commandParts.get(0).equals("EXIT"));
        scanner.close();
    }
}