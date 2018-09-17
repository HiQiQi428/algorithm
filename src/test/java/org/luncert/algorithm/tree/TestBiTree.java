package org.luncert.algorithm.tree;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.luncert.algorithm.queue.LinkedQueue;
import org.luncert.algorithm.queue.Queue;
import org.luncert.algorithm.stack.LinkedStack;
import org.luncert.algorithm.stack.Stack;

@RunWith(JUnit4.class)
public class TestBiTree {

    BiTree<Integer> biTree = new BiTree<>((a, b) -> Math.max(a, b), (a, b) -> a + b);

    @Test
    public void test() {
        for (int i = 0; i < 11; i++)
            biTree.add(i);
        System.out.println("maxItem: " + biTree.maxItem());
        System.out.println("search: " + biTree.search(8));
        
        for (Integer i : biTree)
            System.out.print(i + " ");
        System.out.println();

        System.out.println("size: " + biTree.size());
        System.out.println("height: " + biTree.height());
        System.out.println("deepestNode: " + biTree.deepestNode());
        System.out.println("equals: " + biTree.equals(biTree));
        System.out.println("maxLayerSum: " + biTree.maxLayerSum());
        
        levelOrderTraversalInReverse(biTree);
        allPathsFromRoot(biTree);
    }

    /**
     * 逆向逐层输出数中元素
     */
    private void levelOrderTraversalInReverse(Tree<Integer> tree) {
        Stack<Integer> s = new LinkedStack<>();
        Queue<Tree.Node<Integer>> q = new LinkedQueue<>();
        q.enQueue(tree.getRoot());
        Tree.Node<Integer> node;
        while (!q.isEmpty()) {
            node = q.deQueue();
            s.push(node.data);
            if (node.rc != null)
                q.enQueue(node.rc);
            if (node.lc != null)
                q.enQueue(node.lc);
        }
        for (Integer elem : s)
            System.out.print(elem + " ");
        System.out.println();
    }

    /**
     * 输出所有从根节点到叶子节点的路径
     */
    private void allPathsFromRoot(Tree<Integer> tree) {
        allPathsFromRoot(tree.getRoot(), "");
    }

    private void allPathsFromRoot(Tree.Node<Integer> node, String prefix) {
        String path = prefix + " -> " + node.data;
        if (node.lc != null)
            allPathsFromRoot(node.lc, path);
        if (node.rc != null)
            allPathsFromRoot(node.rc, path);
        if (node.lc == null && node.rc == null)
            System.out.println(path);
    }

}