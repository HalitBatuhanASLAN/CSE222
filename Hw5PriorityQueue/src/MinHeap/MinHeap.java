package MinHeap;
import java.util.ArrayList;


public class MinHeap<T extends Comparable<T>> implements MyPriorityQueue<T>
{
    private ArrayList<T> queue = new ArrayList<>();
    private int size;
    public void add(T t)
    {
        /*
         * 
         * 
         * 
         * private metod will be better
         */
        queue.add(t);
        size++;
        int child_index = size - 1; 
        int parent_index = (child_index-1)/2;
        while(parent_index > 0 && queue.get(parent_index).compareTo(queue.get(child_index)) > 0)
        {
            T tmp = queue.get(parent_index);
            queue.set(parent_index,queue.get(child_index));
            queue.set(child_index,tmp);
            child_index = parent_index;
            parent_index = (child_index -1) /2 ;
        }
    }
    
    public T poll()
    {
        /*
         * 
         * 
         * 
         * private metod will be better
         */
        if(size == 0)
        {
            System.err.println("We can not pool any element because it is empty");
            return null;
        }
        T returnedOne = queue.getFirst();
        if(queue.size() == 1)
        {
            queue.remove(0);
            size--;
            return returnedOne;
        }
        queue.set(0,queue.get(size-1));
        queue.remove(size -1);
        size--;
        int parent_index = 0;
        while(true)
        {
            int left_child_index = 2*parent_index + 1;
            if(left_child_index >= size)
                break;
            int right_child_index = 2*parent_index + 2;
            int smallerChild = parent_index;
            
            if(left_child_index < size && queue.get(left_child_index).compareTo(queue.get(smallerChild)) < 0)
                smallerChild = left_child_index;
            if(right_child_index < size && queue.get(right_child_index).compareTo(queue.get(smallerChild)) < 0)
                smallerChild = right_child_index;
            if(smallerChild == parent_index)
                break;
            T tmp = queue.get(parent_index);
            queue.set(parent_index,queue.get(smallerChild));
            queue.set(smallerChild,tmp);
            parent_index = smallerChild;
        }
        return returnedOne;
    }
    public Boolean isEmpty()
    {
        return queue.isEmpty() && size == 0;
    }
}
