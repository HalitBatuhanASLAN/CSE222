package DSA.Graphs.MatrixGraph;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * AdjacencyVect class implements the Collection interface for Integer elements.
 * This class represents an adjacency vector for graph implementations using a binary vector approach.
 */
public class AdjacencyVect implements Collection<Integer>
{
    /** Binary vector representing the adjacency information */
    private Boolean[] binaryVecter;
    /** Size of the binary vector */
    private int size = 0; 
    /** Number of neighbors (true values) in the binary vector */
    private int numOfNeighbour = 0;

    /**
     * Constructs a new AdjacencyVect with the specified size.
     *
     *  Time Complexity: O(n) where n is the size of the vector
     *
     * @param size The size of the adjacency vector
     */
    public AdjacencyVect(int size)
    {
        this.size = size;
        binaryVecter = new Boolean[size];
        for(int i = 0;i<size;i++)
            binaryVecter[i] = false;
    }

    /**
     * Adds an integer to the adjacency vector.
     *
     * Time Complexity: O(1)
     * 
     * @param arg0 The integer to add
     * @return True if the integer was successfully added, false otherwise
     */
    @Override
    public boolean add(Integer arg0)
    {
        if(arg0 < 0 || arg0 >= size)
        {
            System.err.println("Index out of range");
            return false;
        }
        if(binaryVecter[arg0] == true)
            return false;
        if(binaryVecter[arg0] == false)
        {
            binaryVecter[arg0] = true;
            numOfNeighbour++;
        }
        return true;
    }

    /**
     * Adds all integers from the specified collection to this adjacency vector.
     *
     * Time Complexity: O(m) where m is the size of the input collection
     * 
     * @param arg0 The collection of integers to add
     * @return True if the adjacency vector was changed as a result of the call
     */
    @Override
    public boolean addAll(Collection<? extends Integer> arg0)
    {
        boolean changed = false;
        for(Integer a : arg0)
        {
            if(add(a))
                changed = true;
        }
        return changed;
    }

    /**
     * 
     * Time Complexity: O(n) where n is the size of the vector
     * 
     * Removes all integers from this adjacency vector.
     */
    @Override
    public void clear()
    {
        for(int i = 0;i<size;i++)
            binaryVecter[i] = false;
        numOfNeighbour = 0;
    }

    /**
     * Checks if the adjacency vector contains the specified object.
     *
     * Time Complexity: O(1)
     * 
     * @param arg0 The object to check for
     * @return True if the object is in the adjacency vector, false otherwise
     */
    @Override
    public boolean contains(Object arg0)
    {
        if(!(arg0 instanceof Integer))
        {
            System.err.println("arg0 parameter is not an integer so it does not contained");
            return false;
        }
        int index = (Integer) arg0;
        if(index < 0 || index >= size)
        {
            System.err.println("Index out of range");
            return false;
        }
        if(binaryVecter[index] == true)
            return true;
        return false;
    }

    /**
     * Checks if the adjacency vector contains all elements in the specified collection.
     *
     * Time Complexity: O(m) where m is the size of the input collection
     * 
     * @param arg0 The collection to check for
     * @return True if the adjacency vector contains all elements in the collection, false otherwise
     */
    @Override
    public boolean containsAll(Collection<?> arg0)
    {
        for(Object a: arg0)
        {
            if(!contains(a))
                return false;
        }
        return true;
    }

    /**
     * Checks if the adjacency vector is empty.
     *
     * Time Complexity: O(1)
     * @return True if the adjacency vector is empty, false otherwise
     */
    @Override
    public boolean isEmpty()
    {
        return numOfNeighbour == 0 ? true : false;
    }

    /**
     * Removes the specified object from the adjacency vector.
     *
     * Time Complexity: O(1)
     * 
     * @param arg0 The object to remove
     * @return True if the object was removed, false otherwise
     */
    @Override
    public boolean remove(Object arg0)
    {
        if(!(arg0 instanceof Integer))
        {
            System.err.println("It is not type of integer");
            return false;
        }
        int index = (Integer) arg0;
        if(index < 0 || index >= size)
        {
            System.err.println("index out of range");
            return false;
        }
        else
        {
            if(binaryVecter[index] == false)
                return false;
            binaryVecter[index] = false;
            numOfNeighbour--;
            return true;
        }
    }

    /**
     * Removes all objects in the specified collection from this adjacency vector.
     *
     * Time Complexity: O(m) where m is the size of the input collection
     * 
     * @param arg0 The collection of objects to remove
     * @return True if the adjacency vector was changed as a result of the call
     */
    @Override
    public boolean removeAll(Collection<?> arg0)
    {
        boolean res = true;
        for(Object a:arg0)
        {
            if(!remove(a))
                res = false;
        }
        return res;
    }

    /**
     * Retains only the elements in this adjacency vector that are contained in the specified collection.
     *
     * Time Complexity: O(m) where m is the size of the input collection
     * 
     * @param arg0 The collection of elements to retain
     * @return True if the adjacency vector was changed as a result of the call
     */
    @Override
    public boolean retainAll(Collection<?> arg0)
    {
        boolean changes = false;
        Boolean curret;
        for(Object a:arg0)
        {
            curret = (Boolean) a;
            if(curret == true)
            {
                if(!contains(a))
                {
                    add((Integer)a);
                    changes = true;
                }
            }
            else
            {
                if(contains(a))
                {
                    remove(a);
                    changes = true;
                }
            }
        }
        return changes;
    }

    /**
     * Returns the number of elements in this adjacency vector.
     *
     * Time Complexity: O(1)
     * @return The number of elements in this adjacency vector
     */
    @Override
    public int size()
    {
        return numOfNeighbour;
    }

    /**
     * Returns an array containing all of the elements in this adjacency vector.
     * Time Complexity: O(n) where n is the size of the vector
     * 
     * @return An array containing all of the elements in this adjacency vector
     */
    @Override
    public Object[] toArray()
    {
        Object[] result = new Object[numOfNeighbour];
        int index = 0;
        for(int i = 0; i < size; i++)
        {
            if(binaryVecter[i] == true)
                result[index++] = i;
        }
        return result;
    }

    /**
     * Returns an array containing all of the elements in this adjacency vector.
     * The runtime type of the returned array is that of the specified array.
     * Time Complexity: O(k) where k is the number of neighbors
     * 
     * @param a The array into which the elements of this adjacency vector are to be stored
     * @return An array containing all of the elements in this adjacency vector
     */
    @Override
    public <T> T[] toArray(T[] a)
    {
        if(a.length >= size())
        {
            int i = 0;
            for (Integer value : this)
            {
                @SuppressWarnings("unchecked")
                T element = (T) value;
                a[i++] = element;
            }
            if (a.length > size())
                a[size()] = null;
        }
        else
            System.err.println("a size must be bigger than current array");
        return a;
    }

    /**
     * Returns an iterator over the elements in this adjacency vector.
     *
     * Time Complexity: O(1)
     * @return An iterator over the elements in this adjacency vector
     */
    @Override
    public Iterator<Integer> iterator()
    {
        return new AdjacencyVectIterater();
    }

    /**
     * Inner class that implements the Iterator interface for AdjacencyVect.
     */
    private class AdjacencyVectIterater implements Iterator<Integer>
    {
        /** Current index in the iteration */
        private int currentIndex = -1;
        /** Next index to be returned */
        private int nextIndex = -1;

        /**
         * Constructs a new AdjacencyVectIterater.
         * Time Complexity: O(n) in worst case where n is the size of the vector
         */
        public AdjacencyVectIterater()
        {
            findNext();
        }

        /**
         * Checks if there are more elements in the iteration.
         *
         * Time Complexity: O(1)
         * @return True if there are more elements, false otherwise
         */
        @Override
        public boolean hasNext()
        {
            return nextIndex != -1;
        }

        /**
         * Returns the next element in the iteration.
         *
         * Time Complexity: O(n) in worst case where n is the size of the vector
         * @return The next element in the iteration
         * @throws NoSuchElementException if there are no more elements
         */
        @Override
        public Integer next()
        {
            if(!hasNext())
            {
                throw new NoSuchElementException("Iterater has no more element");
            }

            currentIndex = nextIndex;
            findNext();
            return currentIndex;
        }
        
        /**
         * Finds the next element in the iteration.
         * Time Complexity: O(n) in worst case where n is the size of the vector
         */
        private void findNext()
        {
            nextIndex = -1;
            for(int i = currentIndex + 1;i<size;i++)
            {
                if(binaryVecter[i] == true)
                {
                    nextIndex = i;
                    break;
                }
            }
        }
    }
}