package DSA.Sorting;

import java.util.Comparator;

public class MyInsertSort extends GTUSorter 
{
    @Override
    public <T> void sort(T[] arr, int start, int end, Comparator<T> comparator)
    {
        for(int i = start + 1;i<end;i++)
        {
            int nextPos = i;
            T nextValue = arr[nextPos];
            while (nextPos > 0 && comparator.compare(arr[nextPos-1],nextValue) > 0)
            {
                arr[nextPos] = arr[nextPos - 1];
                nextPos--;
            }
            arr[nextPos] = nextValue;
        }
    }
    
}

/*
 * 
    
    
    @Override
    public <T> void sort(T[] arr, int start, int end, Comparator<T> comparator)
    {
        if(start >= end -1)
            return;

        int nextPos = start + 1;
        T nextValue = arr[nextPos];
        nextPos = innerLoop(arr, nextPos, nextValue, comparator);
        arr[nextPos] = nextValue;
        sort(arr, start + 1, end, comparator);
        
    }

    private <T> int innerLoop(T[] arr, int nextPos, T nextValue, Comparator<T> comparator)
    {
        if(nextPos <= 0)
            return nextPos;
        else if(comparator.compare(arr[nextPos - 1], nextValue) <= 0)
            return nextPos;
        arr[nextPos] = arr[nextPos - 1];
        return innerLoop(arr, nextPos - 1, nextValue, comparator);
    }
    

 */