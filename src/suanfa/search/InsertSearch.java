package suanfa.search;

import java.util.ArrayList;

public class InsertSearch
{
    public static void main(String[] args)
    {
        int[] luan = { 1,2,45,45,45,45,667,5433,6123,44555,67854,3452452,234235235};
        ArrayList<Integer> index = insertSearch2(luan, 0, luan.length - 1, 45);
        System.out.println("找到的值其在数组中的位置为 " + index);

    }

    public static int insertSearch(int[] arr, int left, int right, int value)
    {
        if( left >= right || value < arr[left] || value > arr[right])
        {
            return -1;
        }

        int mid = left + (right - left)* (value - left) / (arr[right] - arr[left]);
        int midValue = arr[mid];
        if(value < midValue)
            return insertSearch(arr, left, mid, value);
        else if(value > midValue)
            return insertSearch(arr, mid, right, value);
        else
            return mid;
    }

    public static ArrayList<Integer> insertSearch2(int[] arr, int left, int right, int value)
    {
        if( left >= right || value < arr[left] || value > arr[right])
        {
            return new ArrayList<Integer>();
        }

        int mid = left + (right - left)* (value - left) / (arr[right] - arr[left]);
        int midValue = arr[mid];

        if(value < midValue)
            return insertSearch2(arr, left, mid - 1, value);
        else if(value > midValue)
            return insertSearch2(arr, mid + 1, right, value);
        else
        {
            ArrayList<Integer> targetIndex = new ArrayList<Integer>();
            int index = mid;
            while( index >= 0 && arr[index] == value)
            {
                targetIndex.add(index --);
            }
            index = mid + 1;
            while( index <= right && arr[index] == value)
            {
                targetIndex.add(index ++);
            }
            return targetIndex;
        }
    }

}
