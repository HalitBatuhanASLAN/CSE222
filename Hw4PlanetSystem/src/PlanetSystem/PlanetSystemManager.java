package PlanetSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public class PlanetSystemManager
{
    private PlanetSystem currentPlanetSystem;
    public void createPlanetSystem(String starName,double temperature,double pressure,double humidity,double radiation)
    {
        currentPlanetSystem = new PlanetSystem(starName, temperature, pressure, humidity, radiation);
        System.out.println("Star system created: " + starName);
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
    public ArrayList<Node> findRadiationAnomalies(double threshold)
    {
        ArrayList<Node> radiationAnomalies = new ArrayList<>();

        RadiationAnomaliesRecursive(currentPlanetSystem.getStar(),threshold,radiationAnomalies);
        if(radiationAnomalies.isEmpty())
            return null;
        return radiationAnomalies;
    }
    
    public void printMissionReport()
    {
        printNodeInfo(currentPlanetSystem.getStar(),0);
    }
    
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
