package org.luncert.algorithm.list;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class TestLinkedList {

    LinkedList<Integer> list = new LinkedList<>();

    @Test
    public void test() {
        for (int i = 0; i < 10; i++)
            list.add(i);
        list.add(0, 13);
        list.add(5, 14);
        System.out.println(list.size());
        System.out.println(list);

        list.remove(new Integer(9));
        System.out.println(list);

        System.out.println(list.get(3));
        System.out.println(list.set(3, new Integer(100)));
        System.out.println(list.get(3));

        System.out.println(list);
        System.out.println(list.ringDetection());
    }

}