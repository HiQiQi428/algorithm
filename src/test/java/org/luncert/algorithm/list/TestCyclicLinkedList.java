package org.luncert.algorithm.list;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class TestCyclicLinkedList {

    CyclicLinkedList<Integer> c = new CyclicLinkedList<>();

    @Test
    public void test() {
        for (int i = 0; i < 10; i++)
            c.add(i);
        System.out.println(c);
        
        for (Integer i : c)
            System.out.print(i + " ");
        System.out.println();

        c.remove(0);
        c.set(0, new Integer(100));
        System.out.println(c);
    }

}