package org.luncert.algorithm.list;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class TestBiLinkedList {

    BiLinkedList<Integer> list = new BiLinkedList<>();

    @Test
    public void test() {
        for (int i = 0; i < 10; i++)
            list.add(i);
        list.add(0, new Integer(13));
        list.add(5, new Integer(14));
        list.remove(0);
        list.remove(10);
        System.out.println(list.size());
        System.out.println(list);
        list.printTwice();
        list.remove(new Integer(7));
        System.out.println(list);
    }

}