package shujujjiegou.LinkedList;

public class DoubleLinkedList extends SingleLinkedList
{
    private HeroNode2 head = new HeroNode2(0, "", "");

    //往链表中添加新的节点
    public void add(HeroNode2 heroNode) {
        HeroNode2 temp = head;
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        temp.next = heroNode;
        heroNode.pre = temp;
    }

    //遍历链表中的信息
    @Override
    public void showList() {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode2 temp = head.next;
        while (true) {
            if (temp == null)
                break;
            System.out.println(temp);
            //切记后移节点
            temp = temp.next;
        }
    }

    //取出、删除数据
    @Override
    public HeroNode2 pickout(int number) {
        HeroNode2 temp = head.next;
        if(head.next == null){
            System.out.println("该链表为空");
            return head;
        }
        HeroNode2 picked = null;
        while(temp != null)
        {
            if(temp.no == number)
            {
                temp.pre.next = temp.next;
                if(temp.next != null)
                    temp.next.pre = temp.pre;
                temp.next = null;
                temp.pre = null;
                break;
            }
            temp = temp.next;
        }
        if(temp == null)
            System.out.println("你所想查找的节点没有找到   ");
        return temp;
    }

    //取出排名最小数
    public HeroNode2 pickoutmin(){
        HeroNode2 temp = head.next;
        HeroNode2 min = temp;
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
    public HeroNode2 pickoutmax(){
        HeroNode2 temp = head.next;
        HeroNode2 max = temp;
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
        HeroNode2 sorthead = new HeroNode2(0, "", "");
        HeroNode2 temp = sorthead;
        if(head.next == null || head.next.next == null)
            return;
        while(head.next != null)
        {
            temp.next = this.pickoutmin();
            temp.next.pre = temp;
            temp = temp.next;
        }
        temp.next = null;
        head = sorthead;
    }

}

class HeroNode2 extends HeroNode
{
    public HeroNode2 next;
    public HeroNode2 pre;
    public HeroNode2(int no, String name, String nickname) {
        super(no, name, nickname);
    }
}
