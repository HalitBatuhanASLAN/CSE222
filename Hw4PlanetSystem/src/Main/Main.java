package Main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import PlanetSystem.PlanetSystem;
import PlanetSystem.PlanetSystemManager;

public class Main
{
    public static void main()
    {
        readingCommand();

    }
    public static void readingCommand()
    {
        PlanetSystemManager planetSystemManager = null;
        PlanetSystem planetSystem;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Planetary System Command Interface");
        System.out.println("Enter commands (type 'exit' to quit):");
        
        String commandString;
        ArrayList<String> commandParts;
        
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
            
            if (commandParts.isEmpty()) {
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
                    //planetSystem = new PlanetSystem(starName,temperature,pressure,humidity,radiation);
                    System.out.println("Star system created: " + starName);
                    break;
                case "addPlanet":
                    planetName = commandParts.get(1);
                    parentName = commandParts.get(2);
                    temperature = Double.parseDouble(commandParts.get(3));
                    pressure = Double.parseDouble(commandParts.get(4));
                    humidity = Double.parseDouble(commandParts.get(5));
                    radiation = Double.parseDouble(commandParts.get(6));
                    System.out.println("Planet added: " + planetName);
                    break;
                    
                case "addSatellite":
                    satelliteName = commandParts.get(1);
                    parentName = commandParts.get(2);
                    temperature = Double.parseDouble(commandParts.get(3));
                    pressure = Double.parseDouble(commandParts.get(4));
                    humidity = Double.parseDouble(commandParts.get(5));
                    radiation = Double.parseDouble(commandParts.get(6));
                    System.out.println("Moon added: " + satelliteName);
                    break;
                    
                case "findRadiationAnomalies":
                    threshold = Double.parseDouble(commandParts.get(1));
                    break;
                
                case "getPathTo":
                    nodeName = commandParts.get(1);
                    break;
                
                case "printMissionReport":
                    nodeName = commandParts.get(1);
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