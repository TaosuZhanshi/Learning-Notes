package suanfa.paixu;

import java.util.Arrays;

public class Test
{
    public static void main(String[] args)
    {
        int[] luan = {9, 78, 0, -23, -567, -70};
        int[] zhen = radixSort(luan);
        System.out.println("arr = " + Arrays.toString(zhen));
    }


    public static int[] radixSort(int[] arr)
    {
        int lengthOfArr = arr.length;

        int[][] bucket = new int[10][lengthOfArr];
        int[] nBucketCount = new int[10];
        for(int i = 0; i < nBucketCount.length; i ++)
        {
            nBucketCount[i] = lengthOfArr - 1;
        }
        int[] pBucketCount = new int[10];

        int yanSuanWei1 = 1;
        int yanSuanWei2 = 10;

        int digitOfElement = 0;

        boolean flag = true;

        while(flag)
        {
            for (int i = 0; i < lengthOfArr; i++) {
                if (arr[i] < 0) {
                    digitOfElement = Math.abs(((int) (arr[i] / yanSuanWei1)) % yanSuanWei2);
                    if (digitOfElement > 9)
                        digitOfElement = 0;
                    bucket[digitOfElement][nBucketCount[digitOfElement]] = arr[i];
                    nBucketCount[digitOfElement]--;
                } else {
                    digitOfElement = ((int) (arr[i] / yanSuanWei1)) % yanSuanWei2;
                    if (digitOfElement > 9)
                        digitOfElement = 0;
                    bucket[digitOfElement][pBucketCount[digitOfElement]] = arr[i];
                    pBucketCount[digitOfElement]++;
                }
            }

            yanSuanWei1 *= 10;

            int index = 0;

            if (pBucketCount[0] == nBucketCount[0] + 1)
            {
                flag = false;
            }

            for(int i = nBucketCount.length - 1; i >= 0; i --)
            {
                if(nBucketCount[i] != lengthOfArr - 1)
                {
                    for(int j = lengthOfArr - 1; j > nBucketCount[i]; j --)
                    {
                        arr[index ++] = bucket[i][j];
                    }
                    nBucketCount[i] = lengthOfArr - 1;
                }
            }
            for(int i = 0; i < pBucketCount.length; i ++)
            {
                if(pBucketCount[i] != 0)
                {
                    for(int j = 0; j < pBucketCount[i]; j ++)
                    {
                        arr[index ++] = bucket[i][j];
                    }
                    pBucketCount[i] = 0;
                }
            }
        }
        return arr;
    }
}
