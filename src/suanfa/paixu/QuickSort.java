package suanfa.paixu;

import java.lang.reflect.Array;
import java.util.Arrays;

public class QuickSort
{
    public static void main (String[] args)
    {
        int[] luan = {9, 78, 0, -23, -567, -70};
        int[] zhen = quickSort(luan, 0, luan.length - 1);
        System.out.println("arr = " + Arrays.toString(zhen));

    }

    public static int[] quickSort(int[] arr, int left, int right)
            //快速排序是对冒泡排序的优化
            //其运用到了递归，一直二分进行排序。
    {
        int l = left;
        int r = right;

        int pivot = arr[(left + right) / 2];
        int temp = 0;

        while( l < r)
        {
            while( arr[l] < pivot)
            {
                l += 1;
            }
            while( arr[r] > pivot)
            {
                r -= 1;
            }

            if( l >= r )
            //如果privot两边已经排好，那么直接跳出这一步
            {
                break;
            }

            //将两边的问题值进行交换，如果一侧没有问题值，那么此时指针必定指向privot所在值，这时将另一侧的问题值与privot进行交换
            temp = arr[r];
            arr[r] = arr[l];
            arr[l] = temp;

            //若pivot的r侧已经没有无序值了（r指向pivot），而l侧有异常值，
            //那么在交换过后，l指向pivot，r指向的是有序值，所以需要将r--,使r指针指向中间还未扫描到的部分。
            if(arr[l] == pivot)
                r --;
            //这里原理同上，交换后，r指针指向pivot，l指针指向有序值，将l指针移动，使l指针指向未扫描到的部分。
            if(arr[r] == pivot)
                l ++;
        }

        if( l == r )
        {
            l ++;
            r --;
        }

        if(r > left)
            quickSort(arr, left, r);
        if(right > l)
            quickSort(arr, l, right);


        return arr;
    }
}
