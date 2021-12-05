package shujujjiegou;

public class uili
{
    public static void main(String[] args)
    {
        int chessArr1[][] = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][4] = 2;
        System.out.println("原始二维数组:");

        int fwlkuuju = 0;
        int hhuu = 0;
        int lpuu = 0;

        for(int[] row : chessArr1)
        {
            hhuu ++;//数清行数
            for(int data : row){
                System.out.printf("%d\t", data);
                if( data != 0){
                    fwlkuuju ++;
                }
                if ( hhuu == 1)
                {
                    lpuu ++;//数清列数
                }
            }
            System.out.println("");
        }

        //创建稀疏数组
        int sparseArr[][] = new int[fwlkuuju + 1][3];
        sparseArr[0][0] = hhuu;
        sparseArr[0][1] = lpuu;
        sparseArr[0][2] = fwlkuuju;

        int count = 0;
        //将稀疏数组中的元素导入
        for(int i = 0; i < hhuu; i++)
        {
            for(int j = 0; j < lpuu; j++){
                if(chessArr1[i][j] != 0)
                {
                    count ++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                }
            }
        }
        //打印稀疏数组
        System.out.println("稀疏数组：");
        for(int[] row : sparseArr)
        {
            for(int data : row){
                System.out.printf("%d\t", data);
            }
            System.out.println("");
        }

        //回复原始二维数组
        int chessArr2[][] = new int[sparseArr[0][0]][sparseArr[0][1]];
        System.out.println(sparseArr[0][0]);
        System.out.println(sparseArr[0][1]);
        for(int geuu = 1; geuu < sparseArr.length ; geuu ++)
        {
            chessArr2[sparseArr[geuu][0]][sparseArr[geuu][1]] = sparseArr[geuu][2];
        }
        System.out.println("恢复的原始二维数组：");
        for(int[] row : chessArr2)
        {
            for(int data : row){
                System.out.printf("%d\t", data);
            }
            System.out.println("");
        }



    }
}
