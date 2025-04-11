package PlanetSystem;
import java.util.ArrayList;
import java.util.List;
/**
 * Represents a celestial body in the planetary system.
 * This class manages individual nodes that can be stars, planets, or satellites.
 * Each node maintains its environmental data and hierarchical relationships.
 */
public class Node
{
    /**
     * Constructs a new celestial body with specified parameters.
     * Initializes the node with given characteristics and creates an empty list for child nodes.
     *
     * @param name The name of the celestial body
     * @param type The type of the celestial body (Star, Planet, or Satellite)
     * @param temperature The temperature measurement of the celestial body
     * @param pressure The pressure measurement of the celestial body
     * @param humidity The humidity measurement of the celestial body
     * @param radiation The radiation measurement of the celestial body
     */
    public Node(String name,String type,double temperature,double pressure,double humidity,double radiation)
    {
        this.name = name;
        this.type = type;
        this.sensorData = new SensorData(temperature,pressure,humidity,radiation);
        this.childeren = new ArrayList<>();
    }

    /** Name of the celestial body */
    private String name;
    
    /** Type of the celestial body (Star, Planet, or Satellite) */
    private String type;
    
    /** List of child nodes (planets or satellites) belonging to this celestial body */
    private List<Node> childeren;
    
    /** Environmental sensor data for this celestial body */
    private SensorData sensorData;
    
     /**
     * Adds a child node to this celestial body.
     * Used to build the hierarchical structure of the planetary system.
     *
     * @param child The node to be added as a child
     */
    public void addChild(Node child)
    {
        this.childeren.add(child);
    }
    /**
     * Retrieves the list of child nodes.
     *
     * @return List of all child nodes (planets or satellites)
     */
    public List<Node> getChildren()
    {
        return childeren;
    }
    /**
     * Retrieves the name of the celestial body.
     *
     * @return The name of the celestial body
     */
    public String getName()
    {
        return name;
    }
    /**
     * Retrieves the type of the celestial body.
     *
     * @return The type of the celestial body (Star, Planet, or Satellite)
     */
    public String getType()
    {
        return type;
    }
    /**
     * Calculates the total radiation effect from this node.
     * This includes the node's own radiation value.
     *
     * @return The total radiation value for this node
     */
    public double getRadiationFromNode()
    {
        return sensorData.getRadiation();
    }
    /**
     * Creates a formatted string containing all information about the celestial body.
     * Includes name, type, and all sensor data measurements.
     *
     * @return A string containing all information about the celestial body
     */
    public String informations()
    {
        String info = "Name is: " + this.name + " Type is: " + this.type;
        info += sensorData.information();
        return info;
    }
}