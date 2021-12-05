package shujujjiegou.LinkedList;

import java.lang.reflect.Array;

public class ArrayStack
{
    private int maxsize;
    private int[] stack;
    private int top = -1;

    public ArrayStack(int number) {
        this.maxsize = number;
        stack = new int[this.maxsize];
    }

    //判断栈是否已满
    public boolean isFull(){
        return top == maxsize - 1;
    }

    //判断栈是否为空
    public boolean isEmpty(){
        return top == -1;
    }

    //入栈-push
    public void push(int value)
    {
        if(this.isFull())
        {
            System.out.println("栈满，无法入栈");
            return;
        }
        top ++;
        stack[top] = value;
    }

    //出栈-pop
    public int pop()
    {
        if (this.isEmpty())
        {
            throw new RuntimeException("栈空，无法出栈");
        }
        int value = stack[top];
        top --;
        return value;
    }

    //遍历栈,遍历时，需要从栈顶开始显示
    public void list(){
        if(this.isEmpty())
        {
            throw new RuntimeException("栈空，无法遍历");
        }

        for(int i = top; i >= 0; i--){
            System.out.printf("stack[%d] = %d\n", i, stack[i]);
        }
    }
}
