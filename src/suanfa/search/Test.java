package suanfa.search;

import java.util.Arrays;

public class Test
{
    public static int maxsize = 100;
    public static void main(String[] args)
    {
        int[] luan = {1, 8, 10, 89, 100, 1000, 1234};
        int index = fibSearch(luan, 1111);
        System.out.println("找到的值其在数组中的位置为 " + index);
    }

    public static int[] fibMaker()
    {
        int[] fib = new int[maxsize];
        fib[0] = 1;
        fib[1] = 1;
        for(int i = 2; i < maxsize; i ++)
        {
            fib[i] = fib[i - 1] + fib[ i - 2];
        }
        return fib;
    }

    public static int fibSearch(int[] arr, int a)
    {
        int low = 0;
        int high = arr.length - 1;
        int[] fib = fibMaker();
        int k = 0;
        while(fib[k] < arr.length)
        {
            k ++;
        }
        int [] arrCopy = Arrays.copyOf(arr, fib[k]);
        for(int i = arr.length; i < arrCopy.length; i ++)
        {
            arrCopy[i] = arr[high];
        }

        while(low < high)
        {
            int midFront = low + fib[k - 1] - 1;
            int midBehind = high + 1;
            if(a < arrCopy[midFront])
            {
                high = midFront;
                k -= 1;
            }
            else if(a > arrCopy[midBehind])
            {
                low = midBehind;
                k -= 2;
            }
            else if(a == arrCopy[midFront])
            {
                return midFront;
            }
            else if(a == arrCopy[midBehind])
            {
                return midBehind;
            }
            else
            {
                break;
            }
        }
        return -1;
    }

}
