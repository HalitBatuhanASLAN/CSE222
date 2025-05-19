package DSA.Graphs.MatrixGraph;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class AdjacencyVect implements Collection<Integer>
{
    private Boolean[] binaryVecter;
    private int size = 0;
    private int numOfNeighbour = 0;

    public AdjacencyVect(int size)
    {
        this.size = size;
        binaryVecter = new Boolean[size];
        for(int i = 0;i<size;i++)
            binaryVecter[i] = false;
    }

    @Override
    public boolean add(Integer arg0)
    {
        if(arg0 < 0 || arg0 >= size)
        {
            System.err.println("Index out of range");
            return false;
        }
        if(binaryVecter[arg0] == false)
        {
            binaryVecter[arg0] = true;
            numOfNeighbour++;
        }
        return true;
    }

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

    @Override
    public void clear()
    {
        for(int i = 0;i<size;i++)
            binaryVecter[i] = false;
        numOfNeighbour = 0;
    }

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

    @Override
    public boolean isEmpty()
    {
        return numOfNeighbour == 0 ? true : false;
    }

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
                return true;
            binaryVecter[index] = false;
            numOfNeighbour--;
            return true;
        }
    }

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

    @Override
    public int size()
    {
        return numOfNeighbour;
    }

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


    /************************* */
    @Override
    public <T> T[] toArray(T[] arg0)
    {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toArray'");
    }

    @Override
    public Iterator<Integer> iterator()
    {
        return new AdjacencyVectIterater();
    }

    private class AdjacencyVectIterater implements Iterator<Integer>
    {
        private int currentIndex = -1;
        private int nextIndex = -1;

        public AdjacencyVectIterater()
        {
            findNext();
        }

        @Override
        public boolean hasNext()
        {
            return nextIndex != -1;
        }

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