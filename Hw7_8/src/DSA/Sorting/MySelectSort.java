package DSA.Sorting;

import java.util.Comparator;

/**
 * MySelectSort class extends GTUSorter and implements the selection sort algorithm.
 * This class provides a way to sort arrays using the selection sort method.
 */
public class MySelectSort extends GTUSorter
{
    /**
     * Sorts the specified range of the array using the selection sort algorithm.
     * The sorting is done according to the order induced by the specified comparator.
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
        if(start >= end)
            return;
        int posMin;
        for(int fill = start;fill < end;fill++)
        {
            posMin = fill;
            for(int next = fill + 1;next < end ;next++)
            {
                if(comparator.compare(arr[next],arr[posMin]) < 0)
                    posMin = next;
            }
            if(posMin != fill)
                change(arr,posMin,fill);
        }
    }

    /**
     * Swaps two elements in the given array.
     *
     * @param <T> the type of elements in the array
     * @param arr the array containing the elements to swap
     * @param first the index of the first element to swap
     * @param second the index of the second element to swap
     */
    private<T> void change(T[] arr,int first,int second)
    {
        T tmp = arr[first];
        arr[first] = arr[second];
        arr[second] = tmp;
    }
}