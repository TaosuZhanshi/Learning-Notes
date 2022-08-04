import org.junit.jupiter.api.Test;

public class test731 {
    public test731(){
        root = new Node();
    }

    public boolean book(int start, int end) {
        if(query(root, 0, N, start, end - 1) == 2) return false;
        update(root, 0, N, start, end - 1);
        return true;
    }

    private int N = (int) 1e9;
    private Node root;

    class Node{
        Node left, right;
        int add, val;
    }
    public void update(Node cur, int start, int end, int left, int right){
        if(start >= left && end <= right){
            cur.val += 1;
            cur.add += 1;
            return ;
        }
        downPush(cur);
        int mid = (start + end) >> 1;
        if(left <= mid) update(cur.left, start, mid, left, right);
        if(right > mid) update(cur.right, mid + 1, end, left, right);
        upPush(cur);
    }

    public int query(Node cur, int start, int end, int left, int right){
        if(start >= left && end <= right){
            return cur.val;
        }
        downPush(cur);
        int mid = (start + end) >> 1, ans = 0;
        if(left <= mid) ans = query(cur.left, start, mid, left, right);
        if(right > mid) ans = Math.max(query(cur.right, mid + 1, end, left, right), ans);
        return ans;
    }

    public void downPush(Node cur){
        if(cur.left == null){
            cur.left = new Node();
        }
        if(cur.right == null){
            cur.right = new Node();
        }
        if(cur.add == 0){
            return ;
        }
        cur.left.val += cur.add;
        cur.right.val += cur.add;
        cur.left.add += cur.add;
        cur.right.add += cur.add;
        cur.add = 0;
    }

    public void upPush(Node cur){
        cur.val = Math.max(cur.left.val, cur.right.val);
    }

    @Test
    public void testFor731(){
        test731 test = new test731();
        System.out.println(test.book(10, 20));
        System.out.println(test.book(50, 60));
        System.out.println(test.book(10, 40));
    }
}
