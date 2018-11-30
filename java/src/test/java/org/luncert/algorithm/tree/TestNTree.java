package org.luncert.algorithm.tree;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class TestNTree {

    @Test
    public void test() {
        NTree<Integer> tree = new NTree<>();
        tree.add(0, false, 0);
        tree.add(0, true, 1); // 作为孩子添加到 0 节点
        tree.add(1, false, 2); // 作为兄弟添加到 1 节点
        tree.add(2, false, 3);
        tree.add(3, false, 4);
        tree.add(4, false, 5);
        tree.add(5, false, 6);
        tree.add(3, true, 7);
        tree.add(4, true, 8);
        tree.add(8, false, 9);
        tree.add(5, true, 10);
        tree.add(10, false, 11);
        tree.add(11, false, 12);
        tree.add(6, true, 13);
        tree.add(9, true, 14);
        tree.add(14, false, 15);

        System.err.println("size: " + tree.size());
        System.out.println("height: " + tree.height());
    }

}