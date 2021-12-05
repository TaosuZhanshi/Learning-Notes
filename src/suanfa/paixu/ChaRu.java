package suanfa.paixu;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ChaRu
{
    public static void main(String[] args)
    {
        int[] luan = new int[80000];
        for(int i = 0; i < 80000; i ++)
        {
            luan[i] = (int) (Math.random() * 80000);
        }

        System.out.println("排序前");
        Date data1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String data1Str = simpleDateFormat.format(data1);
        System.out.println("排序前的时间是" + data1Str);


        int[] zhen = insertSort(luan);

/*        for(int i = 0; i < zhen.length; i++)
            System.out.println(zhen[i]);*/

        System.out.println("排序后");
        Date data2 = new Date();
        String data2Str = simpleDateFormat.format(data2);
        System.out.println("排序后的时间是" + data2Str);




    }

    public static int[] insertSort(int[] arr)
    {
        for (int i = 1; i < arr.length; i ++)
        {
            int insertVal = arr[i];
            //将需要插入到数组前面已排序组合中的元素赋予inverset
            int insertIndex = i - 1;
            //将需要插入到排序好数组中的元素的前一个索引值赋予insetIndex
            while(insertIndex >= 0 && insertVal < arr[insertIndex])
            //如果索引值未超出数组，需插入的值未大于索引在数组中所对应的值，那么将索引数据后移一位
            {
                arr[insertIndex + 1] = arr[ insertIndex];
                insertIndex --;
            }
            arr[insertIndex + 1] = insertVal;
            //将插入值放入到索引值的前一个位置上。
            //这里需要解释的一是，为什么是前一个索引值，因为他是和索引值进行比对，发现比索引值大，那么就放入到索引值的前一个位置
            //第二，这里由于前一步中索引值前一个位置中的元素已经前移，所以可以直接插入到指定位置而不对数组的元素造成覆盖
        }
        return arr;
    }
}
