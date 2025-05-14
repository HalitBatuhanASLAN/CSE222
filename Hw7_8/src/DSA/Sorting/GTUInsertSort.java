package DSA.Sorting;

import java.util.Comparator;

public class GTUInsertSort extends GTUSorter 
{
    @Override
    public <T> void sort(T[] arr, int start, int end, Comparator<T> comparator)
    {
        if(start >= end -1)
            return;

        int i = start + 1;

        int nextPos = start + 1;
        T nextValue = arr[nextPos];
        nextPos = innerLoop(arr, nextPos, nextValue, comparator);
        arr[nextPos] = nextValue;
        sort(arr, i + 1, end, comparator);
        
    }

    private <T> int innerLoop(T[] arr, int nextPos, T nextValue, Comparator<T> comparator)
    {
        if(nextPos <= 0)
            return nextPos;
        if(comparator.compare(arr[nextPos-1], nextValue) <= 0)
            return nextPos;
        arr[nextPos] = arr[nextPos - 1];
        return innerLoop(arr, nextPos - 1, nextValue, comparator);
    }
    
}

/*
 * 
    @Override
    public <T> void sort(T[] arr, int start, int end, Comparator<T> comparator)
    {
        for(int i = start + 1;i<end;i++)
        {
            int nextPos = start + 1;
            T nextValue = arr[nextPos];
            while (nextPos > 0 && comparator.compare(arr[nextPos-1],nextValue) > 0)
            {
                arr[nextPos] = arr[nextPos - 1];
                nextPos--;
            }
            arr[nextPos] = nextValue;
        }
    }


 */