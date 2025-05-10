package Structure;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A custom implementation of a HashSet using GTUHashMap as the underlying data structure.
 * This class provides basic set operations such as add, remove, and contains.
 * 
 * @param <E> The type of elements in this set
 */
public class GTUHashSet<E> implements Iterable<E>
{
    /** A dummy object used as a value for all entries in the backing map */
    private static final Object WORD = new Object();
    
    /** The map used to store the set elements */
    private GTUHashMap<E, Object> map;
    
    /**
     * Constructs a new, empty set.
     */
    public GTUHashSet()
        { map = new GTUHashMap<>(); }
    
    /**
     * Adds the specified element to this set if it is not already present.
     * 
     * @param element The element to be added to the set
     */
    public void add(E element)
        { map.put(element, WORD); }
    
    /**
     * Removes the specified element from this set if it is present.
     * 
     * @param element The element to be removed from the set
     */
    public void remove(E element)
        { map.remove(element); }
    
    
    /**
     * Returns true if this set contains the specified element.
     * 
     * @param element The element whose presence in this set is to be tested
     * @return true if this set contains the specified element, false otherwise
     */
    public boolean contains(E element)
        { return map.containsKey(element); }
    
    /**
     * Returns the number of elements in this set.
     * 
     * @return The number of elements in this set
     */
    public int size()
        { return map.size(); }

    /*
     * Returns the number of collisions that occurred in the underlying hash map.
     *       
     * @return The number of collisions
    */
     public int getCollisions()
        { return map.getCollisions();}

    /**
     * Returns a string representation of this set.
     * The string representation consists of a list of the set's elements
     * enclosed in square brackets ("[]").
     * 
     * @return A string representation of this set
     */
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder("[");
        for (E key : map.keySet())
            sb.append(key).append(", ");
        if (sb.length() > 1) sb.setLength(sb.length() - 2);
        sb.append("]");
        return sb.toString();
    }

    /**
     * Returns an iterator over the elements in this set.
     * 
     * @return An iterator over the elements in this set
     */
    @Override
    public Iterator<E> iterator()
    {
        return new GTUHashSetIterator();
    }
    
    /**
     * An iterator over the elements in the set.
     */
    private class GTUHashSetIterator implements Iterator<E>
    {
        /** List of keys from the backing map */
        private GTUArrayList<E> keys;
    
        /** Current position in the iteration */
        private int currentIndex;

        /**
         * Creates a new iterator over the set elements.
         */
        public GTUHashSetIterator()
        {
            keys = map.keySet();
            currentIndex = 0;
        }

        /**
         * Checks if there are more elements in the iteration.
         * 
         * @return true if there are more elements in the iteration, false otherwise
         */
        @Override
        public boolean hasNext()
        {
            return currentIndex < keys.size();
        }

        /**
         * Returns the next element in the iteration.
         * 
         * @return The next element in the iteration
         * @throws NoSuchElementException if there are no more elements in the iteration
         */
        @Override
        public E next()
        {
            if (!hasNext())
            {
                throw new NoSuchElementException();
            }
            return keys.get(currentIndex++);
        }
    }

}