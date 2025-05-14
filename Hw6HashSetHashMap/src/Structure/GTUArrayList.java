package Structure;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * A custom implementation of a resizable array list.
 * This class provides basic list operations such as add and get.
 * The list automatically grows when elements are added beyond its capacity.
 * 
 * @param <T> The type of elements in this list
 */
public class GTUArrayList<T> implements Iterable<T>
{
    /** The default initial capacity of the list */
    private static final int DEFAULT_CAPACITY = 10;
    
    /** Array to store the elements */
    private T[] elements;
    
    /** The number of elements in the list */
    private int size = 0;

    /**
     * Constructs an empty list with the default initial capacity.
     */
    @SuppressWarnings("unchecked")
    public GTUArrayList(){elements = (T[]) new Object[DEFAULT_CAPACITY];}

    /**
     * Adds the specified element to the end of this list.
     * Increases the capacity of the list if necessary.
     * 
     * @param element The element to be added to the list
     */
    public void add(T element)
    {
        ensureCapacity();
        elements[size++] = element;
    }

    /**
     * Returns the element at the specified position in this list.
     * 
     * @param index The index of the element to return
     * @return The element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    @SuppressWarnings("unchecked")
    public T get(int index) {
        return elements[index];
    }

    /**
     * Returns the number of elements in this list.
     * 
     * @return The number of elements in this list
     */
    public int size(){return size;}

    /**
     * Returns true if this list contains no elements.
     * 
     * @return true if this list contains no elements, false otherwise
     */
    public boolean isEmpty() {return size == 0;}

    /**
     * Ensures that the list has enough capacity to add a new element.
     * If the current capacity is full, it doubles the capacity.
     */
    private void ensureCapacity()
    {
        if (size == elements.length)
        {
            int newCapacity = elements.length * 2;
            elements = Arrays.copyOf(elements, newCapacity);
        }
    }

    /**
     * Returns a string representation of this list.
     * The string representation consists of a list of the elements
     * enclosed in square brackets ("[]").
     * 
     * @return A string representation of this list
     */
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++)
        {
            sb.append(elements[i]);
            if (i < size - 1)
                sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * Returns an iterator over the elements in this list.
     * 
     * @return An iterator over the elements in this list
     */
     @Override
    public Iterator<T> iterator()
        {return new GTUArrayListIterator();}

    /**
     * An iterator over the elements in the list.
     */
    private class GTUArrayListIterator implements Iterator<T>
    {
        /** Current position in the iteration */
        private int currentIndex = 0;

        /**
         * Checks if there are more elements in the iteration.
         * 
         * @return true if there are more elements in the iteration, false otherwise
         */
        @Override
        public boolean hasNext()
            {return currentIndex < size;}

        /**
         * Returns the next element in the iteration.
         * 
         * @return The next element in the iteration
         * @throws NoSuchElementException if there are no more elements in the iteration
         */
        @SuppressWarnings("unchecked")
        @Override
        public T next()
        {
            if (!hasNext())
                throw new NoSuchElementException();
            return elements[currentIndex++];
        }
    }
}
