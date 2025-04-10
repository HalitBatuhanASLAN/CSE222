package Main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

import PlanetSystem.Node;
import PlanetSystem.PlanetSystemManager;

public class Main
{
    public static void main(String args[])
    {
        readingCommand();

    }
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
                case "createPlanetSystem":
                    starName = commandParts.get(1);
                    temperature = Double.parseDouble(commandParts.get(2));
                    pressure = Double.parseDouble(commandParts.get(3));
                    humidity = Double.parseDouble(commandParts.get(4));
                    radiation = Double.parseDouble(commandParts.get(5));
                    planetSystemManager.createPlanetSystem(starName, temperature, pressure, humidity, radiation);
                    System.out.println("Star system created: " + starName);
                    break;
                case "addPlanet":
                    planetName = commandParts.get(1);
                    parentName = commandParts.get(2);
                    temperature = Double.parseDouble(commandParts.get(3));
                    pressure = Double.parseDouble(commandParts.get(4));
                    humidity = Double.parseDouble(commandParts.get(5));
                    radiation = Double.parseDouble(commandParts.get(6));
                    planetSystemManager.addPlanet(planetName, parentName, temperature, pressure, humidity, radiation);
                    break;
                    
                case "addSatellite":
                    satelliteName = commandParts.get(1);
                    parentName = commandParts.get(2);
                    temperature = Double.parseDouble(commandParts.get(3));
                    pressure = Double.parseDouble(commandParts.get(4));
                    humidity = Double.parseDouble(commandParts.get(5));
                    radiation = Double.parseDouble(commandParts.get(6));
                    planetSystemManager.addSatellite(satelliteName, parentName, temperature, pressure, humidity, radiation);
                    break;
                    
                case "findRadiationAnomalies":
                    threshold = Double.parseDouble(commandParts.get(1));
                    anomalies = new ArrayList<>();
                    anomalies = planetSystemManager.findRadiationAnomalies(threshold);
                    if(anomalies == null)
                        System.out.println("No anomalies found");
                    else
                    {
                        System.out.println("Anomalies;");
                        for(Node currentNode:anomalies)
                            System.out.println(currentNode.informations());
                    }
                    break;
                case "getPathTo":
                    nodeName = commandParts.get(1);
                    System.out.println("Path to " + nodeName);
                    path = planetSystemManager.getPathTo(nodeName);
                    for(String planets:path)
                    {
                        System.out.println(planets);
                    }
                    break;
                
                case "printMissionReport":
                    if(commandParts.size() == 2)
                    {
                        nodeName = commandParts.get(1);
                        planetSystemManager.printMissionReport(nodeName);
                    }
                    else
                        planetSystemManager.printMissionReport();
                    break;
                    
                case "EXIT":
                    System.out.println("Exiting planetary system...");
                    break;
                default:
                    System.out.println("Unknown command: " + commandParts.get(0));
                    break;
            }
            
        }while(!commandParts.get(0).equals("EXIT"));
        scanner.close();
    }
}