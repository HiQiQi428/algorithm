package org.luncert.algorithm.tree;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class TestBiTree {

    BiTree<Integer> biTree = new BiTree<>();

    @Test
    public void test() {
        for (int i = 0; i < 10; i++)
            biTree.add(i);
        System.out.println(biTree.maxItem((a, b) -> Math.max(a, b)));
        System.out.println(biTree.search(8));
        for (Integer i : biTree)
            System.out.print(i + " ");
        System.out.println(biTree.size());
        System.out.println(biTree.height());
        System.out.println(biTree.deepestElem());
        biTree.levelOrderTraversalInReverse();
    }

}