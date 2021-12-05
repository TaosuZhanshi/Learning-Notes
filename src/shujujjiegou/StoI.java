package shujujjiegou;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class StoI
{
    public static void main(String[] args)
    {
        String expression = "10+((2+3)*4)-5";
        List<String> infixExpressionList = iToP2(expression);
        System.out.println(infixExpressionList);
    }
    public static List<String> iToP2(String s){
        List<String> infixExpressionList = toInfixExpressdionList(s);
        //先将输入的字符串进行分割

        // 初始化运算符栈symbol以及中间结果栈number
        Stack<String> symbol = new Stack<String>();
        List<String> number = new ArrayList<String>();

        //遍历infixExpressionList
        for( String item : infixExpressionList)
        {
            if (item.matches("\\d+"))
            {
                number.add(item);
                //若是数字则直接入到中间结果栈
            }

            else if (item.equals("("))
            {
                symbol.push(item);
                //若是“(”则直接入到符号栈中
            }

            else if (item.equals(")"))
            {
                //若是右括号则直接开始弹出符号栈中的符号，压入到中间结果栈中，直到遇到左括号为止
                while(!symbol.peek().equals("(")){
                    number.add(symbol.pop());
                }
                symbol.pop();
                //完成后，这一对括号丢弃
            }

            else
            {
                while(!symbol.isEmpty() && !Operater.comparebiger(item, symbol.peek()))
                {
                    number.add(symbol.pop());
                }
                symbol.push(item);
            }
        }

        //将symbol中剩余的运算符依次压入到number中
        while(!symbol.isEmpty())
        {
            number.add(symbol.pop());
        }
        return number;
    }

    public static List<String> toInfixExpressdionList(String s)
            //一个将中缀表达式字符串变成List的静态方法
            //其原理为，符号直接add，而数字的话则用一个String将其直接串在一起，防止多位数
    {
        List<String> ls = new ArrayList<String>();
        int i = 0;
        String str;
        char c;
        do {
            //如果c是一个非数字，则需要加入到ls
            if((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57)
            {
                ls.add("" + c);
                i ++;
            }

            else {
                str = "";//先将str置成字符串
                while (i < s.length() && ((c = s.charAt(i)) >= 48) && ((c = s.charAt(i)) <= 57))
                    //在判断式中将s的某一位的值直接赋给c，同时做判断。
                    //太妙了啊！！！
                    //利用while循环一步解决多步问题，直接将多位数直接锁死并一起放入到str中，判断输入的数字是否是数字
                {
                    str += c;
                    i++;
                }
                ls.add(str);
            }
        }
        while (i < s.length());

        return ls;
    }

    public boolean isOper(char val){
        return val == '+' || val == '-' || val == '*' || val == '/';
    }
    public boolean isLeftBracket(char val){
        return val == '(';
    }
    public boolean isRightBracket(char val){
        return val == ')';
    }
}

class Operater
{
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    public static int getValue(String operation)
            //获得当前符号的优先级,加减为1，乘除为2，(为0
    {
        int result = 0;
        switch (operation)
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
                System.out.println("不存在此运算符");
                break;
        }
        return result;
    }


    public static boolean comparebiger(String operation1, String operation2)
            //比较两个符号的优先级,如果operation1的优先级高于operation2的返回true
    {
        return (getValue(operation1) > getValue(operation2));
    }
}
