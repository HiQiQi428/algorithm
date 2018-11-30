package org.luncert.algorithm.stack;

import java.util.Scanner;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class TestLinkedStack {

    
    @Test
    public void test() {
        LinkedStack<Integer> s = new LinkedStack<>();
        for (int i = 0; i < 10; i++)
            s.push(i);
        System.out.println(s);
        s.reverse();
        System.out.println(s);
        for (Integer i : s)
            System.out.print(i + " ");
        System.out.println(s);
    }

    /**
     * 圆括号匹配检测
     */
    @Test
    public void test1() {
        Stack<String> s = new LinkedStack<>();
        String src = "(A+B)+(C-D)";
        try (Scanner scan = new Scanner(src)) {
            String tmp;
            while (scan.hasNext()) {
                tmp = scan.next();
                if (tmp.compareTo("(") == 0)
                    s.push(tmp);
                else if (tmp.compareTo(")") == 0)
                    s.pop();
            }
        }
        System.out.println("match: " + s.isEmpty());
    }

    private int getPrority(char c) {
        if (c == '+' || c == '-')
            return 1;
        else if (c == '*' || c == '/')
            return 2;
        else
            return 10;
    }

    /**
     * 中缀表达式转后缀表达式
     */
    @Test
    public void test2() {
        char c;
        String src = "(4+5)*2-3/8";
        StringBuilder tar = new StringBuilder();
        Stack<Character> stack = new LinkedStack<>();
        for (int i = 0, limit = src.length(); i < limit; i++) {
            c = src.charAt(i);
            if (48 <= c && c <= 57){
                for (; i < limit && (c = src.charAt(i)) >= 48 && c <= 57; i++)
                    tar.append(c);
                i--;
            }
            else if (c == ')') {
                while (!stack.isEmpty() && stack.top() != '(')
                    tar.append(stack.pop());
                stack.pop();    
            }
            else {
                char tmp;
                int p = getPrority(c);
                while (!stack.isEmpty()) {
                    tmp = stack.top();
                    if (getPrority(tmp) < p || tmp == '(') break;
                    else tar.append(stack.pop());
                }
                stack.push(c);
            }
        }
        while (!stack.isEmpty())
            tar.append(stack.pop());
        System.out.println(tar);
    }

    /**
     * 使用栈计算后缀表达式的值
     */
    @Test
    public void test3() {
        String src = "45+2*84/-";
        Stack<Integer> stack = new LinkedStack<>();
        char c;
        for (int i = 0, limit = src.length(); i < limit; i++) {
            c = src.charAt(i);
            if (48 <= c && c <= 57)
                stack.push(c - 48);
            else {
                int a = Integer.valueOf(stack.pop());
                int b = Integer.valueOf(stack.pop());
                int r = 0;
                switch(c) {
                    case '+': r = b + a; break;
                    case '-': r = b - a; break;
                    case '*': r = b * a; break;
                    case '/': r = b / a; break;
                }
                stack.push(r);
            }
        }
        System.out.println(stack.pop());
    }

}