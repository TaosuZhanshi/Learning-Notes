package suanfa.paixu;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class RadixSort
{
    public static void main(String[] args)
    {
        int[] luan1 = {9, 78, 0, -23, -567, -70};
        int[] zhen1 = radixSort(luan1);
        System.out.println("arr = " + Arrays.toString(zhen1));


        int[] luan = new int[8000000];
        for(int i = 0; i < 8000000; i ++)
        {
            luan[i] = (int) (Math.random() * 8000000);
        }

        System.out.println("排序前");
        Date data1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String data1Str = simpleDateFormat.format(data1);
        System.out.println("排序前的时间是" + data1Str);


        int[] zhen = radixSort(luan);

/*        for(int i = 0; i < zhen.length; i++)
            System.out.println(zhen[i]);*/

        System.out.println("排序后");
        Date data2 = new Date();
        String data2Str = simpleDateFormat.format(data2);
        System.out.println("排序后的时间是" + data2Str);
    }

    public static int[] radixSort(int[] arr)
    {
        int lengthOfArr = arr.length;

        int[][] bucket = new int[10][lengthOfArr];

        int[] pBucketElementCount = new int[10];
        int[] nBucketElementCount = new int[10];

        int digitOfElement = 0;

        int jianYanWei = 10;
        int jianYanWei2 = 1;

        boolean flag = true;

        for(int i = 0; i < nBucketElementCount.length; i ++)
            nBucketElementCount[i] = lengthOfArr - 1;

        while(flag)
        {
            for(int i = 0; i < arr.length; i ++)
            {
                if(arr[i] >= 0)
                {
                    digitOfElement = ((int)(arr[i] / jianYanWei2)) % jianYanWei;
                    if(digitOfElement > 9)
                        digitOfElement = 0;
                    bucket[digitOfElement][pBucketElementCount[digitOfElement]] = arr[i];
                    pBucketElementCount[digitOfElement] ++;
                }
                else
                {
                    digitOfElement = ((int)(arr[i] / jianYanWei2)) % jianYanWei;
                    if(digitOfElement < -9)
                        digitOfElement = 0;
                    digitOfElement = Math.abs(digitOfElement);
                    bucket[digitOfElement][nBucketElementCount[digitOfElement]] = arr[i];
                    nBucketElementCount[digitOfElement] --;
                }
            }

            if(pBucketElementCount[0] == nBucketElementCount[0] + 1)
                flag = false;

            jianYanWei2 *= 10;

            int indexOfArr = 0;

            for(int i = nBucketElementCount.length - 1; i    >= 0; i--)
            {
                if(nBucketElementCount[i] != 5)
                {
                    for( int j = lengthOfArr - 1; j > nBucketElementCount[i]; j --)
                    {
                        arr[indexOfArr ++] = bucket[i][j];
                    }
                    nBucketElementCount[i] = lengthOfArr - 1;
                }
            }
            for(int i = 0; i < nBucketElementCount.length; i++)
            {
                if(pBucketElementCount[i] != 0)
                {
                    for( int j = 0; j < pBucketElementCount[i]; j ++)
                    {
                        arr[indexOfArr ++] = bucket[i][j];
                    }
                    pBucketElementCount[i] = 0;
                }
            }
        }

        return arr;
    }
}
