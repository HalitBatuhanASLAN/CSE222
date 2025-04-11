package PlanetSystem;
/**
 * Represents a planetary system containing a central star and its celestial bodies.
 * This class serves as the foundation for creating and managing an entire planetary system.
 */
public class PlanetSystem
{
    /**
     * Constructs a new planetary system with a central star.
     * Initializes the system with the given parameters and creates the central star.
     *
     * @param PlanetSystemName The name of the planetary system
     * @param temperature The temperature measurement of the central star
     * @param pressure The pressure measurement around the central star
     * @param humidity The humidity measurement (should be 0 for stars)
     * @param radiation The radiation measurement of the central star
     */
    public PlanetSystem(String PlanetSystemName,double temperature,double pressure,double humidity,double radiation)
    {
        /*if(humidity != 0)
        {
            System.out.println("Humidity must be zeor for stars.Your value is not excepted.So took as 0");
            humidity = 0;
        }*/
        this.PlanetSystemName = PlanetSystemName;
        this.star = new Node(PlanetSystemName,"Star",temperature,pressure,humidity,radiation);
    }
    /** Name of the planetary system */
    private String PlanetSystemName;
    /** The central star node of the planetary system */
    private Node star;
    /**
     * Retrieves the name of the planetary system.
     *
     * @return The name of the planetary system
     */
    public String getName()
    {
        return PlanetSystemName;
    }
    /**
     * Retrieves the central star node of the planetary system.
     *
     * @return The Node object representing the central star
     */
    public Node getStar()
    {
        return star;
    }
}