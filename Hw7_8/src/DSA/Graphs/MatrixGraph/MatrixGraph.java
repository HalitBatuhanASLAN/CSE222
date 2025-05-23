package DSA.Graphs.MatrixGraph;

import java.util.Collection;
import DSA.Graphs.GTUGraph;

/**
 * MatrixGraph class implements the GTUGraph interface.
 * This class represents a graph using adjacency vectors.
 */
public class MatrixGraph implements GTUGraph
{
    /** Array of adjacency vectors representing the graph structure */
    private AdjacencyVect[] graph;
    /** Number of vertices in the graph */
    private int numOfVertices = 0;
    
    /**
     * Constructs a new MatrixGraph with the specified number of vertices.
     *
     * Time Complexity: O(n²) where n is the number of vertices
     * - Creating n AdjacencyVect objects, each requiring O(n) time
     * @param size The number of vertices in the graph
     */
    public MatrixGraph(int size)
    {
        reset(size);
    }

    /**
     * Default constructor for MatrixGraph.
     * Creates an empty graph with no vertices.
     * Time Complexity: O(1)
     */
    public MatrixGraph()
        {}
  
    /**
     * Sets an edge between two vertices.
     * This implementation creates an undirected edge between v1 and v2.
     *
     * Time Complexity: O(1)
     * - Each add operation on AdjacencyVect takes O(1) time
     * @param v1 The first vertex
     * @param v2 The second vertex
     * @return True if the edge was successfully set, false otherwise
     */
    @Override
    public Boolean setEdge(int v1, int v2)
    {
        if(v1 == v2)
            return false;
        if(v1 < 0 || v2 < 0)
            return false;
        return (graph[v1].add(v2) && graph[v2].add(v1));
    }

    /**
     * Checks if an edge exists between two vertices.
     *
     * Time Complexity: O(1)
     * - The contains operation on AdjacencyVect takes O(1) time
     * @param v1 The first vertex
     * @param v2 The second vertex
     * @return True if an edge exists between v1 and v2, false otherwise
     */
    @Override
    public Boolean getEdge(int v1, int v2)
    {
        if(v1 < 0 || v2 < 0)
            return false;
        return graph[v1].contains(v2);
    }

    /**
     * Returns a collection of vertices that are adjacent to the specified vertex.
     *
     * Time Complexity: O(n) where n is the number of vertices
     * - Creating a new AdjacencyVect takes O(n) time
     * - The addAll operation takes time proportional to the number of neighbors
     * @param v The vertex whose neighbors are to be returned
     * @return A collection of vertices adjacent to v
     */
    @Override
    public Collection<Integer> getNeighbors(int v)
    {
        Collection<Integer> neighbors = new AdjacencyVect(numOfVertices);
        neighbors.addAll(graph[v]); 
        return neighbors;
    }

    /**
     * Returns the number of vertices in the graph.
     *
     * Time Complexity: O(1)
     * @return The number of vertices
     */
    @Override
    public int size()
    {
        return numOfVertices;
    }

    /**
     * Resets the graph to have the specified number of vertices with no edges.
     *
     * Time Complexity: O(n²) where n is the number of vertices
     * - Creating n AdjacencyVect objects, each requiring O(n) time
     * @param size The new number of vertices for the graph
     */
    @Override
    public void reset(int size)
    {
        numOfVertices = size;
        graph = new AdjacencyVect[size];
        for(int i = 0;i<numOfVertices;i++)
            graph[i] = new AdjacencyVect(numOfVertices);
    }
}