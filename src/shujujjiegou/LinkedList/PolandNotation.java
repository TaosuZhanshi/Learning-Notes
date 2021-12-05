package shujujjiegou.LinkedList;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation
{
    public static void main(String[] args)
    {
        String suffixExpression = "30 4 + 5 * 6 - ";

        List<String> rpnList = getListString(suffixExpression);
        System.out.println("rpnList = " + rpnList);
        System.out.println("计算结果为 " + calculate(rpnList));
    }

    //获取字符串中的字符，并放入一个List中。
    public static List<String> getListString(String suffixExpression) {
        //用空格作为分割依据，分割所有的数
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<String>();
        for (String ele : split) {
            list.add(ele);
        }
        return list;
    }

    //计算
    public static int calculate (List<String> ls){
        //创建初始数栈
        Stack<String> stack = new Stack<String>();

        for (String item : ls){
            if(item.matches("\\d+")){  //判断此字符是否为数
                //若为数，则入栈
                stack.push(item);
            }
            else
            {
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if(item.equals("+"))
                    res = num1 + num2;
                else if (item.equals("-"))
                    res = num1 - num2;
                else if (item.equals("*"))
                    res = num1 * num2;
                else if (item.equals("/"))
                    res = num1 / num2;
                else
                    throw new RuntimeException("您输入的运算符有误");
                stack.push(res + "");
            }
        }

        //最后留在栈中的即为所求的结果
        return Integer.parseInt(stack.pop());
    }
}
