package DSA.Sorting;

import java.util.Comparator;
import java.util.Random;

public class GTUQuickSort extends GTUSorter
{

    @Override
    public <T> void sort(T[] arr, int start, int end, Comparator<T> comparator)
    {
        if(start < end)
        {
            int pivot = partition(arr,start,end,comparator);
            sort(arr,start,pivot - 1, comparator);
            sort(arr,pivot + 1,end, comparator);
        }
    }

    private <T> int partition(T[] arr,int start,int end,Comparator<T> comparator)
    {
        Random random = new Random();
        int pivot = random.nextInt(end - start) + start;
        int up = start;
        int down = end;
        do
        {
            while(comparator.compare(arr[up],arr[pivot]) < 0 && up < end) 
                {up++;}
            while(comparator.compare(arr[down],arr[pivot]) > 0 && down > start) 
                {down--;}
            if(up < down)
                shift(arr,up,down);
        }while(up < down);
        shift(arr, pivot,down);
        return down;
    }

    private<T> void shift(T[] arr, int first, int second)
    {
        T tmp = arr[first];
        arr[first] = arr[second];
        arr[second] = tmp;
    }
    
}
