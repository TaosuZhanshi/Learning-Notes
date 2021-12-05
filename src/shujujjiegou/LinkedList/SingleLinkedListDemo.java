package shujujjiegou.LinkedList;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        var node1 = new HeroNode(1, "宋江", "及时雨");
        var node2 = new HeroNode(2, "吴用", "智多星");
        var node3 = new HeroNode(3, "林冲", "豹子头");
        var node4 = new HeroNode(4, "卢俊义", "玉麒麟");

        var djxllmbn = new SingleLinkedList();
        djxllmbn.add(node4);
        djxllmbn.add(node2);

        djxllmbn.showList();
        System.out.println("");

        System.out.println("加入新节点");
        djxllmbn.add(node1);
        djxllmbn.showList();
        System.out.println("");

        System.out.println("链表节点数为 " + djxllmbn.getLength());
        System.out.println("");

        //反转链表
        djxllmbn.convert();
        System.out.println("反转后的链表为");
        djxllmbn.showList();

        //反向打印链表
        System.out.println("逆序打印列表");
        djxllmbn.reversedPrint();
        System.out.println("");

        //取出链表中的一个节点（以no值作为参考）
        djxllmbn.convert();
        System.out.println("取出排名为3节点");
        System.out.println(djxllmbn.pickout(3));
        System.out.println("取完后的链表为");
        djxllmbn.showList();
        System.out.println("");

        //取出排名最小的一个节点
        System.out.println("取出排名最小的一个节点为");
        System.out.println(djxllmbn.pickoutmin());
        System.out.println("取完后的链表为");
        djxllmbn.showList();
        System.out.println("");

        //取出排名最大的一个节点
        System.out.println("取出排名最大的一个节点为");
        System.out.println(djxllmbn.pickoutmax());
        System.out.println("取完后的链表为");
        djxllmbn.showList();
        System.out.println("");

        var node5 = new HeroNode(5, "宋江", "及时雨");
        var node6 = new HeroNode(6, "吴用", "智多星");
        var node7 = new HeroNode(7, "林冲", "豹子头");
        var node8 = new HeroNode(8, "卢俊义", "玉麒麟");
        var node9 = new HeroNode(9, "公孙胜", "入云龙");
        var node10 = new HeroNode(10, "关胜", "大刀");

        var djxllmbn2 = new SingleLinkedList();
        djxllmbn2.add(node5);
        djxllmbn2.add(node8);
        djxllmbn2.add(node7);

        var djxllmbn3 = new SingleLinkedList();
        djxllmbn3.add(node10);
        djxllmbn3.add(node9);
        djxllmbn3.add(node6);


        //对链表进行排序
        System.out.println("排序后的链表为");
        djxllmbn2.sort();
        djxllmbn2.showList();

        System.out.println("");

        //对链表进行合并
        SingleLinkedList djxllmbn4 = new SingleLinkedList();
        System.out.println("合并后的链表为");
        djxllmbn4 = djxllmbn2.combine(djxllmbn3);
        djxllmbn4.showList();
        System.out.println("");


        System.out.println("");
    }
}
