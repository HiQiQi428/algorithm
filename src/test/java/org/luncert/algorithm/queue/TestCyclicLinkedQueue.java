package org.luncert.algorithm.queue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class TestCyclicLinkedQueue {

    @Test
    public void test() {
        Queue<Integer> q = new CyclicLinkedQueue<>();
        for (int i = 0; i < 10; i++)
            q.enQueue(i);
        q.reverse();
        System.out.println(q);
        for (Integer i : q)
            System.out.print(i + " ");
        System.out.println();
        System.out.println(q);
    }

}