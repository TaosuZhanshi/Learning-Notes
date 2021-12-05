package shujujjiegou.LinkedList;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class IToP
{
    public static void main(String[] args)
    {
        String expression = "10+((2+3)*4)-5";
        List<String> infixExpressionList = iToP(expression);
        System.out.println(infixExpressionList);

    }

    public static List<String> iToP(String s)
    {
        List<String> split = iSplit(s);
        //将输入的字符进行分割

        Stack<String> symbol = new Stack<String>();
        List<String> number = new ArrayList<String>();

        for(String item : split)
        {
            if(item.matches("\\d+"))
            {
                number.add(item);
                //如果是数字则直接加入到中间结果列中
            }

            else if (item.equals("("))
            {
                symbol.push(item);
                //若是(则直接压入符号栈
            }

            else if (item.equals(")"))
            {
                while(!symbol.peek().equals("("))
                {
                    number.add(symbol.pop());
                    //将符号栈中（前的符号压入到中间结果栈中
                }
                symbol.pop();
                //此对括号作废
            }
            else
            {
                while(!symbol.isEmpty() && !Operation.comparebiger(item, symbol.peek()))
                //如果符号栈不为空  且  字符并不比符号栈栈顶的优先级高（即先让乘除先运算——先入结果栈）
                    //则让左边需要先运算的字符先运算
                {
                    number.add(symbol.pop());
                }
                symbol.push(item);
                //如果字符的优先级比栈顶的高，则入栈
                // （为什么不直接入结果栈？——因为需要考虑括号的影响）
            }
        }

        //将符号栈中的所有剩下的字符全部入结果栈。
        while(!symbol.isEmpty())
        {
            number.add(symbol.pop());
        }
        return number;
    }

    public static List<String> iSplit(String s)
            //将字符串中的数字和字符进行分割，放入到一个List中
    {
        List<String> splited = new ArrayList<String>();
        for(int i = 0; i < s.length();)
        {
            char c;
            String str = "";

            if(( c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57)
            //若字符不是数字，在这里则将其直接加入到List中
            {
                splited.add("" + c);
                //利用"" + c 将char类型变化为String类型
                i++;
            }

            //若是数字，则考虑其是否为多位数之后再加入到List中
            else {
                while (i < s.length() && ((c = s.charAt(i)) >= 48) && ((c = s.charAt(i)) <= 57)) {
                    str += c;
                    i++;
                    //充分考虑多位数
                }
                splited.add(str);
            }
        }
        return splited;
    }

}
class Operation
{
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    public static int getValue(String s)
    {
        int result = 0;
        switch(s)
        {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            case "(":
                result = 0;
                break;
            default:
                System.out.println("您所输入的字符不合规定");
        }
        return result;
    }


    public static boolean comparebiger(String s1, String s2)
    {
        return (getValue(s1) > getValue(s2));
    }
}