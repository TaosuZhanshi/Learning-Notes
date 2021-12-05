package suanfa.paixu;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ShellSort
{
    public static void main (String[] args)
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


        int[] zhen = shellSortExchange(luan);

/*        for(int i = 0; i < zhen.length; i++)
            System.out.println(zhen[i]);*/

        System.out.println("排序后");
        Date data2 = new Date();
        String data2Str = simpleDateFormat.format(data2);
        System.out.println("排序后的时间是" + data2Str);
    }

    public static int[] shellSortMove(int[] arr)
    {
        int numberofcom = (int)(arr.length / 2);
        //分组数量
        int lengthofcom = (int)(arr.length / numberofcom);
        //分组数量

        int temp = 0;
        int count = 0;
        while( numberofcom != 0)
        {
            for(int i = numberofcom; i < arr.length; i++)
            {
                //这里仍然是用到插值法，由后往前插值
                for(int j = i - numberofcom; j >= 0 ; j -= numberofcom)
                //依次把数值较大的插值由后往前传递
                {
                    if(arr[j] > arr[j + numberofcom])
                    //如果后面的数据小于前面的数据那么进行交换
                    {
                        temp = arr[j];
                        arr[j] = arr[j + numberofcom];
                        arr[j + numberofcom] = temp;
                    }
                }
            }
            numberofcom = (int) (numberofcom / 2);
            count += 1;
        }
        System.out.println("count = " + count);
        return arr;
    }

    public static int[] shellSortExchange(int[] arr)
    {
        int numberofcom = (int)(arr.length / 2);
        //分组数量
        int lengthofcom = (int)(arr.length / numberofcom);
        //分组数量

        int temp = 0;
        int count = 0;
        while( numberofcom != 0)
        {
            for(int i = numberofcom; i < arr.length; i++)
            {
                //这里仍然是用到插值法，由后往前插值
                temp = arr[i];

                int j = i - numberofcom;
                while( j >= 0 && arr[j] > temp)
                //依次把数值较大的插值由后往前传递
                {
                    arr[j + numberofcom] = arr[j];

                    j -= numberofcom;
                }
                    arr[j + numberofcom] = temp;
            }
            numberofcom = (int) (numberofcom / 2);
            count += 1;
        }
        System.out.println("count = " + count);
        return arr;
    }
}
