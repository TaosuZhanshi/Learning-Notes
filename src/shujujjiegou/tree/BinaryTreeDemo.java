package shujujjiegou.tree;

public class BinaryTreeDemo
{
    public static void main(String[] args)
    {
        BinaryTree binaryTree= new BinaryTree();

        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node1 = new HeroNode(2, "吴用");
        HeroNode node2 = new HeroNode(3, "卢俊义");
        HeroNode node3 = new HeroNode(4, "林冲");
        HeroNode node4 = new HeroNode(5, "武松");

        binaryTree.setroot(root);
        root.setLeftNode(node1);
        root.setRightNode(node2);
        node2.setLeftNode(node3);
        node2.setRightNode(node4);

        System.out.println("前序遍历");
        binaryTree.preOrder();

        System.out.println("中序遍历");
        binaryTree.infixOrder();

        System.out.println("后序遍历");
        binaryTree.postOrder();

        System.out.println("前序查找id为3的node： ");
        System.out.println(binaryTree.preOrderSearch(3).toString());

        System.out.println("中序查找id为4的node:  ");
        System.out.println(binaryTree.infixOrderSearch(4).toString());

        System.out.println("后序查找id为2的node： ");
        System.out.println(binaryTree.postOrderSearch(2).toString());

        System.out.println("删除前后序遍历树");
        binaryTree.postOrder();

        binaryTree.delete(4);

        System.out.println("删除后后序遍历树");
        binaryTree.postOrder();


    }
}

class BinaryTree
{
    private HeroNode root;

    public void setroot(HeroNode root) {
        this.root = root;
    }

    public HeroNode getBoot() {
        return root;
    }


    public void add(HeroNode node)
    {
        HeroNode temp = root;
        while(true)
        {
            if(node.getNo() < temp.getNo())
            {
                if(temp.getLeftNode() != null)
                {
                    temp = temp.getLeftNode();
                }
                else
                {
                    temp.setLeftNode(node);
                    break;
                }
            }
            else if(node.getNo() > temp.getNo())
            {
                if(temp.getRightNode() != null)
                {
                    temp = temp.getRightNode();
                }
                else
                {
                    temp.setRightNode(node);
                    break;
                }
            }
            else
            {
                System.out.println("您所想添加的元素在树中已存在，其为" + temp.toString());
                break;
            }
        }

    }

    public void preOrder()
    {
         if(this.root != null)
         {
             this.root.preOrder();
         }
         else
         {
             System.out.println("此二叉树为空");
         }
     }

    public void infixOrder()
    {
        if(this.root != null)
        {
            this.root.infixOrder();
        }
        else
        {
            System.out.println("此二叉树为空");
        }
    }

    public void postOrder()
    {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("您所想查找的树为空");
        }


    }

    public HeroNode preOrderSearch(int no)
    {
        if(this.root != null)
        {
            return this.root.preOrderSearch(no);
        }
        else
        {
            return null;
        }
    }

    public HeroNode infixOrderSearch(int no)
    {
        if(this.root != null)
        {
            return this.root.infixOrderSearch(no);
        }
        else
        {
            return null;
        }
    }

    public HeroNode postOrderSearch(int no)
    {
        if(this.root != null)
        {
            return this.root.postOrderSearch(no);
        }
        else
        {
            return null;
        }
    }

    public void delete(int no)
    {
        if(root == null)
        {
            System.out.println("空树不能删除");
            return;
        }
        else if(root.getNo() == no)
        {
            setroot(null);
            return;
        }
        else
        {
            root.delete(no);
        }
    }


}



class HeroNode
{
    private int no;
    private String name;
    private HeroNode leftNode;
    private HeroNode rightNode;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(HeroNode leftNode) {
        this.leftNode = leftNode;
    }

    public HeroNode getRightNode() {
        return rightNode;
    }

    public void setRightNode(HeroNode rightNode) {
        this.rightNode = rightNode;
    }

    public String toString()
    {
        return "node id = " + no + ", name = " + name;
    }

    //前序遍历
    public void preOrder()
    {
        System.out.println(this.toString());
        if(this.leftNode != null)
        {
            this.leftNode.preOrder();
        }
        if(this.rightNode != null)
        {
            this.rightNode.preOrder();
        }
    }

    //中序遍历
    public void infixOrder()
    {
        if(this.leftNode != null)
        {
            this.leftNode.infixOrder();
        }
        System.out.println(this.toString());
        if(this.rightNode != null)
        {
            this.rightNode.infixOrder();
        }
    }

    //后序遍历
    public void postOrder()
    {
        if(this.leftNode != null)
        {
            this.leftNode.postOrder();
        }
        if(this.rightNode != null)
        {
            this.rightNode.postOrder();
        }
        System.out.println(this.toString());
    }

    public  HeroNode preOrderSearch(int no)
    {
        if(this.getNo() == no)
        {
            return this;
        }

        HeroNode temp = null;

        if(this.getLeftNode() != null)
        {
            temp = this.leftNode.preOrderSearch(no);
        }

        if(temp != null)
        {
            return temp;
        }

        if(this.getRightNode() != null)
        {
            temp = this.rightNode.preOrderSearch(no);
        }

        return temp;
    }

    public HeroNode infixOrderSearch(int no)
    {
        HeroNode temp = null;
        if(this.getLeftNode() != null)
        {
            temp = this.getLeftNode().infixOrderSearch(no);
        }
        if(temp != null)
        {
            return temp;
        }
        if(this.getNo() == no)
        {
            return this;
        }
        if(this.getRightNode() != null)
        {
            temp = this.getRightNode().infixOrderSearch(no);
        }
        return temp;
    }

    public HeroNode postOrderSearch(int no)
    {
        HeroNode temp = null;
        if(this.getLeftNode() != null)
        {
            temp = this.getLeftNode().postOrderSearch(no);
        }
        if(temp != null)
        {
            return temp;
        }
        if(this.getRightNode() != null)
        {
            temp = this.getRightNode().postOrderSearch(no);
        }
        if(temp != null)
        {
            return temp;
        }
        if(this.getNo() == no)
        {
            return this;
        }
        return temp;
    }

    public void delete(int no)
    {
        if(this.getLeftNode() != null && this.getLeftNode().getLeftNode() == null
                && this.getLeftNode().getRightNode() == null && this.getLeftNode().getNo() == no)
        //如果左分支为想找的叶节点，那么直接将左节点删去
        {
            this.setLeftNode(null);
            return;
        }

        if(this.getRightNode() != null && this.getRightNode().getLeftNode() == null
        && this.getRightNode().getRightNode() == null && this.getRightNode().getNo() ==no)
        //如果右分支为想找的叶节点，那么直接将右节点删去
        {
            this.setRightNode(null);
            return;
        }

        if(this.getLeftNode() != null)
        {
            this.getLeftNode().delete(no);
        }

        if(this.getRightNode() != null)
        {
            this.getRightNode().delete(no);
        }
    }


}
