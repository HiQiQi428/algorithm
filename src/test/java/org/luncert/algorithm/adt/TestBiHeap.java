package org.luncert.algorithm.adt;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class TestBiHeap {
    
    @Test
    public void test() {
        Adt<Integer, String> adt = new BiHeap<>(true, (a, b) -> a > b ? 1 : (a < b ? -1 : 0));
        adt.insert(1, "one");
        adt.insert(2, "two");
        adt.insert(3, "three");
        System.out.println(adt.getMaximum());
        System.out.println(adt.getMinimum());
        System.out.println(adt.deleteMin());
        System.out.println(adt.getMinimum());
        adt.insert(-1, "-one");
        System.out.println(adt.getMinimum());
    }

    @Test
    public void test1() {
        Integer[] data = {1, 3, 6, 32, 0, 55, 3};
        BiHeap.sort(data, true, (a, b) -> a > b ? 1 : (a < b ? -1 : 0));
        System.out.println(Arrays.toString(data));
    }

}