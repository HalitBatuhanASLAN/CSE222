package PlanetSystem;

//import java.util.Stack;

public class PlanetSystemManager
{
    private PlanetSystem currentPlanetSystem;
    public void createPlanetSystem(String starName,double temperature,double pressure,double humidity,double radiation)
    {
        currentPlanetSystem = new PlanetSystem(starName, temperature, pressure, humidity, radiation);
    }
    public void addPlanet(String planetName,String parentName,double temperature,double pressure,double humidity,double radiation)
    {
        if(currentPlanetSystem == null)
        {
            System.out.println("There is no any system, please firstly create a system with star");
        }
        else
        {
            Node newPlanet = new Node(planetName,"Planet", temperature, pressure, humidity, radiation);
            
        }

    }
    public void addSatellite(String satelliteName,String parentName,double temperature,double pressure,double humidity,double radiation)
    {

    }
    public void findRadiationAnomalies(double threshold)
    {

    }
    /*public Stack<String> getPathTo(String planetName)
    {
        
    }*/
    public void printMissionReport()
    {

    }
    public void printMissionReport(String NodeName)
    {
        
    }
}
