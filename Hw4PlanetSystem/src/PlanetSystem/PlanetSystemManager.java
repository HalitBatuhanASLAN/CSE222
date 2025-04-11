package PlanetSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Manages the operations and structure of a planetary system.
 * Handles creation of star systems, addition of planets and satellites,
 * and provides various analysis and reporting functions.
 */
public class PlanetSystemManager
{
    /**
     * Constructor for PlanetSystemManager as default
     */
    public PlanetSystemManager(){}
    /** The current active planetary system being managed */
    private PlanetSystem currentPlanetSystem;
    /**
     * Creates a new planetary system with a central star.
     *
     * @param starName The name of the central star
     * @param temperature The temperature of the star in Kelvin
     * @param pressure The pressure around the star in Pascals
     * @param humidity The humidity level (should be 0 for stars)
     * @param radiation The radiation level in Sieverts
     */
    public void createPlanetSystem(String starName,double temperature,double pressure,double humidity,double radiation)
    {
        currentPlanetSystem = new PlanetSystem(starName, temperature, pressure, humidity, radiation);
        System.out.println("Star system created: " + starName);
    }
    /**
     * Adds a new planet to the system under a specified parent body.
     * Validates parent type and existing children before adding.
     *
     * @param planetName The name of the new planet
     * @param parentName The name of the parent body (star or planet)
     * @param temperature The temperature of the planet in Kelvin
     * @param pressure The pressure of the planet in Pascals
     * @param humidity The humidity level of the planet
     * @param radiation The radiation level of the planet in Sieverts
     */
    public void addPlanet(String planetName,String parentName,double temperature,double pressure,double humidity,double radiation)
    {
        if(currentPlanetSystem == null)
        {
            System.out.println("There is no any system, please firstly create a system with star");
        }
        else
        {
            Node newPlanet = new Node(planetName,"Planet", temperature, pressure, humidity, radiation);
            Node parent = findParentNode(parentName);
            if(parent == null)
                System.out.println("No parent matching for adding " + planetName);
            else if(parent.getType() == "Satellite")
            {
                System.out.println("Satellites can not have subplanet.\nSo adding " + planetName +  " planet is unseccessfull");
                return;
            }
            else
            {
                if(!parent.getChildren().isEmpty())
                {
                    for(Node child: parent.getChildren())
                    {
                        if(child.getType() == "Planet")
                        {    
                            System.out.println("Each planet has only one subplanet.\nSo adding " + planetName +  "  planet is unseccessfull");
                            return;
                        }
                        else if(child.getName().equals(planetName))
                        {
                            System.out.println("Planet has already adde.\nSo adding again  " + planetName +  "  is unseccessfull");
                            return;
                        }
                        else
                        {
                            parent.addChild(newPlanet);
                            System.out.println("Planet added: " + planetName);
                        }
                    }
                }
                else
                {
                    parent.addChild(newPlanet);
                    System.out.println("Planet added: " + planetName);
                }
            }
        }
    }
    /**
     * Adds a new satellite to the system under a specified parent body.
     * Validates parent type and existing children before adding.
     *
     * @param satelliteName The name of the new satellite
     * @param parentName The name of the parent body
     * @param temperature The temperature of the satellite in Kelvin
     * @param pressure The pressure of the satellite in Pascals
     * @param humidity The humidity level of the satellite
     * @param radiation The radiation level of the satellite in Sieverts
     */
    public void addSatellite(String satelliteName,String parentName,double temperature,double pressure,double humidity,double radiation)
    {
        if(currentPlanetSystem == null)
        {
            System.out.println("There is no any system, please firstly create a system with star");
        }
        else
        {
            Node newSatellite = new Node(satelliteName,"Satellite", temperature, pressure, humidity, radiation);
            Node parent = findParentNode(parentName);
            if(parent == null)
                System.out.println("No parent matching for adding " + satelliteName);
            else if(parent.getType() == "Satellite")
            {
                System.out.println("Satellites can not have subsatellite.\nSo adding " + satelliteName +  " satellite is unseccessfull");
                return;
            }
            else
            {
                if(!parent.getChildren().isEmpty())
                {
                    for(Node child: parent.getChildren())
                    {
                        if(child.getName().equals(satelliteName))
                        {
                            System.out.println("Satellite has already adde.\nSo adding again " + satelliteName +  " is unseccessfull");
                            return;
                        }
                    }
                    parent.addChild(newSatellite);
                    System.out.println("Satellite added: " + satelliteName);
                
                }
                else
                {
                    parent.addChild(newSatellite);
                    System.out.println("Satellite added: " + satelliteName);
                }
            }
        }
    }
    /**
     * Finds celestial bodies with radiation levels above the specified threshold.
     *
     * @param threshold The minimum radiation level to be considered an anomaly
     * @return ArrayList of Nodes with radiation levels above threshold, null if none found
     */
    public ArrayList<Node> findRadiationAnomalies(double threshold)
    {
        ArrayList<Node> radiationAnomalies = new ArrayList<>();
        if(currentPlanetSystem == null)
            System.out.println("No any system");
        else
        {
            RadiationAnomaliesRecursive(currentPlanetSystem.getStar(),threshold,radiationAnomalies);
            if(radiationAnomalies.isEmpty())
                return null;
        }
        return radiationAnomalies;
    }
    /**
     * Prints a hierarchical report of the entire planetary system.
     */
    public void printMissionReport()
    {
        if(currentPlanetSystem != null)
            printNodeInfo(currentPlanetSystem.getStar(),0);
        else
            System.out.println("No any star system");
    }
    /**
     * Prints information about a specific node in the system.
     *
     * @param NodeName The name of the node to report on
     */
    public void printMissionReport(String NodeName)
    {
        Node parentNode = findParentNode(NodeName);
        if(parentNode != null)
        {
            System.out.println("Mission reports of " + NodeName);
            printNodeInfo(findParentNode(NodeName),-1);
        }
        else
        {
            System.out.println("Could not find " + NodeName + "in printing mission report");
        }
    }
    /**
     * Finds the path from the central star to a specified celestial body.
     *
     * @param planetName The name of the target celestial body
     * @return Stack containing the path from star to target, null if path not found
     */
    public Stack<String> getPathTo(String planetName)
    {
        Stack<String> path = new Stack<>();
        ArrayList<Node> nodePath = new ArrayList<>();
        Node target = findParentNode(planetName);
        if(target == null)
            return null;
        Node current = currentPlanetSystem.getStar();
        if(findPath(current, target, nodePath))
        {
            path.push(current.getName());
            for(Node child:nodePath)
            {
                if(child.getType().equals("Planet"))
                    path.push(child.getName());
            }
        }
        if(path.isEmpty())
            return null;
        return path;
    }
    /**
     * Recursively searches for a path between two nodes in the system.
     *
     * @param current The current node being examined
     * @param target The target node to find
     * @param NodePath List to store the path
     * @return true if path is found, false otherwise
     */
    private Boolean findPath(Node current,Node target,ArrayList<Node> NodePath)
    {
        NodePath.add(current);
        if(current.getName().equals(target.getName()))
            return true;
        else
        {
            for(Node child:current.getChildren())
            {
                if(findPath(child, target, NodePath))
                    return true;
            }
        }
        NodePath.remove(NodePath.size()-1);
        return false;
    }
    /**
     * Searches for a node in the system by its name using breadth-first search.
     *
     * @param parentName The name of the node to find
     * @return The found Node object, null if not found
     */
    private Node findParentNode(String parentName)
    {
        if (currentPlanetSystem == null)
            return null;
        
        Node startNode = currentPlanetSystem.getStar();
        
        if(startNode.getName().equals(parentName))
            return startNode;
        
        java.util.Queue<Node> queue = new java.util.LinkedList<>();
        queue.add(startNode);
        
        while(!queue.isEmpty())
        {
            Node current = queue.poll();
            
            if(current.getName().equals(parentName))
                return current;
            
            List<Node> children = current.getChildren();
            if (children != null)
            {
                for (Node child : children)
                    queue.add(child);
            }
        }
        return null;
    }
    /**
     * Prints hierarchical information about a node and its children.
     *
     * @param currentNode The node to print information about
     * @param part The indentation level for hierarchical display
     */
    private void printNodeInfo(Node currentNode,int part)
    {
        if(part == -1)
        {
            System.out.println(currentNode.informations());
            return;
        }
        if(currentNode == null)
            return;
        
        for(int i = 0;i<part;i++)
            System.out.print("  ");
        System.out.print("└──");
        System.out.println(currentNode.informations());
        List<Node> childrenList = currentNode.getChildren();
        for(Node child:childrenList)
        {
            if(!child.getType().equals("Satellite"))
                part++;
            printNodeInfo(child,part);   
        }
    }
    /**
    * Prints hierarchical information about a node and its children.
    *
    * @param currentNode The node to print information about
    * @param part The indentation level for hierarchical display
    */
    private void RadiationAnomaliesRecursive(Node current,double threshold,List<Node> listOfNodes)
    {
        if(current == null)
            return;
        else
        {
            if(current.getRadiationFromNode() > threshold)
                listOfNodes.add(current);
            for(Node child:current.getChildren())
                RadiationAnomaliesRecursive(child,threshold,listOfNodes);
        }
    }
}
