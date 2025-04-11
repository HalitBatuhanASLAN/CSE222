package PlanetSystem;

public class PlanetSystem
{
    public PlanetSystem(String PlanetSystemName,double temperature,double pressure,double humidity,double radiation)
    {
        if(humidity != 0)
        {
            System.out.println("Humidity must be zeor for stars.Your value is not excepted.So took as 0");
            humidity = 0;
        }
        this.PlanetSystemName = PlanetSystemName;
        this.star = new Node(PlanetSystemName,"Star",temperature,pressure,humidity,radiation);
    }
    private String PlanetSystemName;
    private Node star;
    public String getName()
    {
        return PlanetSystemName;
    }
    public Node getStar()
    {
        return star;
    }
}