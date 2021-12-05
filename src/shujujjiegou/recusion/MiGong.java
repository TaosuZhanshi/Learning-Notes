package shujujjiegou.recusion;

public class MiGong
{
    public static void main(String[] args){
        //先创建一个二维数组，模拟迷宫
        //地图
        int[][] map0 = new int[8][7];

        for(int i = 0; i < 7; i++)
        {
            map0[0][i] = 1;
            map0[7][i] = 1;
        }
        for (int j = 0; j < 8; j ++)
        {
            map0[j][0] = 1;
            map0[j][6] = 1;
        }
        map0[3][1] = 1;
        map0[3][2] = 1;

        int[][]map = map0;
        int [][][] order = new int[24][4][2];
        int [][] source = new int[4][2];
        source[0][0] = 1;
        source[1][1] = 1;
        source[2][0] = -1;
        source[3][1] = -1;

        for(int i = 0; i < 4; i++)
        {
        }







        //设置新的地图形式
        System.out.println("迷宫地图为:");
        for (int i = 0; i < 8; i ++)
        {
            for (int j = 0; j < 7; j ++)
            {
                System.out.print(map[i][j] + "  ");
            }
            System.out.println("");
        }

        setWay(map, 1, 1, order[0]);

        System.out.println("走出来的迷宫地图为:");
        for (int i = 0; i < 8; i ++)
        {
            for (int j = 0; j < 7; j ++)
            {
                System.out.print(map[i][j] + "  ");
            }
            System.out.println("");
        }
    }


    public static boolean setWay(int[][] map, int i ,int j, int[][] order)
    {
        if(map[6][5] == 2)
            //如果已经找到了终点，那么返回true
            return true;
        else
        {
            if(map[i][j] == 0)
                //如果当前点之前没有经过，那么就按照下→右→上→左走
            {
                map[i][j] = 2;

                int a1 = order[0][0];
                int a2 = order[0][1];
                int b1 = order[1][0];
                int b2 = order[1][1];
                int c1 = order[2][0];
                int c2 = order[2][1];
                int d1 = order[3][0];
                int d2 = order[3][1];

                if(setWay(map, i + a1, j + a2, order))
                    //往下走
                    return true;
                else if (setWay(map,i + b1, j + b2, order))
                    //往右走
                    return true;
                else if (setWay(map,i + c1, j + c2, order))
                    //往上走
                    return true;
                else if (setWay(map,i + d1, j + d2, order))
                    //往左走
                    return true;
                else{
                    //3表示此路不通, 这里仅在不通的路中设置了不通点
                    map[i][j] = 3;
                    return false;
                }
            }
            else
                //表示改点无法走动
                return false;
        }
    }
}
