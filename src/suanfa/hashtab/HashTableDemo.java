package suanfa.hashtab;

import java.util.Scanner;

public class HashTableDemo
{
    public static void main(String[] args)
    {
        String key = "";
        Scanner scanner = new Scanner(System.in);
        HashTable hashTable = new HashTable(7);
        while(true)
        {
            System.out.println("add: 添加雇员");
            System.out.println("list: 列出雇员");
            System.out.println("exit: 退出程序");
            key = scanner.next();
            switch (key)
            {
                case "add":
                    System.out.println("输入ID");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    Emp emp = new Emp(id, name);
                    hashTable.add(emp);
                    break;
                case "list":
                    hashTable.list();
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }

    }
}
class HashTable
{
    private EmpLinkedList[] empLinkedLists;
    private int size;

    public HashTable(int size)
    {
        this.size = size;
        empLinkedLists = new EmpLinkedList[size];

        for(int i = 0; i < size; i ++)
        {
            empLinkedLists[i] = new EmpLinkedList();
        }

    }

    public void add(Emp emp)
    {
        int empNO = number(emp);
        empLinkedLists[empNO].add(emp);
    }

    public int number(Emp emp)
    {
        return emp.id % size;
    }

    public void list()
    {
        for(int i = 0; i < size; i ++)
        {
            empLinkedLists[i].list(i);
        }
    }

}

class Emp
{
    public int id;
    public String name;
    public Emp next;

    public Emp(int id, String name)
    {
        this.id = id;
        this.name = name;
    }
}

class EmpLinkedList
{
    private Emp head;

    //在EmpLinkedList中添加新的节点
    public void add(Emp emp)
    {
        if(head == null)
        {
            head = emp;
            return;
        }
        Emp curEmp = head;
        while(curEmp.next != null)
        {
            curEmp = curEmp.next;
        }
        curEmp.next = emp;
    }


    //列出EmpLinkedList的全部节点
    public void list(int i)
    {
        if(head == null)
        {
            System.out.printf("您所想打印的员工列表%d为空\n", i);
            return;
        }
        Emp curEmp = head;

        System.out.printf("您所想列出的员工列表%d为：\n", i);

        while(curEmp != null)
        {
            System.out.printf(" => 员工id为 %d , 姓名为 %s \n", curEmp.id, curEmp.name);
            curEmp = curEmp.next;
        }
    }


}


