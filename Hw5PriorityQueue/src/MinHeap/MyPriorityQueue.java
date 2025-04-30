package MinHeap;

/**
 * Interface for a priority queue implementation
 * @param <T> Type that extends Comparable
 */
public interface MyPriorityQueue<T extends Comparable<T>>
{
    /**
     * Adds an element to the priority queue
     * Time Complexity: Depends on implementation, typically O(log n)
     * @param t Element to be added
     */
    void add(T t);
    
    /**
     * Removes and returns the highest priority element from the queue
     * Time Complexity: Depends on implementation, typically O(log n)
     * @return The highest priority element, or null if the queue is empty
     */
    T poll();
    
    /**
     * Checks if the priority queue is empty
     * Time Complexity: Depends on implementation, typically O(1)
     * @return true if the queue is empty, false otherwise
     */
    Boolean isEmpty();
}