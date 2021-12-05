package shujujjiegou.recusion;

public class Queue8
{
    int max = 8;
    int[] array = new int[max];
    int count = 0;

    public static void main(String[] args)
    {
        //进行测试
        Queue8 queue8 = new Queue8();
        queue8.place(0);

    }

    public void place(int n)
    {
        if( n == max)
        //如果已经到顶,需要注意的是n = max时已经在放第九个棋子
        {
            print();
            return ;

            //第九个棋子不用防止，打印好此放置方案后回溯,
            //当然，回溯不是由这一步启用，这一步只是阻断当前的递归
            //真正的回溯不需要启用，递归算法中会自动进行回溯。
            //由此可见回溯只是递归的一个正常步骤而已
        }

        for (int i = 0; i < array.length; i ++)
        {
            array[n] = i;
            //将第 n + 1 个棋子放在第 i + 1个位置
            if(judge(n))
            //判断此时的第n+1个棋子的位置是否和之前的棋子有冲突
            //如果没有冲突则开始放下一个棋子
            {
                place(n + 1);
            }
        }

    }

    public boolean judge(int n){
        for(int i = 0; i < n; i ++)
        {
            if( array[i] == array[n] || Math.abs( n - i) == Math.abs( array[n] - array[i]))
            {
                return false;
            }
        }
        return true;
    }

    public void print()
    {
        count ++;
        System.out.printf("第%d种解法为： \n", count);
        for(int i = 0; i < array.length; i++)
        {
            System.out.print( array[i] + " ");
        }
        System.out.println("");
    }
}
