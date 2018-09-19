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
        BiTree<Integer> BiTree = new BiTree<>((a, b) -> Math.max(a, b), (a, b) -> a + b);
        for (int i = 0; i < 11; i++)
            BiTree.add(i);
        
        for (Integer i : BiTree)
            System.out.print(i + " ");
        System.out.println();

        System.out.println("maxItem: " + BiTree.maxItem());
        System.out.println("search: " + BiTree.search(8));
        System.out.println("size: " + BiTree.size());
        System.out.println("height: " + BiTree.height());
        System.out.println("deepestBiNode: " + BiTree.deepestNode());
        System.out.println("equals: " + BiTree.equals(BiTree));
        System.out.println("maxLayerSum: " + BiTree.maxLayerSum());
        System.out.println("hasPathSum: " + hasPathSum(BiTree, 13));
        System.out.println("sum: " + sum(BiTree));
        
        levelOrderTraversalInReverse(BiTree);
        allPathsFromRoot(BiTree);

        transforMirror(BiTree);
        System.out.println(BiTree);
    }

    @Test
    public void test1() {
        Integer[] seq = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        BiTree<Integer> BiTree = new BiTree<>(seq);
        System.out.println(BiTree);

        BiTree.BiNode<Integer> a = BiTree.find(10), b = BiTree.find(8), c = BiTree.lca(a, b);
        System.out.println("lca: " + c);

        System.out.println(zigzag(BiTree));

        BiTree.remove(3);
        System.out.println(BiTree);
    }

    /**
     * 逆向逐层输出数中元素
     */
    private void levelOrderTraversalInReverse(BiTree<Integer> BiTree) {
        Stack<Integer> s = new LinkedStack<>();
        Queue<BiTree.BiNode<Integer>> q = new LinkedQueue<>();
        q.enQueue(BiTree.getRoot());
        BiTree.BiNode<Integer> BiNode;
        while (!q.isEmpty()) {
            BiNode = q.deQueue();
            s.push(BiNode.data);
            if (BiNode.rc != null)
                q.enQueue(BiNode.rc);
            if (BiNode.lc != null)
                q.enQueue(BiNode.lc);
        }
        for (Integer elem : s)
            System.out.print(elem + " ");
        System.out.println();
    }

    /**
     * 输出所有从根节点到叶子节点的路径
     */
    private void allPathsFromRoot(BiTree<Integer> BiTree) {
        allPathsFromRoot(BiTree.getRoot(), "");
    }

    private void allPathsFromRoot(BiTree.BiNode<Integer> BiNode, String prefix) {
        if (BiNode != null) {
            String path = prefix + " -> " + BiNode.data;
            if (BiNode.lc == null && BiNode.rc == null)
                System.out.println(path);
            else {
                allPathsFromRoot(BiNode.lc, path);
                allPathsFromRoot(BiNode.rc, path);
            }
        }
    }

    /**
     * 判断是否存在路径的数据和等于给定值
     */
    private boolean hasPathSum(BiTree<Integer> BiTree, int k) {
        return hasPathSum(BiTree.getRoot(), 0, k);
    }

    private boolean hasPathSum(BiTree.BiNode<Integer> BiNode, int pre, int k) {
        if (BiNode != null) {
            pre += BiNode.data;
            return pre == k || hasPathSum(BiNode.lc, pre, k) || hasPathSum(BiNode.rc, pre, k);
        }
        else return k == 0;
    }

    /**
     * 求所有节点数据之和
     */
    private int sum(BiTree<Integer> BiTree) {
        return sum(BiTree.getRoot());
    }

    private int sum(BiTree.BiNode<Integer> BiNode) {
        if (BiNode == null) return 0;
        else return BiNode.data + sum(BiNode.lc) + sum(BiNode.rc);
    }

    /**
     * 转镜像
     */
    private void transforMirror(BiTree<Integer> BiTree) {
        transforMirror(BiTree.getRoot());
    }

    private void transforMirror(BiTree.BiNode<Integer> BiNode) {
        BiTree.BiNode<Integer> tmp = BiNode.rc;
        BiNode.rc = BiNode.lc;
        BiNode.lc = tmp;
        if (BiNode.rc != null)
            transforMirror(BiNode.rc);
        if (BiNode.lc != null)
            transforMirror(BiNode.lc);
    }

    /**
     * Zigzag 遍历
     */
    private List<Integer> zigzag(BiTree<Integer> BiTree) {
        boolean leftFirst = false;
        BiTree.BiNode<Integer> node, a, b;
        Queue<BiTree.BiNode<Integer>> q = new LinkedQueue<>();
        List<Integer> list = new LinkedList<>();
        if ((node = BiTree.getRoot()) != null) {
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
                    a = node.lc;
                    b = node.rc;
                }
                else {
                    a = node.rc;
                    b = node.lc;
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