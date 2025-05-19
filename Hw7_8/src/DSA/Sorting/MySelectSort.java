package DSA.Sorting;

import java.util.Comparator;

public class MySelectSort extends GTUSorter
{
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

    private<T> void change(T[] arr,int first,int second)
    {
        T tmp = arr[first];
        arr[first] = arr[second];
        arr[second] = tmp;
    }
}


    /*@Override
    public <T> void sort(T[] arr, int start, int end, Comparator<T> comparator)
    {
        if(start > end - 2)
            return;
        int fill = start;
        int posMin;
        posMin = findPosMin(arr, fill, fill + 1, end, comparator);
        change(arr, posMin, fill);

        sort(arr,start + 1,end, comparator);
    }

    private <T> int findPosMin(T[] arr,int posMin,int next,int end,Comparator<T> comparator)
    {
        if(next > end -1)
            return posMin;
        if(comparator.compare(arr[next],arr[posMin]) < 0)
        {
            posMin = next;
        }
        return findPosMin(arr, posMin, next + 1, end, comparator);
    }*/
