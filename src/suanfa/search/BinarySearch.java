package suanfa.search;

import java.util.ArrayList;
import java.util.List;

public class BinarySearch
{
    public static void main(String[] args)
    {
        int[] luan = { 1,2,45,45,45,45,667,5433,6123,44555,67854,3452452,234235235};
        ArrayList<Integer> index = binarySearch2(45, luan, 0, luan.length - 1);
        System.out.println("找到的值其在数组中的位置为 " + index);

    }

    public static int binarySearch(int value, int[] arr, int left, int right)
    {
        if(left >= right)
        {
            return -1;
        }
        //这里arr默认是由小到大排序
        int mid = (left + right) / 2;
        int midvalue = arr[mid];
        if( value < midvalue) {
            //这里使用了return开头的递归，是因为这里需要返回索引值，故需要利用return来传递最后找到的index值
            return binarySearch(value, arr, left, mid - 1);
        }
        else if( value > midvalue)
        {
            return binarySearch(value, arr, mid + 1, right);
        }
        else
        {
            return mid;
        }
    }

    public static ArrayList<Integer> binarySearch2(int value, int[] arr, int left, int right)
    {
        if(left >= right)
        {
            return new ArrayList<Integer>();
        }

        int mid = (left + right) / 2;
        int midvalue = arr[mid];

        if(value < midvalue)
            return binarySearch2(value, arr, left, mid - 1);
        else if(value > midvalue)
            return binarySearch2(value, arr, mid + 1, right);
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
