package DSA.Sorting;

import java.util.Comparator;
import java.util.Random;

/**
 * MyQuickSort class extends GTUSorter and implements the quicksort algorithm.
 * This class provides a way to sort arrays using the quicksort method with optional
 * fallback to another sorting algorithm for small partitions.
 */
public class MyQuickSort extends GTUSorter
{
    /** The size limit below which partitions will use the alternative sorter */
    private int PartitionLimit;
    /** The alternative sorter to use for small partitions */
    private GTUSorter sorter;
    
    /**
     * Constructs a new MyQuickSort with the specified alternative sorter and partition limit.
     *
     * @param sorter The alternative sorter to use for small partitions
     * @param partition The size limit below which partitions will use the alternative sorter
     */
    public MyQuickSort(GTUSorter sorter,int partition)
    {
        this.sorter = sorter;
        PartitionLimit = partition;
    }

    /**
     * Constructs a new MyQuickSort with no alternative sorter.
     * This will use pure quicksort for all partitions.
     */
    public MyQuickSort()
    {
        PartitionLimit = 0;
        sorter = null;
    }

    /**
     * Sorts the specified range of the array using the quicksort algorithm.
     * If the partition size is below the limit and an alternative sorter is provided,
     * it will use the alternative sorter instead.
     *
     * @param <T> the type of elements in the array
     * @param arr the array to be sorted
     * @param start the starting index of the range to be sorted (inclusive)
     * @param end the ending index of the range to be sorted (exclusive)
     * @param comparator the comparator to determine the order of the array
     */
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

    /**
     * Partitions the array around a randomly selected pivot element.
     * Elements less than the pivot are moved to the left, and elements greater
     * than or equal to the pivot are moved to the right.
     *
     * @param <T> the type of elements in the array
     * @param arr the array to be partitioned
     * @param start the starting index of the range to be partitioned (inclusive)
     * @param end the ending index of the range to be partitioned (exclusive)
     * @param comparator the comparator to determine the order of the array
     * @return the index of the pivot element after partitioning
     */
    private <T> int partition(T[] arr,int start,int end,Comparator<T> comparator)
    {
        Random random = new Random();
        int pivot_index = start + random.nextInt(end - start);
        T pivotValue = arr[pivot_index];
        shift(arr, pivot_index, end - 1);

        int storeIndex = start;
        for(int i = start; i < end - 1; i++)
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

    /**
     * Swaps two elements in the given array.
     *
     * @param <T> the type of elements in the array
     * @param arr the array containing the elements to swap
     * @param first the index of the first element to swap
     * @param second the index of the second element to swap
     */
    private<T> void shift(T[] arr, int first, int second)
    {
        T tmp = arr[first];
        arr[first] = arr[second];
        arr[second] = tmp;
    }   
}