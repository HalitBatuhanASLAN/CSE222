package DSA.Sorting;

import java.util.Comparator;
import java.util.Random;

public class MyQuickSort extends GTUSorter
{
    private int PartitionLimit;
    private GTUSorter sorter;
    public MyQuickSort(GTUSorter sorter,int partition)
    {
        this.sorter = sorter;
        PartitionLimit = partition;
    }
    public MyQuickSort()
    {
        PartitionLimit = 0;
        sorter = null;
    }
    @Override
    public <T> void sort(T[] arr, int start, int end, Comparator<T> comparator)
    {
        if(start >= end - 1)
            return;
        if(end - start <= PartitionLimit && PartitionLimit != 0)
        {
            sorter.sort(arr, comparator);
            return;
        }
        else if(start < end)
        {
            int pivot_index = partition(arr,start,end,comparator);
            sort(arr,start,pivot_index, comparator);
            sort(arr,pivot_index + 1,end, comparator);
        }
    }

    private <T> int partition(T[] arr,int start,int end,Comparator<T> comparator)
    {
        Random random = new Random();
        int pivot_index = start + random.nextInt(end - start);
        T pivotValue = arr[pivot_index];
        shift(arr, pivot_index, end - 1);

        int storeIndex = start;
        for (int i = start; i < end - 1; i++)
        {
            if (comparator.compare(arr[i], pivotValue) < 0)
            {
                shift(arr, i, storeIndex);
                storeIndex++;
            }
        }

        shift(arr, storeIndex, end - 1);

        return storeIndex;
    }

    private<T> void shift(T[] arr, int first, int second)
    {
        T tmp = arr[first];
        arr[first] = arr[second];
        arr[second] = tmp;
    }   
}