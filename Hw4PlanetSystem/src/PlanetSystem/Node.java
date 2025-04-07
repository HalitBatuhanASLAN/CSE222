package PlanetSystem;
import java.util.List;
public class Node
{
    public Node(String name,String type,double temperature,double pressure,double humidity,double radiation)
    {
        this.name = name;
        this.type = type;
        this.sensorData = new SensorData(temperature,pressure,humidity,radiation);
    }
    private String name;
    private String type;
    private SensorData sensorData;
    private List<Node> childeren;

    public void addChild(Node child)
    {
        this.childeren.add(child);
    }
    public List<Node> getChildren()
    {
        return childeren;
    }
    public String informations()
    {
        String info = "Name is: " + this.name + " Type is: " + this.type;
        info += sensorData.information();
        return info;
    }
}
