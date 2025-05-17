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
        if(end - start <= PartitionLimit && PartitionLimit != 0)
        {
            sorter.sort(arr, comparator);
            return;
        }
        else if(start < end)
        {
            if(end - start != 1)
            {
                int pivot_index = partition(arr,start,end,comparator);
                sort(arr,start,pivot_index - 1, comparator);
                sort(arr,pivot_index + 1,end, comparator);
            }
        }
    }

    private <T> int partition(T[] arr,int start,int end,Comparator<T> comparator)
    {
        Random random = new Random();
        int pivot_index = random.nextInt(end - start) + start;
        T pivot = arr[pivot_index];
        int i = start - 1;
        for(int j = start;j <= end-1;j++)
        {
            if(comparator.compare(arr[j], pivot) < 0)
            {
                i++;
                shift(arr, i, j);
            }
        }
        shift(arr, i + 1, end);
        return i + 1;
    }

    private<T> void shift(T[] arr, int first, int second)
    {
        T tmp = arr[first];
        arr[first] = arr[second];
        arr[second] = tmp;
    }
    
}
