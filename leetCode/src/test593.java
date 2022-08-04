import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class test593 {
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        int[][] corner = new int[4][2];
        corner[0] = p1; corner[1] = p2; corner[2] = p3; corner[3] = p4;
        Arrays.sort(corner, (a, b) -> {
            if(a[0] == b[0]){
                return b[1] - a[1];
            }else{
                return a[0] - b[0];
            }
        });
        if(isVertical(corner[0], corner[1], corner[2]) && isVertical(corner[3], corner[1], corner[2])
                && isVertical(corner[2], corner[0], corner[3])){
            return true;
        }
        return false;
    }

    public boolean isVertical(int[] mid, int[] left, int[] right){
        return ((left[1] - mid[1])*(right[1] - mid[1]) + (left[0] - mid[0])*(right[0] - mid[0])) == 0;
    }

    @Test
    public void Test(){
        System.out.println(validSquare(new int[]{3954, 5220}, new int[]{2244, 4028}, new int[]{2503, 5479}, new int[]{3695, 3769}));
    }
}
