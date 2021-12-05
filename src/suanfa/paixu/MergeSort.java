package suanfa.paixu;

import java.util.Arrays;

public class MergeSort
{
    public static void main(String[] args)
    {

        int arr[] = {8, 4, 5, 7, 1, 3, 6, 2};
        int[] temp = new int[arr.length];
        mergeSort(arr, 0, arr.length -1, temp);
        System.out.println("归并排序后 = " + Arrays.toString(arr));



    }


    public static void mergeSort(int[] arr, int left, int right, int[] temp)
    {
        if(left < right)
        {
            int mid = (left + right) / 2;

            //二分递归
            mergeSort(arr, left, mid, temp);
            mergeSort(arr, mid + 1, right, temp);

            //每分一次，相应进行merge
            merge(arr, left, mid, right, temp);
        }
    }

    /**
     *
     * @param arr    排序的数组
     * @param left   左边有序序列的索引
     * @param mid    中间索引
     * @param right  右边索引
     * @param temp   做中转的数组
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] temp)
    {
        int i = left;
        int j = mid + 1;
        int t = 0;

        //将两段中的较小数依次放入到temp中
        while(i <= mid && j <= right)
        {
            if(arr[i] < arr[j])
            {
                temp[t] = arr[i];
                t ++;
                i ++;
            }
            else
            {
                temp[t] = arr[j];
                t ++;
                j ++;
            }
        }

        //把两段中剩余未传的数传入到temp中
        while(i <= mid)
        {
            temp[t] = arr[i];
            t ++;
            i ++;
        }
        while( j <= right)
        {
            temp[t] = arr[j];
            t ++;
            j ++;
        }

        //将temp中的有序数据重新拷回去
        t = 0;
        int tempLeft = left;
        while(tempLeft <= right)
        {
            arr[tempLeft] = temp[t];
            tempLeft ++;
            t ++;
        }

    }
}
