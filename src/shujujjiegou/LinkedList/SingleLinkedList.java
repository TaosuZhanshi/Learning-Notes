package shujujjiegou.LinkedList;

import java.util.LinkedList;
import java.util.Stack;

public class SingleLinkedList {
    private HeroNode head = new HeroNode(0, "", "");

    //往链表中添加新的节点
    public void add(HeroNode heroNode) {
        HeroNode temp = head;
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        temp.next = heroNode;
    }

    //遍历链表中的信息
    public void showList() {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = head.next;
        while (true) {
            if (temp == null)
                break;
            System.out.println(temp);
            //切记后移节点
            temp = temp.next;
        }
    }

    //通过判断节点的大小插入到适当位置，如果已存在当前的值则添加失败
    public void addByOrder(HeroNode heroNode) {
        HeroNode temp = head;
        boolean flag = true;
        while (true) {
            if (temp.next == null) {
                break;
            } else if (temp.next.no < heroNode.no) {
                temp = temp.next;
            } else if (temp.next.no > heroNode.no) {
                break;
            } else if (temp.next.no == heroNode.no) {
                flag = false;
                break;
            }
        }
        if (flag) {
            heroNode.next = temp.next;
            temp.next = heroNode;
        } else {
            System.out.printf("您想加入的节点排名为%d已存在", heroNode.no);
        }

    }

    //通过判断节点的大小插入到适当位置，如果已存在当前的值则添加失败
    public void updata(HeroNode heroNode) {
        HeroNode temp = head;
        boolean flag = true;
        while (true) {
            if (temp.next == null) {
                break;
            } else if (temp.next.no < heroNode.no) {
                temp = temp.next;
            } else if (temp.next.no > heroNode.no) {
                break;
            } else if (temp.next.no == heroNode.no) {
                flag = false;
                break;
            }
        }
        if (flag) {
            heroNode.next = temp.next;
            temp.next = heroNode;
        } else {
            heroNode.next = temp.next.next;
            temp.next = heroNode;
        }

    }

    //获取链表的有效长度
    public int getLength() {
        HeroNode temp = head;
        int length = 0;
        while(temp.next != null)
        {
            length ++;
            temp = temp.next;
        }
        return length;
    }

    //反转链表的所有节点
    public  void convert() {
        HeroNode converthead = new HeroNode(0,"", "");
        HeroNode temp = head.next;
        if(head.next == null || head.next.next == null )
            return ;
        while(temp != null)
        {
            HeroNode temp1 = temp.next;//记录当前节点的下一个节点
            temp.next = converthead.next;
            converthead.next = temp;
            temp = temp1; //将原来的链表中的指针指向原来链表中的下一个节点
        }
        head = converthead;
    }

    //逆序打印所有节点
    public  void reversedPrint() {
        var relinkedlist = new Stack<HeroNode>();
        HeroNode temp = head.next;
        if(temp == null)
            return;
        while(temp != null)
        {
            relinkedlist.push(temp);
            temp = temp.next;
        }
        while (relinkedlist.size() > 0)
        {
            System.out.println(relinkedlist.pop());
        }
    }

    //取出数据
    public HeroNode pickout(int number) {
        HeroNode temp = head;
        if(head.next == null){
            System.out.println("该链表为空");
            return head;
        }
        HeroNode picked = null;
        while(temp.next != null)
        {
            HeroNode last = temp;
            temp = temp.next;
            if(temp.no == number)
            {
                picked = temp;
                last.next = temp.next;
                picked.next = null;
                break;
            }
            if(temp.next == null)
                System.out.println("你所想查找的节点没有找到   ");
        }
        return picked;
    }

    //取出排名最小数
    public HeroNode pickoutmin(){
        HeroNode temp = head.next;
        HeroNode min = temp;
        if(temp == null)
        {
            System.out.println("此链表为空");
            return null;
        }
        while(temp != null)
        {
            if(min.no > temp.no)
            {
                min = temp;
            }
            temp = temp.next;
        }
        return this.pickout(min.no);
    }

    //取出排名最大数
    public HeroNode pickoutmax(){
        HeroNode temp = head.next;
        HeroNode max = temp;
        if(temp == null)
        {
            System.out.println("此链表为空");
            return null;
        }
        while(temp != null)
        {
            if(max.no < temp.no)
            {
                max = temp;
            }
            temp = temp.next;
        }
        return this.pickout(max.no);
    }

    //将节点排序
    public void sort() {
        HeroNode sorthead = new HeroNode(0, "", "");
        HeroNode temp = sorthead;
        if(head.next == null || head.next.next == null)
            return;
        while(head.next != null)
        {
            temp.next = this.pickoutmin();
            temp = temp.next;
        }
        temp.next = null;
        head = sorthead;
    }

    //合并两个有序的单链表，合并之后仍为有序的单链表
    public SingleLinkedList combine (SingleLinkedList other) {
        this.sort();
        other.sort();

        while(other.head.next != null)
        {
            this.addByOrder(other.pickoutmin());
        }
        return this;
    }


}

class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;

    //指向下一个节点
    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}