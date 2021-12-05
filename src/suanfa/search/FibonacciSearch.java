package suanfa.search;

import java.util.Arrays;

public class FibonacciSearch
{
    public static int maxSize = 100;
    public static void main(String[] args)
    {
        int[] luan = {1, 8, 10, 89, 1000, 1234};
        int index = fibSearch(luan, 1234);
        System.out.println("找到的值其在数组中的位置为 " + index);
    }

    public static int[] fib()
    {
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for(int i = 2; i < maxSize; i++)
        {
            f[i] = f[i - 1] + f [i - 2];
        }
        return f;
    }

    public static int fibSearch(int[] a, int key)
    {
        int low = 0;
        int high = a.length - 1;
        int k = 0;
        int midFront = 0;
        int midBehind = 0;
        int f[] = fib();
        //获取斐波那契数列分割数值下标
        while(high > f[k] - 1)
        {
            k ++;
        }

        int[] temp = Arrays.copyOf(a, f[k]);
        for(int i = high + 1; i < temp.length; i ++)
        {
            temp[i] = a[high];
        }

        while(low <= high)
        {
            midFront = low + f[k - 1] - 1;
            midBehind = midFront + 1;
            if(key < temp[midFront])
            {
                high = midFront;
                k --;
            }
            else if(key > temp[midBehind])
            {
                low = midFront;
                k -= 2;
            }
            else if(key == temp[midFront])
            {
                return midFront;
            }
            else
                return midBehind;
        }
        return -1;
    }


}
