package suanfa.paixu;

public class XuanZe
{
    public static void main(String[] args)
    {
        int[] luan = {8, 0, -2, -3, 20, 10, 7, 4 };

        int temp1 = 0;
        int count = 0;

        for(int i = 0; i < luan.length - 1; i ++)
        {
            int minIndex = i;
            //将当前待替换值的序号赋予xuhao变量
            for(int j = i + 1; j < luan.length; j ++)
            {
                //从待替换值开始往后第一个值开始比较，找出最小值的序号
                if(luan[j] < luan[minIndex])
                {
                    minIndex = j;
                }
                count ++;
            }
            //将包括待替换值之后的所有的值中的最小值与待替换值进行交换
            temp1 = luan[i];
            luan[i] = luan[minIndex];
            luan[minIndex] = temp1;

        }

        for( int i = 0; i < luan.length ; i ++)
            System.out.println(luan[i]);
        System.out.println("进行比较的次数为" + count);
    }
}
