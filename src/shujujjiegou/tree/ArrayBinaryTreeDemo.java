package shujujjiegou.tree;

public class ArrayBinaryTreeDemo
{
    public static void main(String[] args)
    {
        int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(arr);

        System.out.println("前序遍历结果：");
        arrayBinaryTree.preOrderList();

        System.out.println("中序遍历结果: ");
        arrayBinaryTree.infixOrderList();

        System.out.println("后序遍历结果: ");
        arrayBinaryTree.postOrderList();

    }

}

class ArrayBinaryTree
{
    private int[] arr;

    public ArrayBinaryTree(int[] arr) {
        this.arr = arr;
    }

    public void preOrderList()
    {
        preOrderList(0);
    }

    public void infixOrderList()
    {
        infixOrderList(0);
    }

    public void postOrderList()
    {
        postOrderList(0);
    }

    public void preOrderList(int index)
    {
        if(arr == null || arr.length == 0)
        {
            System.out.println("您所想遍历的树为空");
            return;
        }

        System.out.println(arr[index]);

        if((index * 2 + 1) < arr.length)
        {
            preOrderList(index * 2 + 1);
        }
        if((index * 2 + 2) < arr.length)
        {
            preOrderList(index * 2 + 2);
        }



    }

    public void infixOrderList(int index)
    {
        if(arr == null || arr.length == 0)
        {
            System.out.println("您所想遍历的树为空");
            return;
        }

        if((index * 2 + 1) < arr.length)
        {
            infixOrderList( index * 2 + 1);
        }

        System.out.println(arr[index]);

        if((index * 2 + 2) < arr.length)
        {
            infixOrderList( index * 2 + 2);
        }
    }

    public void postOrderList(int index)
    {
        if(arr == null || arr.length == 0)
        {
            System.out.println("您所想遍历的数为空");
            return;
        }
        if((index * 2 + 1) < arr.length)
        {
            postOrderList(index * 2 + 1);
        }
        if((index * 2 + 2) < arr.length)
        {
            postOrderList( index * 2 + 2);
        }
        System.out.println(arr[index]);
    }
}
