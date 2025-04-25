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
        queue.addLast(t);
        T currentOne = queue.getLast();
        int parent_index = (size-1)/2;
        T parent = queue.get(parent_index);
        while(currentOne.compareTo(parent) > 0)
        {
            queue.set(parent_index,currentOne);
            queue.set(size,parent);
            currentOne = queue.get(parent_index);
            parent_index = (parent_index-1)/2;
            parent = queue.get(parent_index);
        }
        size++;
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
        T lastOne = queue.removeLast();
        int parent_index = 0;
        int left_child_index = 1;
        int right_child_index = 2;
        queue.set(parent_index, lastOne);
        T parent = queue.get(parent_index);
        T child_left = queue.get(left_child_index);
        T child_right = queue.get(right_child_index);
        while(parent.compareTo(child_right) > 0 || parent.compareTo(child_left) > 0)
        {
            if(parent.compareTo(child_right) > 0 && parent.compareTo(child_left) > 0)
            {
                if(child_left.compareTo(child_right) > 0)
                {
                    queue.set(parent_index, child_right);
                    queue.set(right_child_index, parent);
                    parent_index = right_child_index;
                    right_child_index = 2*parent_index + 2;
                    left_child_index = 2*parent_index + 1;
                }
                else if(child_left.compareTo(child_right) < 0)
                {
                    queue.set(parent_index, child_left);
                    queue.set(left_child_index, parent);
                    parent_index = left_child_index;
                    right_child_index = 2*parent_index + 2;
                    left_child_index = 2*parent_index + 1;
                }
            }
            else if(parent.compareTo(child_right) > 0)
            {
                queue.set(parent_index, child_right);
                queue.set(right_child_index, parent);
                parent_index = right_child_index;
                right_child_index = 2*parent_index + 2;
                left_child_index = 2*parent_index + 1;
            }
            else if(parent.compareTo(child_left) > 0)
            {
                queue.set(parent_index, child_left);
                queue.set(left_child_index, parent);
                parent_index = left_child_index;
                right_child_index = 2*parent_index + 2;
                left_child_index = 2*parent_index + 1;
            }
            child_left = queue.get(left_child_index);
            child_right = queue.get(right_child_index);
        }
        size--;
        return returnedOne;
    }
    public Boolean isEmpty()
    {
        return queue.isEmpty() && size == 0;
    }
}
