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

    /**
     * 中缀表达式转后缀表达式
     */
    @Test
    public void test2() {
        String src = "(4+5)*2-3/8";
        try (Scanner scan = new Scanner(src)) {
            String tmp;
            while (scan.hasNext()) {
                // tmp = scan.next
            }
        }
    }

}