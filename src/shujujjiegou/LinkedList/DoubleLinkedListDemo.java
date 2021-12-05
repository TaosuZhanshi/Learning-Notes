package shujujjiegou.LinkedList;

public class DoubleLinkedListDemo
{
    public static void main(String[] args)
    {
        var node1 = new HeroNode2(1, "宋江", "及时雨");
        var node2 = new HeroNode2(2, "吴用", "智多星");
        var node3 = new HeroNode2(3, "林冲", "豹子头");
        var node4 = new HeroNode2(4, "卢俊义", "玉麒麟");

        var djxllmbn = new DoubleLinkedList();
        djxllmbn.add(node4);
        djxllmbn.add(node2);
        djxllmbn.add(node3);
        djxllmbn.add(node1);

        djxllmbn.sort();
        djxllmbn.showList();
        System.out.println("取出排名最小的节点为");
        System.out.println(djxllmbn.pickoutmin());
    }
}
