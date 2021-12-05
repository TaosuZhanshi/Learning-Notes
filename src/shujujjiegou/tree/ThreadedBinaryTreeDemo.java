package shujujjiegou.tree;

public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        ThreadedHeroNode node1 = new ThreadedHeroNode(1, "TOM");
        ThreadedHeroNode node2 = new ThreadedHeroNode(2, "Jack");
        ThreadedHeroNode node3 = new ThreadedHeroNode(3, "Rose");
        ThreadedHeroNode node4 = new ThreadedHeroNode(4, "Five");
        ThreadedHeroNode node5 = new ThreadedHeroNode(5, "Six");
        ThreadedHeroNode node6 = new ThreadedHeroNode(6, "Even");

        var threadedBinaryTree = new ThreadedBinaryTree();

        threadedBinaryTree.setroot(node1);

        node1.setLeftNode(node2);
        node1.setRightNode(node3);
        node2.setLeftNode(node4);
        node2.setRightNode(node5);
        node3.setLeftNode(node6);

        threadedBinaryTree.threadedNodes(threadedBinaryTree.getRoot());

        ThreadedHeroNode leftNode = node5.getLeftNode();
        System.out.println("5节点的前驱节点是 " + leftNode);
        ThreadedHeroNode rightNode = node5.getRightNode();
        System.out.println("5节点的前驱节点是 " + rightNode);

        System.out.println("使用线索化遍历： ");
        threadedBinaryTree.threadedList();
    }
}

class ThreadedHeroNode {
    private int no;
    private String name;
    private ThreadedHeroNode leftNode;
    private ThreadedHeroNode rightNode;
    private int lefttype;
    private int righttype;


    public ThreadedHeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getLefttype() {
        return lefttype;
    }

    public void setLefttype(int lefttype) {
        this.lefttype = lefttype;
    }

    public int getRighttype() {
        return righttype;
    }

    public void setRighttype(int righttype) {
        this.righttype = righttype;
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

    public ThreadedHeroNode getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(ThreadedHeroNode leftNode) {
        this.leftNode = leftNode;
    }

    public ThreadedHeroNode getRightNode() {
        return rightNode;
    }

    public void setRightNode(ThreadedHeroNode rightNode) {
        this.rightNode = rightNode;
    }

    public String toString() {
        return "node id = " + no + ", name = " + name;
    }

    //前序遍历
    public void preOrder() {
        System.out.println(this.toString());
        if (this.leftNode != null) {
            this.leftNode.preOrder();
        }
        if (this.rightNode != null) {
            this.rightNode.preOrder();
        }
    }

    //中序遍历
    public void infixOrder() {
        if (this.leftNode != null) {
            this.leftNode.infixOrder();
        }
        System.out.println(this.toString());
        if (this.rightNode != null) {
            this.rightNode.infixOrder();
        }
    }

    //后序遍历
    public void postOrder() {
        if (this.leftNode != null) {
            this.leftNode.postOrder();
        }
        if (this.rightNode != null) {
            this.rightNode.postOrder();
        }
        System.out.println(this.toString());
    }

    public ThreadedHeroNode preOrderSearch(int no) {
        if (this.getNo() == no) {
            return this;
        }

        ThreadedHeroNode temp = null;

        if (this.getLeftNode() != null) {
            temp = this.leftNode.preOrderSearch(no);
        }

        if (temp != null) {
            return temp;
        }

        if (this.getRightNode() != null) {
            temp = this.rightNode.preOrderSearch(no);
        }

        return temp;
    }

    public ThreadedHeroNode infixOrderSearch(int no) {
        ThreadedHeroNode temp = null;
        if (this.getLeftNode() != null) {
            temp = this.getLeftNode().infixOrderSearch(no);
        }
        if (temp != null) {
            return temp;
        }
        if (this.getNo() == no) {
            return this;
        }
        if (this.getRightNode() != null) {
            temp = this.getRightNode().infixOrderSearch(no);
        }
        return temp;
    }

    public ThreadedHeroNode postOrderSearch(int no) {
        ThreadedHeroNode temp = null;
        if (this.getLeftNode() != null) {
            temp = this.getLeftNode().postOrderSearch(no);
        }
        if (temp != null) {
            return temp;
        }
        if (this.getRightNode() != null) {
            temp = this.getRightNode().postOrderSearch(no);
        }
        if (temp != null) {
            return temp;
        }
        if (this.getNo() == no) {
            return this;
        }
        return temp;
    }

    public void delete(int no) {
        if (this.getLeftNode() != null && this.getLeftNode().getLeftNode() == null
                && this.getLeftNode().getRightNode() == null && this.getLeftNode().getNo() == no)
        //如果左分支为想找的叶节点，那么直接将左节点删去
        {
            this.setLeftNode(null);
            return;
        }

        if (this.getRightNode() != null && this.getRightNode().getLeftNode() == null
                && this.getRightNode().getRightNode() == null && this.getRightNode().getNo() == no)
        //如果右分支为想找的叶节点，那么直接将右节点删去
        {
            this.setRightNode(null);
            return;
        }

        if (this.getLeftNode() != null) {
            this.getLeftNode().delete(no);
        }

        if (this.getRightNode() != null) {
            this.getRightNode().delete(no);
        }
    }


}


class ThreadedBinaryTree {
    private ThreadedHeroNode root;

    //设定线索化，设定一个变量指向前向节点
    private ThreadedHeroNode pre = null;

    public void setroot(ThreadedHeroNode root) {
        this.root = root;
    }

    public ThreadedHeroNode getRoot() {
        return root;
    }

    //中序线索化节点
    public void threadedNodes(ThreadedHeroNode node) {
        if (node == null) {
            return;
        }

        //（1）先线索化左子节点
        threadedNodes(node.getLeftNode());

        //（2）线索化此节点
        if (node.getLeftNode() == null) {
            node.setLeftNode(pre);
            node.setLefttype(1);
        }

        if (pre != null && pre.getRightNode() == null) {
            pre.setRightNode(node);
            pre.setRighttype(1);
        }

        //保留此次实际线索化的节点以备下次使用
        pre = node;

        //（3）线索化后继节点
        threadedNodes(node.getRightNode());

    }

    public void threadedList(){
        ThreadedHeroNode temp = root;
        while(temp != null)
        {
            while(temp.getLefttype() != 1){
                temp = temp.getLeftNode();
            }
            //打印此节点
            System.out.println(temp);

            while(temp.getRighttype() == 1) {
                //更新temp到后继节点
                temp = temp.getRightNode();
                System.out.println(temp);
            }
            temp = temp.getRightNode();
        }
    }

    public void add(ThreadedHeroNode node) {
        ThreadedHeroNode temp = root;
        while (true) {
            if (node.getNo() < temp.getNo()) {
                if (temp.getLeftNode() != null) {
                    temp = temp.getLeftNode();
                } else {
                    temp.setLeftNode(node);
                    break;
                }
            } else if (node.getNo() > temp.getNo()) {
                if (temp.getRightNode() != null) {
                    temp = temp.getRightNode();
                } else {
                    temp.setRightNode(node);
                    break;
                }
            } else {
                System.out.println("您所想添加的元素在树中已存在，其为" + temp.toString());
                break;
            }
        }

    }

    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("此二叉树为空");
        }
    }

    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("此二叉树为空");
        }
    }

    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("您所想查找的树为空");
        }


    }

    public ThreadedHeroNode preOrderSearch(int no) {
        if (this.root != null) {
            return this.root.preOrderSearch(no);
        } else {
            return null;
        }
    }

    public ThreadedHeroNode infixOrderSearch(int no) {
        if (this.root != null) {
            return this.root.infixOrderSearch(no);
        } else {
            return null;
        }
    }

    public ThreadedHeroNode postOrderSearch(int no) {
        if (this.root != null) {
            return this.root.postOrderSearch(no);
        } else {
            return null;
        }
    }

    public void delete(int no) {
        if (root == null) {
            System.out.println("空树不能删除");
            return;
        } else if (root.getNo() == no) {
            setroot(null);
            return;
        } else {
            root.delete(no);
        }
    }


}