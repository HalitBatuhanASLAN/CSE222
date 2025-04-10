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
    }
    /*public void addPlanet(String planetName,String parentName,double temperature,double pressure,double humidity,double radiation)
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
                System.out.println("No parent matching");
            else
            {
                parent.addChild(newPlanet);
            }
        }

    }*/
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
                System.out.println("No parent matching");
            else if(parent.getType() == "Satellite")
            {
                System.out.println("Satellites can not have subplanet.\nSo adding planet is unseccessfull");
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
                            System.out.println("Each planet has only one subplanet.\nSo adding planet is unseccessfull");
                            return;
                        }
                        else if(child.getName().equals(planetName))
                        {
                            System.out.println("Planet has already adde.\nSo adding again is unseccessfull");
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
                System.out.println("No parent matching");
            else if(parent.getType() == "Satellite")
            {
                System.out.println("Satellites can not have subplanet.\nSo adding planet is unseccessfull");
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
                            System.out.println("Satellite has already adde.\nSo adding again is unseccessfull");
                            return;
                        }
                    }
                    /*for(Node child: parent.getChildren())
                    {
                        if(child.getName().equals(satelliteName))
                        {    
                            System.out.println("Satellite has already added.\nSo adding again is unseccessfull");
                            return;
                        }
                        else
                        {
                            parent.addChild(newSatellite);
                            System.out.println("Satellite added: " + satelliteName);
                        }
                    }*/
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
        return radiationAnomalies;
    }
    public void RadiationAnomaliesRecursive(Node current,double threshold,List<Node> listOfNodes)
    {
        if(current == null)
            return;
        else
        {
            if(current.getRadiationFromNode() >= threshold)
                listOfNodes.add(current);
            for(Node child:current.getChildren())
                RadiationAnomaliesRecursive(child,threshold,listOfNodes);
        }
    }
    public void printMissionReport()
    {
        printNodeInfo(currentPlanetSystem.getStar(),0);
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
    public void printMissionReport(String NodeName)
    {
        printNodeInfo(findParentNode(NodeName),-1);
    }

    public Stack<String> getPathTo(String planetName)
    {
        Stack<String> path = new Stack<>();
        ArrayList<Node> nodePath = new ArrayList<>();
        Node target = findParentNode(planetName);
        if(target == null)
            return path;
        Node current = currentPlanetSystem.getStar();
        path.add(current.getName());
        if(findPath(current, target, nodePath))
        {
            for(Node child:nodePath)
                path.push(child.getName());
        }
        return path;
    }

    public Boolean findPath(Node current,Node target,ArrayList<Node> path)
    {
        path.add(current);
        if(current.getName().equals(target.getName()))
            return true;
        else
        {
            for(Node child:current.getChildren())
            {
                if(findPath(child, target, path))
                    return true;
            }
        }
        path.remove(path.size()-1);
        return false;
    }

    /*public Stack<String> getPathTo(String planetName)
    {
        Stack<String> path = new Stack<>();
        Stack<Node> pathNode = new Stack<>();
        ArrayList<Node> tmp = new ArrayList<>();
        Node target = findParentNode(planetName);
        if(target == null)
            return path;
        Node current = currentPlanetSystem.getStar();
        tmp.add(current);
        while(current.getName() != target.getName())
        {
            for(Node child:current.getChildren())
                tmp.add(tmp.indexOf(current)+1,child);
            current = tmp.remove(0);
            pathNode.add(current);
        }
        System.out.println("controller");
        path.add(currentPlanetSystem.getName());
        Boolean childController = false;
        for(int i = 0;i<pathNode.size();i++)
        {
            Node node = pathNode.get(i + 1);
            for(Node nd:pathNode.get(i).getChildren())
            {
                if(nd == node)
                    childController = true;
            }
            if(childController == false)
                pathNode.remove(i+1);
            else
                path.add(pathNode.get(i+1).getName());
            childController = false;
        }
        return path;
    }*/

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
    
}
