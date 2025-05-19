package DSA.Graphs.MatrixGraph;

import java.util.ArrayList;
import java.util.Collection;

import DSA.Graphs.GTUGraph;

public class MatrixGraph implements GTUGraph
{
    private AdjacencyVect[] graph;
    private int numOfVertices;
    
    @Override
    public Boolean setEdge(int v1, int v2)
    {
        return (graph[v1].add(v2) && graph[v2].add(v1));
    }

    @Override
    public Boolean getEdge(int v1, int v2)
    {
        return graph[v1].contains(v2);
    }

    /*
     * 
     * 
     * 
     * alt 
     * tarafta
     * arraylist
     * var 
     * kontrol ed
     * edilmeli
     * 
     * 
     * 
     */
    @Override
    public Collection<Integer> getNeighbors(int v)
    {
        Collection<Integer> neighbors = new ArrayList<>();
        for(int i = 0;i<numOfVertices;i++)
        {
            if(graph[v].contains(i))
                neighbors.add(i);
        }
        return neighbors;
    }

    @Override
    public int size()
    {
        return numOfVertices;
    }

    @Override
    public void reset(int size)
    {
        numOfVertices = size;
        graph = new AdjacencyVect[size];
        for(int i = 0;i<numOfVertices;i++)
            graph[i] = new AdjacencyVect(numOfVertices);
    }
}