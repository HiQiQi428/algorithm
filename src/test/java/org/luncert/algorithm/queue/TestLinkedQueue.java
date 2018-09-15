package org.luncert.algorithm.queue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class TestLinkedQueue {

    LinkedQueue<Integer> q = new LinkedQueue<>();

    @Test
    public void test() {
        for (int i = 0; i < 10; i++)
            q.enQueue(i);
        System.out.println(q);
        for (int i = 0; i < 10; i++)
            q.deQueue();
        System.out.println(q);
    }
    
}