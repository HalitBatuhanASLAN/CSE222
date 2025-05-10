package Structure;
import java.util.Iterator;
import java.util.NoSuchElementException;
public class GTUHashSet<E> implements Iterable<E>
{
    private static final Object WORD = new Object();
    private GTUHashMap<E, Object> map;
    public GTUHashSet()
        { map = new GTUHashMap<>(); }
    public void add(E element)
        { map.put(element, WORD); }
    public void remove(E element)
        { map.remove(element); }
    public boolean contains(E element)
        { return map.containsKey(element); }
    public int size()
        { return map.size(); }



/*    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder("[");
        for (E key : map.keySet())
            sb.append(key).append(", ");
        if (sb.length() > 1) sb.setLength(sb.length() - 2);
        sb.append("]");
        return sb.toString();
    }
*/
    @Override
    public Iterator<E> iterator()
    {
        return new GTUHashSetIterator();
    }
    
    private class GTUHashSetIterator implements Iterator<E>
    {
        private GTUArrayList<E> keys;
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
         */
        @Override
        public boolean hasNext()
        {
            return currentIndex < keys.size();
        }

        /**
         * Returns the next element in the iteration.
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


    /*
    public Iterator<E> iterator()
    {
        return map.keySet().iterator();
    }*/



}