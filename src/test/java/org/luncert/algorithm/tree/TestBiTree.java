package org.luncert.algorithm.tree;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.luncert.algorithm.list.LinkedList;
import org.luncert.algorithm.list.List;
import org.luncert.algorithm.queue.LinkedQueue;
import org.luncert.algorithm.queue.Queue;
import org.luncert.algorithm.stack.LinkedStack;
import org.luncert.algorithm.stack.Stack;

@RunWith(JUnit4.class)
public class TestBiTree {
    
    @Test
    public void test() {
        Tree<Integer> biTree = new BiTree<>((a, b) -> Math.max(a, b), (a, b) -> a + b);
        for (int i = 0; i < 11; i++)
            biTree.add(i);
        
        for (Integer i : biTree)
            System.out.print(i + " ");
        System.out.println();

        System.out.println("maxItem: " + biTree.maxItem());
        System.out.println("search: " + biTree.search(8));
        System.out.println("size: " + biTree.size());
        System.out.println("height: " + biTree.height());
        System.out.println("deepestNode: " + biTree.deepestNode());
        System.out.println("equals: " + biTree.equals(biTree));
        System.out.println("maxLayerSum: " + biTree.maxLayerSum());
        System.out.println("hasPathSum: " + hasPathSum(biTree, 13));
        System.out.println("sum: " + sum(biTree));
        
        levelOrderTraversalInReverse(biTree);
        allPathsFromRoot(biTree);

        transforMirror(biTree);
        System.out.println(biTree);
    }

    @Test
    public void test1() {
        Integer[] seq = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        Tree<Integer> tree = new BiTree<>(seq);
        System.out.println(tree);

        Tree.Node<Integer> a = tree.find(10), b = tree.find(8), c = tree.lca(a, b);
        System.out.println("lca: " + c);

        System.out.println(zigzag(tree));

        tree.remove(3);
        System.out.println(tree);
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
            if (node.getRightChild() != null)
                q.enQueue(node.getRightChild());
            if (node.getLeftChild() != null)
                q.enQueue(node.getLeftChild());
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
        if (node != null) {
            String path = prefix + " -> " + node.data;
            if (node.getLeftChild() == null && node.getRightChild() == null)
                System.out.println(path);
            else {
                allPathsFromRoot(node.getLeftChild(), path);
                allPathsFromRoot(node.getRightChild(), path);
            }
        }
    }

    /**
     * 判断是否存在路径的数据和等于给定值
     */
    private boolean hasPathSum(Tree<Integer> tree, int k) {
        return hasPathSum(tree.getRoot(), 0, k);
    }

    private boolean hasPathSum(Tree.Node<Integer> node, int pre, int k) {
        if (node != null) {
            pre += node.data;
            return pre == k || hasPathSum(node.getLeftChild(), pre, k) || hasPathSum(node.getRightChild(), pre, k);
        }
        else return k == 0;
    }

    /**
     * 求所有节点数据之和
     */
    private int sum(Tree<Integer> tree) {
        return sum(tree.getRoot());
    }

    private int sum(Tree.Node<Integer> node) {
        if (node == null) return 0;
        else return node.data + sum(node.getLeftChild()) + sum(node.getRightChild());
    }

    /**
     * 转镜像
     */
    private void transforMirror(Tree<Integer> tree) {
        transforMirror(tree.getRoot());
    }

    private void transforMirror(Tree.Node<Integer> node) {
        Tree.Node<Integer> tmp = node.getRightChild();
        node.setRightChild(node.getLeftChild());
        node.setLeftChild(tmp);
        if (node.getRightChild() != null)
            transforMirror(node.getRightChild());
        if (node.getLeftChild() != null)
            transforMirror(node.getLeftChild());
    }

    /**
     * Zigzag 遍历
     */
    private List<Integer> zigzag(Tree<Integer> tree) {
        boolean leftFirst = false;
        Tree.Node<Integer> node, a, b;
        Queue<Tree.Node<Integer>> q = new LinkedQueue<>();
        List<Integer> list = new LinkedList<>();
        if ((node = tree.getRoot()) != null) {
            list.add(node.data);
            q.enQueue(node);
            q.enQueue(null);
        }
        while (!q.isEmpty()) {
            node = q.deQueue();
            if (node == null) {
                if (!q.isEmpty())
                    q.enQueue(null);
                leftFirst = !leftFirst;
            }
            else {
                if (leftFirst) {
                    a = node.getLeftChild();
                    b = node.getRightChild();
                }
                else {
                    a = node.getRightChild();
                    b = node.getLeftChild();
                }
                if (a != null) {
                    list.add(a.data);
                    if (b != null) {
                        list.add(b.data);
                        q.enQueue(b);
                    }
                    q.enQueue(a);
                }
                else if (b != null) {
                    list.add(b.data);
                    q.enQueue(b);
                }
            }
        }
        return list;
    }
    
}