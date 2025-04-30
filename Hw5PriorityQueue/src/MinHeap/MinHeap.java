package MinHeap;
import java.util.ArrayList;

/**
 * A generic min heap implementation that functions as a priority queue
 * @param <T> Type that extends Comparable
 */
public class MinHeap<T extends Comparable<T>> implements MyPriorityQueue<T>
{
    private ArrayList<T> queue = new ArrayList<>();
    
    /**
     * Adds an element to the heap and maintains the heap property
     * Time Complexity: O(log n) where n is the number of elements in the heap
     * @param t Element to be added
     */
    public void add(T t)
    {
        queue.add(t);
        int child_index = queue.size() - 1; 
        int parent_index = (child_index-1)/2;
        while(child_index > 0 && queue.get(child_index).compareTo(queue.get(parent_index)) < 0)
        {
            swap(child_index, parent_index);
            child_index = parent_index;
            parent_index = (child_index -1) / 2 ;
        }
    }
    
    /**
     * Removes and returns the minimum element from the heap
     * Time Complexity: O(log n) where n is the number of elements in the heap
     * @return The minimum element, or null if the heap is empty
     */
    public T poll()
    {
        if(queue.size() == 0)
        {
            System.err.println("We can not pool any element because it is empty");
            return null;
        }
        T returnedOne = queue.get(0);
        queue.set(0,queue.get(queue.size()-1));
        queue.remove(queue.size() -1);
        int parent_index = 0;
        while(true)
        {
            int left_child_index = 2*parent_index + 1;
            int right_child_index = 2*parent_index + 2;
            
            int smallerChild = parent_index;
            
            if(left_child_index < queue.size() && queue.get(left_child_index).compareTo(queue.get(smallerChild)) < 0)
                smallerChild = left_child_index;
            if(right_child_index < queue.size() && queue.get(right_child_index).compareTo(queue.get(smallerChild)) < 0)
                smallerChild = right_child_index;
            if(smallerChild == parent_index)
                break;
            swap(parent_index,smallerChild);    
            parent_index = smallerChild;
        }
        return returnedOne;
    }
    
    /**
     * Checks if the heap is empty
     * Time Complexity: O(1)
     * @return true if the heap is empty, false otherwise
     */
    public Boolean isEmpty()
    {
        return queue.size() == 0;
    }

    /**
     * Swaps two elements in the heap
     * Time Complexity: O(1)
     * @param index1 Index of the first element
     * @param index2 Index of the second element
     */
    private void swap(int index1, int index2)
    {
        T tmp = queue.get(index1);
        queue.set(index1, queue.get(index2));
        queue.set(index2, tmp);
    }
}