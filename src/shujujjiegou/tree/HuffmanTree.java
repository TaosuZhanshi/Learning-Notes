package shujujjiegou.tree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class HuffmanTree
{
    public static void main(String[] args)
    {
        int[] arr = {13,7,8,3,29,6,1};
        preOrder(creatHuffmanTree(arr));
    }

    public static void preOrder(Node root){
        if(root != null){
            root.preOrder();
        }
        else {
            System.out.println("您所想遍历的树为空");
        }
    }

    public static Node creatHuffmanTree(int[] arr)
    {
        var nodes = new ArrayList<Node>();
        for(int node : arr)
        {
            nodes.add(new Node(node));
        }

        while(nodes.size() > 1) {
            Collections.sort(nodes);

            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);

            Node parent = new Node(leftNode.number + rightNode.number);
            parent.left = leftNode;
            parent.right = rightNode;

            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parent);
        }
        return nodes.get(0);

    }
}

class Node implements Comparable<Node>{

    public int number;
    public Node left;
    public Node right;

     public Node(int number)
     {
         this.number = number;
     }

     public void preOrder()
     {
         System.out.println(this);
         if(this.left != null)
         {
             this.left.preOrder();
         }
         if(this.right != null)
         {
             this.right.preOrder();
         }
     }

    @Override
    public String toString() {
        return "Node{" +
                "number=" + number +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return number - o.number;
    }
}
