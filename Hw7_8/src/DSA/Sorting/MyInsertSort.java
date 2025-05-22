package DSA.Sorting;

import java.util.Comparator;

/**
 * MyInsertSort class extends GTUSorter and implements the insertion sort algorithm.
 * This class provides a way to sort arrays using the insertion sort method.
 */
public class MyInsertSort extends GTUSorter 
{
    /**
     * Sorts the specified range of the array using the insertion sort algorithm.
     * The sorting is done according to the order induced by the specified comparator.
     *
     *  Time Complexity:
     * - Best Case: O(n) when the array is already sorted
     * - Average Case: O(n²)
     * - Worst Case: O(n²) when the array is sorted in reverse order
     * 
     * Space Complexity: O(1) - only uses a constant amount of extra space
     * @param <T> the type of elements in the array
     * @param arr the array to be sorted
     * @param start the starting index of the range to be sorted (inclusive)
     * @param end the ending index of the range to be sorted (exclusive)
     * @param comparator the comparator to determine the order of the array
     */
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