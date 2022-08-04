package suanfa.paixu;

import java.util.*;

public class Test {

    static int[] zhiShu = new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29};


    public static void main (String[] args){
        Solution324 solution = new Solution324();
        int[] nums = {1, 5, 1, 1, 6, 4};
        solution.wiggleSort(nums);
    }
    public static int[] radixSort(int[] arr) {
        int lengthOfArr = arr.length;

        int[][] bucket = new int[10][lengthOfArr];
        int[] nBucketCount = new int[10];
        for (int i = 0; i < nBucketCount.length; i++) {
            nBucketCount[i] = lengthOfArr - 1;
        }
        int[] pBucketCount = new int[10];

        int yanSuanWei1 = 1;
        int yanSuanWei2 = 10;

        int digitOfElement = 0;

        boolean flag = true;

        while (flag) {
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

            if (pBucketCount[0] == nBucketCount[0] + 1) {
                flag = false;
            }

            for (int i = nBucketCount.length - 1; i >= 0; i--) {
                if (nBucketCount[i] != lengthOfArr - 1) {
                    for (int j = lengthOfArr - 1; j > nBucketCount[i]; j--) {
                        arr[index++] = bucket[i][j];
                    }
                    nBucketCount[i] = lengthOfArr - 1;
                }
            }
            for (int i = 0; i < pBucketCount.length; i++) {
                if (pBucketCount[i] != 0) {
                    for (int j = 0; j < pBucketCount[i]; j++) {
                        arr[index++] = bucket[i][j];
                    }
                    pBucketCount[i] = 0;
                }
            }
        }
        return arr;
    }

    public static boolean isPrime(int n){
        if(n == 29){
            return true;
        }
        int left = 0;
        int right = zhiShu.length - 1;
        int mid = 0;
        while(left < right){
            mid = left + (right - left) / 2;
            if(zhiShu[mid] == n){
                return true;
            }
            else if(zhiShu[mid] > n){
                right = mid;
            }
            else if(zhiShu[mid] < n){
                left = mid + 1;
            }
        }
        return false;
    }


}

class ListNode {
    int val;
    ListNode next;

    public ListNode(int val) {
        this.val = val;
    }

    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode newHead = reverseList(head.next);
        newHead.next = head;
        head.next = null;
        return newHead;
    }
}

class Solution {
    private Node[] cur;

    public Node connect(Node root) {
        if(root != null){
            dfs(root, 0);
            return root;
        }
        return null;
    }

    public void dfs (Node node, int k){
        if(node.left != null){
            dfs(node.left, k + 1);
            dfs(node.right, k + 1);
        }
        else{
            Node[] cur = new Node[k + 1];
            return ;
        }
        if(cur[k] != null){
            cur[k].next = node;
        }
        cur[k] = node;
    }

    public void main(String[] args){
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;

        node1 = this.connect(node1);
    }
}

class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node(int val) {
        this.val = val;
        }
}


class Solution1 {
    public static boolean searchMatrix(int[][] matrix, int target) {
        if((matrix == null) || (matrix[0][0] > target) || (matrix[matrix.length - 1][matrix[0].length - 1] < target))
        {
            return false;
        }
        int hangNum = matrix.length;
        int lieNum = matrix[0].length;
        int left = 0;
        int right = hangNum - 1;
        int mid = 0;
        if( hangNum != 1){
            //找到第一个>=target的
            while(left < right){
                mid = (left + right) / 2;
                if(target <= matrix[mid][0]){
                    right = mid;
                }
                else{
                    left = mid + 1;
                }
            }
        }
        if(matrix[right][0] == target){
            return true;
        }

        int searchHang = left - 1;
        if(matrix[right][0] < target){
            searchHang = right;
        }
        if(searchHang < 0){
            searchHang = 0;
        }
        left = 0;
        right = lieNum - 1;
        while(left <= right){
            mid = (left + right) / 2;
            if(matrix[searchHang][mid] == target)
            {
                return true;
            }
            else if(matrix[searchHang][mid] > target){
                right = mid - 1;
            }
            else{
                left = mid + 1;
            }
        }
        return false;

    }
}

class Solution2 {
    public static int findMin(int[] nums) {
        int l = 0;
        int r = nums.length - 1;
        while(l < r){
            int mid = (l + r) / 2;
            if(nums[r] >= nums[mid]){
                r = mid;
            }
            else{
                l = mid + 1;
            }
        }
        return nums[l];
    }
}


class Solution15 {
    public static List<List<Integer>> threeSum(int[] nums) {
        if(nums.length < 3 || nums == null){
            return null;
        }

        Arrays.sort(nums);
        List<List<Integer>> lists = new ArrayList<>();

        for(int i = 0; i < nums.length; i ++){
            if(nums[i] > 0) return lists;
            if(i > 0 && nums[i] == nums[i - 1]) continue;
            int L = i + 1;
            int R = nums.length - 1;
            while(L < R){
                int temp = nums[i] + nums[L] + nums[R];
                if(temp == 0){
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[L]);
                    list.add(nums[R]);
                    lists.add(list);
                    while(L < R && nums[R - 1] == nums[R]) R --;
                    while(L < R && nums[L + 1] == nums[L]) L ++;
                    R --;
                    L ++;
                }
                else if(temp > 0){
                    R --;
                }
                else{
                    L ++;
                }
            }

        }
        return lists;

    }
}

class Solution844 {
    public static boolean backspaceCompare(String s, String t) {
        Stack<String> sDengJia = new Stack<>();
        Stack<String> tDengJia = new Stack<>();
        for(int i = 0; i < s.length(); i ++){
            String sAtI = String.valueOf(s.charAt(i));
            if(sAtI.equals("#")){
                if(!sDengJia.empty()){
                    sDengJia.pop();
                }
            }
            else{
                sDengJia.push(sAtI);
            }
        }

        for(int i = 0; i < t.length(); i ++){
            String tAtI = String.valueOf(t.charAt(i));
            if(tAtI.equals("#")){
                if(!tDengJia.empty()){
                    tDengJia.pop();
                }
            }
            else{
                tDengJia.push(tAtI);
            }
        }

        if(sDengJia.size() == tDengJia.size()){
            while(!sDengJia.empty()){
                if(!sDengJia.pop().equals(tDengJia.pop())){
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}

class Solution844_2 {
    public static boolean backspaceCompare(String s, String t) {
        int sSkip = 0;
        int tSkip = 0;
        int i = s.length() - 1;
        int j = t.length() - 1;
        //这里一定要用或，不然可能最后一次判断入不了循环
        //比如     String s = "bbbextm";
        //        String t = "bbb#extm";
        // 的情况
        while(i >= 0 || j >= 0){
            while(i >= 0){
                if(s.charAt(i) == '#'){
                    sSkip ++;
                    i --;
                }
                else if(sSkip > 0){
                    i --;
                }
                else{
                    break;
                }
            }
            while( j >= 0){
                if(t.charAt(j) == '#'){
                    tSkip ++;
                    j --;
                }
                else if(tSkip > 0){
                    tSkip --;
                    j --;
                }
                else{
                    break;
                }
            }
            //经历上述过程，i和j两个指针分别指向了结果字符串的有效原始值上
            if( i >= 0 && j >= 0){
                if(s.charAt(i) != t.charAt(j)){
                    return false;
                }
            }
            else{
                if(i >= 0 || j >= 0){
                    return false;
                }
            }
            i --;
            j --;
        }
        return true;
    }
}

//非常经典的一道计算交区间的算法
class Solution986 {
    public static int[][] intervalIntersection(int[][] firstList, int[][] secondList) {

        List<int[]> temp = new ArrayList<>();
        int i = 0, j = 0;

        while(i < firstList.length && j < secondList.length){
            int low = Math.max(firstList[i][0], secondList[j][0]);
            int high = Math.min(firstList[i][1], secondList[j][1]);
            if(low <= high){
                temp.add(new int[]{low, high});
            }
            if(firstList[i][1] > secondList[j][1]){
                j ++;
            }
            else{
                i ++;
            }
        }
        return temp.toArray(new int[temp.size()][]);
    }
}

class Solution200 {
    public static int numIslands(char[][] grid) {
        int res = 0;
        int[][] count = new int[grid.length][grid[0].length];
        for(int i = 0; i < grid.length; i ++){
            for( int j = 0; j < grid[0].length; j ++){
                if(grid[i][j] == '1' && count[i][j] == 0){
                    res += 1;
                    count = countIslands(grid, count, i, j);
                }
            }
        }
        return res;
    }

    public static int[][] countIslands(char[][] grid, int[][] count, int i, int j){
        count[i][j] = 1;
        if(i + 1 < grid.length && grid[i + 1][j] == '1' && count[i + 1][j] == 0){
            count = countIslands(grid, count, i + 1, j);
        }
        if(i - 1 >= 0 && grid[i - 1][j] == '1' && count[i - 1][j] == 0){
            count = countIslands(grid, count, i - 1, j);
        }
        if(j + 1 < grid[0].length && grid[i][j + 1] == '1' && count[i][j + 1] == 0){
            count = countIslands(grid, count, i, j + 1);
        }
        if(j - 1 >= 0 && grid[i][j - 1] == '1' && count[i][j - 1] == 0){
            count = countIslands(grid, count, i , j - 1);
        }
        return count;
    }
}

//利用切比雪夫距离得出最优前途的点
class Solution1091 {
    int n = 0;
    int[] dx = {0, 0, -1, 1, -1, 1, -1, 1};
    int[] dy = {-1, 1, 0, 0, -1, -1, 1, 1};

    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;
        if (grid[0][0] != 0 || grid[n - 1][n - 1] != 0) {
            return -1;
        }
        if (n == 1) {
            return 1;
        }
        PriorityQueue<Node> pq = new PriorityQueue<Node>(Comparator.comparingInt(v -> v.f));
        grid[0][0] = 1;
        pq.offer(new Node(0, 0, 1));
        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            int x = curr.x;
            int y = curr.y;
            if (x == n - 1 && y == n - 1) return grid[x][y];
            for (int j = 0; j < 8; j++) {
                int newX = x + dx[j];
                int newY = y + dy[j];

                if (newX < 0 || newX > n - 1 || newY < 0 || newY > n - 1) {
                    continue;
                }
                //注意判断 grid[newX][newY] > grid[x][y] + 1
                if (grid[newX][newY] == 0 || grid[newX][newY] > grid[x][y] + 1) {
                    grid[newX][newY] = grid[x][y] + 1;
                    pq.offer(new Node(newX, newY, grid[newX][newY]));
                }
            }
        }
        return -1;
    }

    public class Node {
        int x, y, f;

        public Node(int x, int y, int step) {
            this.x = x;
            this.y = y;
            this.f = distance(x, y, step);
        }

        public int distance(int x, int y, int step) {
            return step + Math.max(n - x - 1, n - y - 1);
        }
    }
}

class Solution55 {
    //实时更新最右可达边界
    public static boolean canJump(int[] nums) {
        int rightEdge = 0, i = 0;
        while(i <= rightEdge){
            rightEdge = Math.max(i + nums[i ++], rightEdge);
            if(rightEdge >= nums.length - 1){
                return true;
            }
        }
        return false;
    }
}

class Solution45 {
    public static int jump(int[] nums) {
        return backTrack(nums, nums.length - 1, 0);
    }

    public static int backTrack(int[] nums, int target, int count){
        count ++;
        if(target == 0){
            return count;
        }
        target = canJump(nums, target);
        return backTrack(nums, target, count);
    }

    public static int canJump(int[] nums, int target){
        int rightEdge = 0, cur = 0;
        while(cur <= rightEdge){
            rightEdge = Math.max(rightEdge, cur + nums[cur ++]);
            if(rightEdge >= target){
                return cur - 1;
            }
        }
        return cur - 1;
    }
}

class Solution5 {
    public static String longestPalindrome(String s) {
        if(s == null || s.length() == 0 )
        {
            return "";
        }
        int start = 0, end = 0;
        for(int i = 0; i < s.length() - 1;i ++){
            int len1 = maxLength(s, i, i);
            int len2 = maxLength(s, i, i + 1);
            int len = Math.max(len1, len2);
            if(len > end - start){
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    public static int maxLength(String s, int start, int end){
        while(start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)){
            start ++;
            end ++;
        }
        return end - start - 1;
    }
}

class Solution1020 {
    public static int numEnclaves(int[][] grid) {
        for(int i = 0; i < grid.length; ++i){
            if(grid[i][0] == 1){
                findBianJie(grid, i, 0);
            }
            if(grid[i][grid[0].length - 1] == 1){
                findBianJie(grid, i, grid[0].length - 1);
            }
        }

        for(int i = 0; i < grid[0].length; ++i){
            if(grid[0][i] == 1){
                findBianJie(grid, 0, i);
            }
            if(grid[grid.length - 1][i] == 1){
                findBianJie(grid, grid.length - 1, i);
            }
        }
        int count = 0;
        for(int i = 1; i < grid.length - 1; i ++){
            for(int j = 1; j < grid[0].length - 1; j ++){
                if(grid[i][j] == 1){
                    ++ count;
                }
            }
        }
        return count;
    }

    public static void findBianJie(int[][] grid, int i, int j){
        grid[i][j] = 2;
        if(i + 1 < grid.length && grid[i + 1][j] == 1){
            findBianJie(grid, i + 1, j);
        }
        if(j + 1 < grid[0].length && grid[i][j + 1] == 1){
            findBianJie(grid, i, j + 1);
        }
        if(i - 1 >= 0 && grid[i - 1][j] == 1){
            findBianJie(grid, i - 1, j);
        }
        if(j - 1 >= 0 && grid[i][j - 1] == 1){
            findBianJie(grid, i, j - 1);
        }
    }
}

class Solution688 {
    int size = 0;
    int steps = 0;
    int failCount = 0;
    int successCount = 0;
    public double knightProbability(int n, int k, int row, int column) {
        size = n;
        steps = k;
        walk(1, row + 2, column + 1);
        walk(1, row - 2, column + 1);
        walk(1, row + 1, column + 2);
        walk(1, row - 1, column + 2);
        walk(1, row + 2, column - 1);
        walk(1, row - 2, column - 1);
        walk(1, row + 1, column - 2);
        walk(1, row - 1, column - 2);
        return successCount / (successCount + failCount);

    }
    public void walk(int cur, int row, int column){
        if(row >= size || column >= size || row < 0 || column < 0){
            ++ failCount;
            return ;
        }
        if(cur == steps){
            ++ successCount;
            return ;
        }
        walk(cur + 1, row + 2, column + 1);
        walk(cur + 1, row - 2, column + 1);
        walk(cur + 1, row + 1, column + 2);
        walk(cur + 1, row - 1, column + 2);
        walk(cur + 1, row + 2, column - 1);
        walk(cur + 1, row - 2, column - 1);
        walk(cur + 1, row + 1, column - 2);
        walk(cur + 1, row - 1, column - 2);
        return ;
    }
}

class Solution969 {
    public static List<Integer> pancakeSort(int[] arr) {
        int lengthOfArr = arr.length;
        List<Integer> res = new ArrayList<>();
        for(int i = lengthOfArr; i > 1; -- i){
            int index = max(arr, i);
            swep(arr, index);
            res.add(index);
            swep(arr, lengthOfArr);
            res.add(lengthOfArr);
        }
        return res;
    }

    public static void swep(int[] arr, int k){
        for(int i = 0; i < k / 2; ++i){
            int temp = arr[k - i - 1];
            arr[k - i - 1] = arr[i];
            arr[i] = temp;
        }
    }

    public static int max(int[] arr, int k){
        int maxValue = arr[0];
        int index = 0;
        for(int i = 1; i < k; ++i){
            if(arr[i] > maxValue){
                maxValue = arr[i];
                index = i;
            }
        }
        return index;
    }
}

class Solution838 {
    public static String pushDominoes(String dominoes) {
        StringBuilder res = new StringBuilder();
        int length = dominoes.length();
        for(int i = 0; i < length; ++i){
            int curPlace = i;
            char cur = dominoes.charAt(i);
            switch (cur){
                case '.':
                    ++ i;
                    while(i < length && dominoes.charAt(i) == '.'){
                        ++ i;
                    }
                    if(i == length){
                        for(int j = curPlace; j < i; j++){
                            res.append(".");
                        }
                    }
                    else if(dominoes.charAt(i) == 'L'){
                        for(int j = curPlace; j <= i; ++j){
                            res.append("L");
                        }
                    }
                    else if(dominoes.charAt(i) == 'R'){
                        for(int j = curPlace; j < i; ++j){
                            res.append(".");
                        }
                        -- i;
                    }
                    break;
                case 'R':
                    ++i;
                    while(i < length && dominoes.charAt(i) != 'L'){
                        if(dominoes.charAt(i) == 'R'){
                            for(int j = curPlace; j < i; j++){
                                res.append("R");
                            }
                            curPlace = i;
                        }
                        ++ i;
                    }
                    if( i == length){
                        for(int j = curPlace; j < i; j++){
                            res.append("R");
                        }
                    }
                    else if(dominoes.charAt(i) == 'L'){
                        if((i - curPlace) % 2 == 0){
                            for(int j = 0; j < (i - curPlace) / 2; ++j){
                                res.append("R");
                            }
                            res.append(".");
                            for(int j = 0; j < (i - curPlace) / 2; ++j){
                                res.append("L");
                            }
                        }
                        else{
                            for(int j = 0; j <= (i - curPlace) / 2; ++j){
                                res.append("R");
                            }
                            for(int j = 0; j <= (i - curPlace) / 2; ++j){
                                res.append("L");
                            }
                        }
                    }
                    break;
                case 'L':
                    res.append("L");
                    break;
            }
        }
        return res.toString();
    }
}

class Solution1706 {
    public static int[] findBall(int[][] grid) {
        Map<Integer, location> map = new HashMap<>();
        int[] res = new int[grid[0].length];
        for(int i = 0; i < grid[0].length; ++ i){
            map.put(i, new location(0, i));
        }
        for(int i = 1; i <= grid.length; ++i){
            for(int j = 0; j < grid[0].length; ++j){
                if(res[j] == -1){
                    continue;
                }
                location cur = map.get(j);
                if(grid[cur.row][cur.col] == 1){
                    if(cur.col == grid[0].length - 1 || grid[cur.row][cur.col + 1] == -1){
                        res[j] = -1;
                    }
                    else{
                        map.replace(j, cur, new location(cur.row + 1, cur.col + 1));
                    }
                }
                else{
                    if(cur.col == 0 || grid[cur.row][cur.col - 1] == 1){
                        res[j] = -1;
                    }
                    else{
                        map.replace(j, cur, new location(cur.row + 1, cur.col - 1));
                    }
                }
            }
        }
        for(int i = 0 ; i < grid[0].length; ++i){
            if(res[i] == - 1){
                continue;
            }
            location last = map.get(i);
            res[i] = last.col;
        }
        return res;
    }
}

class location{
    int row;
    int col;
    public location(int row, int col){
        this.row = row;
        this.col = col;
    }
}

class Solution537 {
    public static String complexNumberMultiply(String num1, String num2) {
        StringBuilder res = new StringBuilder();
        int[] left = new int[2];
        int[] right = new int[2];
        int Char = 1;
        boolean flag = true;
        int j = 0;
        for(int i = 0; i < num1.length() - 1; ++i){
            if(flag){
                if(num1.charAt(i) == '-'){
                    Char = -1;
                    ++i;
                }
                --i;
                flag = false;
            }
            else if(num1.charAt(i) == '+'){
                flag = true;
                left[j] *= Char;
                ++j;
                Char = 1;
            }
            else{
                left[j] = left[j] * 10 + (num1.charAt(i) - '0');
            }
        }
        left[j] *= Char;
        j = 0;
        Char = 1;
        flag = true;

        for(int i = 0; i < num2.length() - 1; ++i){
            if(flag){
                if(num2.charAt(i) == '-'){
                    Char = -1;
                    ++i;
                }
                --i;
                flag = false;
            }
            else if(num2.charAt(i) == '+'){
                flag = true;
                right[j] *= Char;
                ++j;
                Char = 1;
            }
            else{
                right[j] = right[j] * 10 + (num2.charAt(i) - '0');
            }
        }
        right[j] *= Char;

        int real = left[0] * right[0] - left[1] * right[1];
        int imag = left[1] * right[0] + left[0] * right[1];
        res.append(String.valueOf(real));
        res.append("+");
        res.append(String.valueOf(imag));
        res.append("i");
        return res.toString();
    }
}

class Solution564 {
    public String nearestPalindromic(String n) {

        List<Long> candidates = findCandidates(n);
        long res = -1, selfNum = Long.parseLong(n);
        for(long candidate : candidates){
            if(candidate != selfNum){
                if(res == -1 || Math.abs(selfNum - candidate) < Math.abs(selfNum - res) ||
                        Math.abs(selfNum - candidate) == Math.abs(selfNum - res) && candidate < res){
                    res = candidate;
                }
            }
        }
        return String.valueOf(res);
    }

    public List<Long> findCandidates(String n){
        int len = n.length();
        List<Long> candidates = new ArrayList<Long>(){{
            add((long) Math.pow(10, len - 1) - 1);
            add((long) Math.pow(10, len) + 1);
        }};
        long temp = Long.parseLong(n.substring(0, (len + 1) / 2));
        for(long i = temp - 1; i <= temp + 1; ++i){
            StringBuffer preHalf = new StringBuffer(String.valueOf(i));
            StringBuffer secHalf = new StringBuffer(preHalf).reverse();
            preHalf.append(secHalf.substring(len & 1));
            candidates.add(Long.parseLong(preHalf.toString()));
        }
        return candidates;
    }
}

class Solution2055 {
    public int[] platesBetweenCandles(String s, int[][] queries) {
        char[] charArr = s.toCharArray();
        int[] NumCount = new int[s.length()];
        int tempCount = 0;
        for(int i = 0; i < charArr.length; ++i){
            if(charArr[i] == '|'){
                tempCount += 1;
            }
            NumCount[i] = tempCount;
        }
        int[] res = new int[queries.length];
        for(int i = 0; i < queries.length; ++i){
            int NumCountInside = 0;
            int left = queries[i][0];
            int right = queries[i][1];
            if(charArr[left] == '|'){
                NumCountInside += 1;
            }
            NumCountInside += (NumCount[right] - NumCount[left]);
            while(charArr[left] != '|'){
                ++left;
            }
            while(charArr[right] != '|'){
                -- right;
            }
            if(left == right){
                continue;
            }
            res[i] = right - left + 1 -NumCountInside;
        }
        return res;
    }
}

class AllOne {

    // use hash alogirthm ensures O(1)
    private Map<String, Node> values;
    // use double-linked list ensures getMax or getMin O(1)
    private Node head;
    private Node tail;

    class Node {

        Node(Node pre, Node next, String key, int value) {
            this.pre = pre;
            this.value = value;
            this.next = next;
            this.key = key;
        }

        private Node pre;
        private Node next;
        private int value;
        private String key;
    }

    public AllOne() {
        this.values = new HashMap<>(8);
    }

    public void inc(String key) {
        Node node = this.values.get(key);
        if (node == null) {
            node = new Node(null, null, key, 1);
            values.put(key, node);
            if (head == null) {
                head = tail = node;
                return;
            }
            Node p = head;
            p.pre = node;
            node.next = p;
            head = node;
        } else {
            node.value++;
            while (node.next != null && node.next.value < node.value) {
                swapNode(node, node.next);
            }
        }
    }

    private void swapNode(Node node1, Node node2) {
        Node node1Pre = node1.pre;
        Node node2Next = node2.next;

        node1.pre = node2;
        node1.next = node2Next;
        node2.next = node1;
        node2.pre = node1Pre;
        if (node1Pre != null) {
            node1Pre.next = node2;
        }
        if (node2Next != null) {
            node2Next.pre = node1;
        }
        if (head == node1) {
            head = node2;
        }
        if (tail == node2) {
            tail = node1;
        }
    }


    public void dec(String key) {
        Node node = this.values.get(key);
        if (node == null) {
            return;
        } else if (node.value == 1) {
            if (node == head) {
                head = node.next;
            }
            if (tail == node) {
                tail = node.pre;
            }
            if (node.pre != null) {
                node.pre.next = node.next;
            }
            if (node.next != null) {
                node.next.pre = node.pre;
            }
        } else {
            node.value--;
            while (node.pre != null && node.pre.value > node.value) {
                swapNode(node.pre, node);
            }
        }
    }

    public String getMaxKey() {
        return tail == null ? "" : tail.key;
    }

    public String getMinKey() {
        return head == null ? "" : head.key;
    }

    /**
     * Your AllOne object will be instantiated and called as such:
     * AllOne obj = new AllOne();
     * obj.inc(key);
     * obj.dec(key);
     * String param_3 = obj.getMaxKey();
     * String param_4 = obj.getMinKey();
     */
}

class Solution2039 {

    public int networkBecomesIdle(int[][] edges, int[] patience) {
        List<Integer>[] list = new List[patience.length];
        for(int i = 0; i < patience.length; ++ i){
            list[i] = new ArrayList<Integer>();
        }
        boolean[] visited = new boolean[patience.length];
        for(int[] edge : edges){
            list[edge[0]].add(edge[1]);
            list[edge[1]].add(edge[0]);
        }
        int res = 0;
        Queue<Integer> queue = new ArrayDeque<Integer>();
        queue.offer(0);
        visited[0] = true;
        int distance = 1;
        while(!queue.isEmpty()){
            for(int i = 0; i < queue.size(); i++){
                int cur = queue.poll();
                for(int v : list[cur]){
                    if(visited[v]){
                        continue;
                    }
                    queue.offer(v);
                    int time = patience[v] * ((2 * distance - 1) / patience[v]) + 2 * distance + 1;
                    res = Math.max(res, time);
                    visited[v] = true;
                }
            }
            distance ++;
        }
        return res;
    }
}

class Solution172 {
    public int trailingZeroes(int m) {
        int numberOfFive = 0;
        int n = m;
        while(n / 5 != 0){
            n /= 5;
            numberOfFive ++;
        }
        int res = 0;
        if(numberOfFive != 0) {
            int maxNumber = (int) Math.pow(5, numberOfFive);
            int numberOfTen = 0;
            while (maxNumber * 2 <= m) {
                maxNumber *= 2;
                numberOfTen++;
            }
            for (int i = 2; i < numberOfFive; i++) {
                res += (i + 1) * (i - 1);
            }
            res += (numberOfTen + 1) * (numberOfFive - 1);
        }
        res += m / 5;
        return res;
    }
}

class Solution2028 {
    public int[] missingRolls(int[] rolls, int mean, int n) {
        int sum = 0;
        int[] res = new int[n];
        for(int roll : rolls){
            sum += roll;
        }
        int lackSum = (rolls.length + n) * mean - sum;
        if(lackSum > n * 6 || lackSum < n){
            return new int[0];
        }
        int meanLack = lackSum / n;
        int meanLast = lackSum % n;
        int j = 0;
        for(int i = 0; i < n; ++i){
            res[i] = meanLack + (j++ < meanLast ? 1 : 0);
        }
        return res;
    }
}

class Solution420 {
    public int strongPasswordChecker(String password) {
        int[] count = new int[4];
        //0~数字是否缺失， 1~数字需改变次数, 2~小写字母是否缺失，3~小写字母需改变次数， 4~大写字母是否缺失， 5~大写字母需改变次数,6~字符需修改数
        char[] passwords = password.toCharArray();
        boolean numFlag = true, chFlag = true, cHFlag = true;
        char pre = '?';int repetCount = 1;
        for(char word : passwords){
            if(word >= '0' && word <= '9'){
                if(numFlag){
                    count[0] = 1;
                    numFlag = false;
                }
                if(word == pre){
                    repetCount ++;
                }
                else{
                    count[1] += repetCount / 3;
                    repetCount = 1;
                }
                pre = word;
            }
            else if(word >= 'a' && word <= 'z'){
                if(chFlag){
                    count[2] = 1;
                    chFlag = false;
                }
                if(word == pre){
                    repetCount ++;
                }
                else{
                    count[1] += repetCount / 3;
                    repetCount = 1;
                }
                pre = word;
            }
            else if(word >= 'A' && word <= 'Z'){
                if(cHFlag){
                    count[3] = 1;
                    cHFlag = false;
                }
                if(word == pre){
                    repetCount ++;
                }
                else{
                    count[1] += repetCount / 3;
                    repetCount = 1;
                }
                pre = word;
            }
            else{
                if(word == pre){
                    repetCount ++;
                }
                else{
                    count[1] += repetCount / 3;
                    repetCount = 1;
                }
                pre = word;
            }
        }
        count[1] += repetCount / 3;
        int repeted = count[1], lacked = ((1 - count[0]) - (1 - count[2]) - (1 - count[3]));
        if(password.length() > 20){
            if((password.length() - 20) > repeted){
                return password.length() - 20 + lacked;
            }
            else{
                if(repeted >= lacked){
                    return repeted;
                }
                else{
                    return lacked;
                }
            }
        }
        else if(password.length() < 6){
            if(6 - password.length() > lacked){
                return 6 - password.length() + repeted;
            }
            else{
                if(lacked >= repeted){
                    return lacked;
                }
                else{
                    return repeted;
                }
            }
        }
        else{
            return Math.max(repeted , lacked);
        }
    }
}

class Solution744 {
    public char nextGreatestLetter(char[] letters, char target) {
        int n = letters.length;
        if(letters[n - 1] <= target || letters[0] > target){
            return letters[0];
        }
        int left = 0, right = n - 1;
        while(left < right){
            int mid = (left + right) >> 1;
            if(letters[mid] > target){
                right = mid;
            }
            else{
                left = mid + 1;
            }
        }
        return letters[right];
    }
}

class Solution479 {
    public int largestPalindrome(int n) {
        int maxInt = (int)Math.pow(10, 1) - 1;
        for(int i = maxInt; maxInt > 0; --i){
            int temp = i;
            long multi = i;
            while(temp != 0){
                multi = multi * 10 + temp % 10;
                temp /= 10;
            }
            for(int j = maxInt; j * j > multi; --j){
                if(multi % j == 0){
                    return (int) multi % 1337;
                }
            }
        }
        return 0;
    }
}

class Solution868 {
    public int binaryGap(int n) {
        String s = Integer.toBinaryString(n);
        int l = s.length();
        int count = 1, max = 0, i = 0;
        while(s.charAt(i) != '1' && i < l){
            ++i;
        }
        ++i;
        for(; i < l; ++i){
            if(s.charAt(i) == '0'){
                count ++;
                continue;
            }
            else{
                max = Math.max(max, count);
                count = 1;
            }
        }
        return max;
    }
}

class Solution12 {
    String[][] s = new String[][]{{"I","V","X"}, {"X", "L", "C"}, {"C", "D", "M"}, {"M"}};
    public String intToRoman(int num) {
        String res = "";
        int digit = 0, temp = num;
        while(temp > 0){
            int cur = temp % 10;
            if(cur % 5 == 4){
                if(cur > 5){
                    res = s[digit][0] + s[digit][2] + res;
                }
                else{
                    res = s[digit][0] + s[digit][1] + res;
                }
            }
            else if(cur == 5){
                res = s[digit][1] + res;
            }
            else if(cur > 5){
                for(int i = 5; i < cur; ++i){
                    res = s[digit][0] + res;
                }
                res = s[digit][1] + res;
            }
            else{
                for(int i = 0; i < cur; ++i){
                    res = s[digit][0] + res;
                }
            }
            digit ++;
            temp /= 10;
        }
        return res;
    }
}

class Solution417 {
    int[][] derection = new int[][]{{1,0}, {0, -1}, {-1, 0}, {0, 1}};

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int rN = heights.length, cN = heights[0].length;
        boolean[][] atlantic = new boolean[rN][cN];
        boolean[][] pacific = new boolean[rN][cN];
        for(int i = 0; i < cN; ++i){
            atlantic[rN - 1][i] = true;
            pacific[0][i] = true;
        }
        for(int i = 0; i < rN; ++i){
            atlantic[i][cN - 1] = true;
            pacific[i][0] = true;
        }

        for(int i = 0; i < cN; ++i){
            findWay(heights, atlantic, rN - 1, i);
            findWay(heights, pacific, 0, i);
        }
        for(int i = 0; i < rN; ++i){
            findWay(heights, atlantic, i, cN - 1);
            findWay(heights, pacific, i, 0);
        }
        List<List<Integer>> res = new ArrayList<>();
        for(int i = 0; i < rN; ++i){
            for(int j = 0; j < cN; ++j){
                if(atlantic[i][j] && pacific[i][j]){
                    List<Integer> temp = new ArrayList<>();
                    temp.add(i);temp.add(j);
                    res.add(temp);
                }
            }
        }
        return res;
    }

    public void findWay(int[][] heights, boolean[][] wayToOcean, int i, int j){
        for(int x = 0; x < 4; ++ x){
            int newi = i + derection[x][0], newj = j + derection[x][1];
            if(newi >=0 && newi < heights.length && newj >= 0 && newj < heights[0].length && heights[newi][newj] >= heights[i][j] && !wayToOcean[newi][newj]){
                wayToOcean[newi][newj] = true;
                findWay(heights, wayToOcean, newi, newj);
            }
        }
    }
}

class Solution20 {
    public boolean isValid(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        for(int i = 0; i < s.length(); ++i){
            int temp = convert(s.charAt(i));
            if(temp % 2 == 1){
                stack.addLast(temp);
            }
            else{
                if(stack.pollLast() != (temp - 1)){
                    return false;
                }
            }
        }
        if(stack.isEmpty()){
            return true;
        }
        return false;
    }

    public int convert(char ch){
        switch(ch){
            case '(' :
                return 1;
            case ')' :
                return 2;
            case '[' :
                return 3;
            case ']' :
                return 4;
            case '{' :
                return 5;
            case '}' :
                return 6;
        }
        return 0;
    }
}

class Solution1823 {
    public int findTheWinner(int n, int k) {
        List<Integer> list = new ArrayList<>();
        for(int i = 1; i <= n; ++i){
            list.add(i);
        }
        int cur = 0;
        while(list.size() != 1){
            int length = list.size();
            cur = (cur + k - 1) % length;
            list.remove(cur);
        }
        return list.get(0);
    }
}

class Solution1728 {
    static final int[][] DIRECTIONS = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public boolean canMouseWin(String[] grid, int catJump, int mouseJump) {
        int M = grid.length, N = grid[0].length();
        int mouse = 0, cat = 0, food = 0;
        for (int x = 0; x < M; ++x) {
            for (int y = 0; y < N; ++y) {
                char ch = grid[x].charAt(y);
                if (ch == 'M') mouse = x * N + y;
                else if (ch == 'C') cat = x * N + y;
                else if (ch == 'F') food = x * N + y;
            }
        }
        // mdp[i][j] 表示 老鼠和猫分别在 i,j 点时，老鼠的胜负状态
        // cdp[i][j] 表示 老鼠和猫分别在 i,j 点时，猫的胜负状态
        // 状态分三种 1必胜 -1必败 0未知
        int[][] mdp = new int[M * N][M * N], cdp = new int[M * N][M * N];
        // 初始化结束边界状态
        for (int i = 0; i < M * N; ++i) {
            if (i == food) continue;
            // 两者到达同一点，老鼠必败，猫必胜
            mdp[i][i] = -1;
            cdp[i][i] = 1;
            // 老鼠到达食物，老鼠必胜，猫必败
            mdp[food][i] = 1;
            cdp[food][i] = -1;
            // 猫到达食物，老鼠必败，猫必胜
            mdp[i][food] = -1;
            cdp[i][food] = 1;
        }
        // 时光倒流大法，从结束状态，往前转移，最终目标是求初始状态mdp[mouse][cat]是否必胜
        for (int round = 0; round < 1000; ++round) {
            boolean changed = false;// 标记这一轮是否有状态发生了改变
            for (int i = 0; i < M * N; ++i) {
                int mx = i / N, my = i % N;
                if (grid[mx].charAt(my) == '#') continue;
                for (int j = 0; j < M * N; ++j) {
                    int cx = j / N, cy = j % N;
                    if (grid[cx].charAt(cy) == '#') continue;
                    if (mdp[i][j] == 0) {// 老鼠的回合
                        boolean win = false, lose = true;
                        // 四个方向搜索相邻猫的状态，因为老鼠跳完了，就轮到猫了
                        // 根据相邻的猫的状态，转移当前老鼠到的状态
                        for (int[] d : DIRECTIONS) {
                            for (int jump = 0; jump <= mouseJump; ++jump) {
                                int mx2 = mx + d[0] * jump, my2 = my + d[1] * jump;
                                if (mx2 < 0 || mx2 >= M || my2 < 0 || my2 >= N) break;
                                if (grid[mx2].charAt(my2) == '#') break;
                                int k = mx2 * N + my2;
                                lose &= cdp[k][j] == 1;// 对方全部必胜，我们则必败
                                if (cdp[k][j] == -1) {// 对方只要有一个必败，我们则必胜
                                    win = true;
                                    break;
                                }
                            }
                            if (win) break;
                        }
                        mdp[i][j] = win ? 1 : lose ? -1 : 0;
                        if (win || lose) changed = true;
                    }
                    if (cdp[i][j] == 0) {// 猫的回合，与上面同理
                        boolean win = false, lose = true;
                        for (int[] d : DIRECTIONS) {
                            for (int jump = 0; jump <= catJump; ++jump) {
                                int cx2 = cx + d[0] * jump, cy2 = cy + d[1] * jump;
                                if (cx2 < 0 || cx2 >= M || cy2 < 0 || cy2 >= N) break;
                                if (grid[cx2].charAt(cy2) == '#') break;
                                int k = cx2 * N + cy2;
                                lose &= mdp[i][k] == 1;
                                if (mdp[i][k] == -1) {
                                    win = true;
                                    break;
                                }
                            }
                            if (win) break;
                        }
                        cdp[i][j] = win ? 1 : lose ? -1 : 0;
                        if (win || lose) changed = true;
                    }
                }
            }
            if (mdp[mouse][cat] == 1) return true;
            if (mdp[mouse][cat] == -1) return false;
            // 优化时间的关键点，200+ms --> 30+ms
            // 如果这一轮没有任何状态发生改变，那么没必要继续下一轮了，下一轮肯定还是不会变
            if (!changed) return false;
        }
        return false;// 跑满1000轮，实际上跑不满
    }
}

class Solution812 {
    public double largestTriangleArea(int[][] points) {
        double max = 0;
        for(int i = 0; i < points.length - 2; i ++){
            for(int j = i + 1; j < points.length - 1; ++j){
                for(int k = j + 1; k < points.length; ++k){
                    double s = Math.abs(0.5 * (points[i][0]*(points[j][1] - points[k][1]) + points[j][0] * (points[k][1] - points[i][1]) + points[k][0] * (points[i][1] - points[j][1])));
                    if(s > max){
                        max = s;
                    }
                }
            }
        }
        return max;
    }
}

class Solution436 {
    public int[] findRightInterval(int[][] intervals) {
        int n = intervals.length;
        int[][] source = Arrays.copyOf(intervals, n);
        int[] res = new int[n];
        Arrays.sort(intervals, (int[] a, int[] b) ->{
            return a[0] - b[0];
        });
        for(int i = 0; i < n; ++i){
            int left = 0, right = n - 1;
            while(left < right){
                int mid = left + (right - left) / 2;
                if(source[i][1] <= intervals[mid][0]){
                    right = mid;
                }
                else{
                    left = mid + 1;
                }
            }
            if(intervals[right][0] == source[i][1]){
                res[i] = right;
            }
            else{
                res[i] = -1;
            }
        }
        return res;
    }
}

class Solution467 {
    public int findSubstringInWraproundString(String p) {
        int left = 0, right = 1, n = p.length(), ans = 0;
        int[] checked = new int[26];
        checked[p.charAt(0) - 'a'] = 1;
        while(right < n){
            char r = p.charAt(right), rPre = p.charAt(right - 1);
            if(r == rPre + 1 || rPre == r + 25) {
                if(right - left + 1 > checked[r - 'a']){
                    checked[r - 'a'] = right - left + 1;
                }
                right ++;
            }
            else{
                left = right;
                right = left + 1;
                if(checked[p.charAt(left) - 'a'] == 0){
                    checked[p.charAt(left) - 'a'] = 1;
                }
            }
        }
        char rPre = p.charAt(right - 1);
        if(right - left > checked[rPre - 'a']){
            checked[rPre - 'a'] = right - left;
        }
        for(int i = 0; i < 26; ++i){
            ans += checked[i];
        }
        return ans;
    }
}

class Solution926 {
    public int minFlipsMonoIncr(String s) {
        int n = s.length(), res = Integer.MAX_VALUE, zeroCount = 0, oneCount = 0, preCount = 0;
        for(int i = 0; i < n; ++i){
            if(s.charAt(i) == '0'){
                zeroCount ++;
            }
            else{
                oneCount ++;
            }
        }
        for(int i = 0; i < n; ++i){
            if(s.charAt(i) == '0'){
                zeroCount --;
            }
            else{
                res = Math.min(res, zeroCount + preCount);
                res = Math.min(res, oneCount + preCount);
                preCount ++;
                oneCount --;
            }
        }
        return res;
    }
}

class RangeModule{

    public RangeModule() {

    }

    public void addRange(int left, int right) {
        // 1 表示覆盖；-1 表示取消覆盖
        update(root, 1, N, left, right - 1, 1);
    }

    public boolean queryRange(int left, int right) {
        return query(root, 1, N, left, right - 1);
    }

    public void removeRange(int left, int right) {
        // 1 表示覆盖；-1 表示取消覆盖
        update(root, 1, N, left, right - 1, -1);
    }

    // *************** 下面是模版 ***************
    class Node {
        Node left, right;
        // 表示当前区间是否被覆盖
        boolean cover;
        int add;
    }
    private int N = (int) 1e9;
    private Node root = new Node();
    public void update(Node node, int start, int end, int l, int r, int val) {
        if (l <= start && end <= r) {
            // 1 表示覆盖；-1 表示取消覆盖
            node.cover = val == 1;
            node.add = val;
            return ;
        }
        int mid = (start + end) >> 1;
        pushDown(node, mid - start + 1, end - mid);
        if (l <= mid) update(node.left, start, mid, l, r, val);
        if (r > mid) update(node.right, mid + 1, end, l, r, val);
        pushUp(node);
    }
    public boolean query(Node node, int start, int end, int l, int r) {
        if (l <= start && end <= r) return node.cover;
        int mid = (start + end) >> 1;
        pushDown(node, mid - start + 1, end - mid);
        // 查询左右子树是否被覆盖
        boolean ans = true;
        if (l <= mid) ans = ans && query(node.left, start, mid, l, r);
        if (r > mid) ans = ans && query(node.right, mid + 1, end, l, r);
        return ans;
    }
    private void pushUp(Node node) {
        // 向上更新
        node.cover = node.left.cover && node.right.cover;
    }
    private void pushDown(Node node, int leftNum, int rightNum) {
        if (node.left == null) node.left = new Node();
        if (node.right == null) node.right = new Node();
        if (node.add == 0) return ;
        node.left.cover = node.add == 1;
        node.right.cover = node.add == 1;
        node.left.add = node.add;
        node.right.add = node.add;
        node.add = 0;
    }
}

class Demo{
    public void demoPrint(){
        int n = 3;
        for(int i = 0; i < n; ++i) {
            char X = (char) ('A' + i);
            if (i != 0) {
                System.out.print("\n");
                for (int j = 0; j <= i; ++j) {
                    System.out.print(X);
                    System.out.print(" ");
                }
            } else {
                System.out.print(X);
            }
        }
    }

    public int[] bubble(int[] a){
        int n = a.length;
        for(int i = 0; i < n - 1; ++i){
            for(int j = i + 1; j < n; ++j) {
                if(a[j] < a[i]){
                    swap(a, i, j);
                }
            }
        }
        return a;
    }

    public void swap(int[] a, int i, int j){
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}

class Solution30 {
    List<Integer> res;
    int n, num;
    public List<Integer> findSubstring(String s, String[] words) {
        res = new ArrayList<>();
        n = words[0].length();
        num = words.length;
        Set<String> set = new HashSet<>();
        Collections.addAll(set, words);
        for(int i = 0; i + n * num <= s.length(); ++i){
            if(match(s.substring(i, i + n * num), set)){
                res.add(i);
            }
        }
        return res;
    }

    public boolean match(String s, Set<String> set){
        List<String> removed = new ArrayList<>();
        for(int i = 0; i < num; ++i){
            String temp = s.substring(i * n, (i + 1) * n);
            if(set.contains(temp)){
                set.remove(temp);
                removed.add(temp);
            }
            else{
                set.addAll(removed);
                return false;
            }
        }
        set.addAll(removed);
        return true;
    }
}

class Solution324 {
    public void wiggleSort(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int indexLeft = 0, indexRight = (n % 2) == 0 ? (n / 2) : (n / 2 + 1);
        int[] temp = new int[n];
        for(int i = 0; i < n; ++i){
            temp[i] = (i % 2) == 0 ? nums[indexLeft ++] : nums[indexRight ++];
        }
        nums = temp;
    }
}


