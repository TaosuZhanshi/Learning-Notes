package shujujjiegou.tree;

import java.util.Arrays;

public class HeapSortDemo {
    public static void main(String[] args) {
        int[] arr = {4, 6, 8, 5, 9};
        heapSort(arr);
    }

    public static void heapSort(int[] arr)
    {
        System.out.println("堆排序结果为 : ");

        //第一步，全树域内进行排序，此时的树为大堆顶
        for(int i = arr.length / 2 - 1; i >= 0; i --)
        {
            adjustHeap(arr, i, arr.length);
        }

        //第二布，由于基本的排序已经完成，这里不再需要进行大量的排序，而是只需要将堆顶的数置换到最后，再进行简单的排序就行
        for(int j = arr.length - 1; j > 0; j --)
        {
            int temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr, 0, j);
        }

        System.out.println(Arrays.toString(arr));
    }

    public static void adjustHeap(int[] arr, int i, int length)
    {
        //将arr中的i先暂时存放在temp中
        int temp = arr[i];

        for(int k = i * 2 + 1; k < length; k = k * 2 + 1)
        {
            if( k + 1 < length && arr[k] < arr[k + 1])
            {
                k ++;
            }

            if(arr[k] > temp)
            {
                arr[i] = arr[k];
            }
            else {
                break;
            }

            i = k;
        }
        //for循环完成，则这里的堆顶为以i为堆顶的局部最大值
        arr[i] = temp;

    }

}
