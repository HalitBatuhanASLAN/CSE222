package Tests;

import DSA.Graphs.GTUGraph;
import DSA.Graphs.MatrixGraph.MatrixGraph;
import DSA.Sorting.GTUSorter;
import DSA.Sorting.MyInsertSort;
import DSA.Graphs.GCA.GreedyGCA;
import DSA.Graphs.GCA.GCASolution;

/**
 * Test class - Contains simple tests for graph algorithms and data structures.
 */
public class GraphTest {
    
    public static void main(String[] args) {
        System.out.println("Graph Algorithms Test Program");
        
        // Test for matrix-based graph creation
        testMatrixGraph();
        
        // Test for Greedy Graph Coloring Algorithm
        testGreedyGCA();
    }
    
    /**
     * Tests the MatrixGraph class
     */
    private static void testMatrixGraph() {
        System.out.println("\n=== MatrixGraph Test ===");
        
        try {
            // Create a graph with 5 vertices
            GTUGraph graph = new MatrixGraph();
            graph.reset(5);
            
            // Add edges
            graph.setEdge(0, 1);
            graph.setEdge(0, 2);
            graph.setEdge(1, 2);
            graph.setEdge(1, 3);
            graph.setEdge(2, 3);
            graph.setEdge(2, 4);
            graph.setEdge(3, 4);
            
            // Print graph information
            System.out.println("Graph created: " + graph.size() + " vertices, " + graph.size() + " edges");
            
            // Check neighbors
            System.out.println("Neighbors of vertex 0: " + graph.getNeighbors(0));
            System.out.println("Neighbors of vertex 2: " + graph.getNeighbors(2));
            
            // Check edges
            System.out.println("Is there an edge between 0-1? " + graph.getEdge(0, 1));
            System.out.println("Is there an edge between 0-3? " + graph.getEdge(0, 3));
            
        } catch (Exception e) {
            System.err.println("Error during MatrixGraph test: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Tests the GreedyGCA class
     */
    private static void testGreedyGCA() {
        System.out.println("\n=== Greedy Graph Coloring Algorithm Test ===");
        
        try {
            // Create a graph for testing
            GTUGraph graph = new MatrixGraph();
            graph.reset(6);
            
            // Create a sample graph structure
            graph.setEdge(0, 1);
            graph.setEdge(0, 2);
            graph.setEdge(1, 2);
            graph.setEdge(1, 3);
            graph.setEdge(2, 3);
            graph.setEdge(2, 4);
            graph.setEdge(3, 4);
            graph.setEdge(3, 5);
            graph.setEdge(4, 5);
            
            GTUSorter sorter = new MyInsertSort();
            // Color the graph using the greedy algorithm
            GreedyGCA coloringAlgorithm = new GreedyGCA();
            GCASolution solution = coloringAlgorithm.solve(graph,sorter);
            
            // Print results
            System.out.println("Coloring completed.");
            System.out.println("Number of colors used: " + solution.colorNum());
            
        } catch (Exception e) {
            System.err.println("Error during GreedyGCA test: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
