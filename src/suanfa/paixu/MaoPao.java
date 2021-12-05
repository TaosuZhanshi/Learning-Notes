package suanfa.paixu;

public class MaoPao
{
    public static void main(String[] args)
    {
        int[] luan = {8, 0, -2, -3, 20, 10, 7, 4 };


        int temp = 0;
        //作为替换值

        int i = luan.length - 1;
        //i为一个给while循环计算是否还需要进行冒泡的判断值
        //为了给每一次单向冒泡的方便，这里将i 设为luan.length - 1，每次单向排序后i -= 1;

        int count = 0;
        int bijiaocishu = 0;

        while(i >= 0)
        {
            temp = luan[0];
            //将luan第一个数赋予temp
            count = 0;
            for(int j = 1; j <= i; j ++)
            {
                if (luan[j] < luan[ j - 1 ])
                {
                    temp = luan[ j - 1];
                    luan[j - 1] = luan[j];
                    luan[j] = temp;
                    //将相邻两个元素中更大的数放在后面
                    count += 1;
                    bijiaocishu ++;
                }
            }
            if(count == 0)
                //如果没有发生数据的交换，则说明已经排序完成，不必进行后续排序，退出while循环
                break;
            System.out.printf("第%d次排序，交换了" + count + "次", luan.length - i);
            System.out.println("");

            i --;
        }
        for(int k = 0; k < luan.length; k ++)
            System.out.println(luan[k]);
        System.out.println("进行比较的次数为" + bijiaocishu);

    }
}
