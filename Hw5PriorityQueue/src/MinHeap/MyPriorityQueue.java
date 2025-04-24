package MinHeap;

public interface MyPriorityQueue<T extends Comparable<T>>
{
    void add(T t);
    T poll();
    Boolean isEmpty();
}
