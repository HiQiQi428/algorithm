package org.luncert.algorithm.tree;

import java.util.Iterator;
import java.util.function.BiFunction;

import org.luncert.algorithm.queue.LinkedQueue;
import org.luncert.algorithm.queue.Queue;

public class BiTree<E> extends Tree<E> {

    private Node<E> root;
    private int size;
    private int diameter;
    private BiFunction<E, E, E> max;
    private BiFunction<E, E, E> add;

    public BiTree() {}

    public BiTree(BiFunction<E, E, E> max, BiFunction<E, E, E> add) {
        this.max = max;
        this.add = add;
    }

    private E maxItem(Node<E> node) {
        if (node.lc != null) {
            E tmp = max.apply(maxItem(node.lc), node.data);
            return node.rc == null ? tmp : max.apply(maxItem(node.rc), tmp);
        }
        else return node.rc != null ? max.apply(maxItem(node.rc), node.data) : node.data;
    }

    /**
     * 使用递归深度优先遍历查找树中最大值
     * @param max
     * @return
     */
    public E maxItem() {
        if (max == null)
            throw new RuntimeException("BiFunction<E, E, E> max cannot be null");
        return this.root != null ? maxItem(this.root) : null;
    }

    /**
     * 使用广度优先遍历查找元素
     * @param elem
     * @return
     */
    public E search(E elem) {
        if (root != null) {
            Node<E> node;
            Queue<Node<E>> q = new LinkedQueue<>();
            q.enQueue(root);
            while (!q.isEmpty()) {
                node = q.deQueue();
                if (node.data == elem || node.data.equals(elem))
                    return node.data;
                if (node.lc != null)
                    q.enQueue(node.lc);
                if (node.rc != null)
                    q.enQueue(node.rc);
            }
        }
        return null;
    }

    /**
     * 广度优先遍历寻找空引用
     * @param elem
     */
    public void add(E elem) {
        Node<E> newNode = new Node<>(elem);
        if (root != null) {
            Node<E> node;
            Queue<Node<E>> q = new LinkedQueue<>();
            q.enQueue(root);
            while (!q.isEmpty()) {
                node = q.deQueue();
                if (node.lc == null) {
                    node.lc = newNode;
                    break;
                }
                else if (node.rc == null) {
                    node.rc = newNode;
                    break;
                }
                else {
                    q.enQueue(node.lc);
                    q.enQueue(node.rc);
                }
            }
        }
        else root = newNode;
        size++;
    }

    public void clear() {
        root = null;
        size = 0;
    }

    public int size() { return size; }

    public Node<E> getRoot() { return root; }

    /**
     * @return 前序遍历迭代器
     */
    public Iterator<E> iterator() { return new DLRItr(); }

    private class DLRItr implements Iterator<E> {

        LinkedQueue<Node<E>> q;

        DLRItr() {
            q = new LinkedQueue<>();
            q.enQueue(root);
        }

        public boolean hasNext() { return !q.isEmpty(); }

        public E next() {
            Node<E> node = q.deQueue();
            if (node.lc != null)
                q.enQueue(node.lc);
            if (node.rc != null)
                q.enQueue(node.rc);
            return node.data;
        }
        
    }

    private int height(Node<E> node) {
        if (node.lc != null) {
            int lh = height(node.lc);
            return node.rc != null ? 1 + Math.max(lh, height(node.rc)) : 1 + lh;                
        }
        else if (node.rc != null)
            return 1 + height(node.rc);
        else
            return 1;
    }

    public int height() { return height(root); }

    public Node<E> deepestNode() {
        if (root != null) {
            int deepestLevel = -1, level = -1;
            Node<E> deepestNode = null, node = null;
            Queue<Node<E>> q = new LinkedQueue<>();
            q.enQueue(root);
            while (!q.isEmpty()) {
                level++;
                node = q.deQueue();
                if (level > deepestLevel) {
                    deepestLevel = level;
                    deepestNode = node;
                }
                if (node.lc != null)
                    q.enQueue(node.lc);
                if (node.rc != null)
                    q.enQueue(node.rc);
            }
            return deepestNode;
        }
        else return null;
    }

    /**
     * @param node
     * @return 子树高度
     */
    private int diameter(Node<E> node) {
        if (node == null)
            return 0;
        int left = diameter(node.lc), right = diameter(node.rc);
        if (left + right > diameter)
            diameter = left + right;
        return Math.max(left, right) + 1;
    }

    /**
     * 求树直径,即树中最长路径的两个叶节点间的节点数,即高度最高的两棵子树的高度和+1
     */
    public int diameter() {
        diameter(root);
        return diameter;
    }

    public E maxLayerSum() {
        if (max == null)
            throw new RuntimeException("BiFunction<E, E, E> max cannot be null");
        if (add == null)
            throw new RuntimeException("BiFunction<E, E, E> add cannot be null");
        E ret = root.data, tmp = null;
        // i 表示所在层还有几个节点在队列中,k 表示所在层的容量
        int i = 1, k = 1;
        Node<E> node;
        Queue<Node<E>> q = new LinkedQueue<>();
        q.enQueue(root);
        while (!q.isEmpty()) {
            i--;
            node = q.deQueue();
            // i + 1 == k 表示所在层已经处理完了,重置 tmp
            if (i + 1 == k)
                tmp = node.data;
            // 正在处理当前层的节点 add node.data to tmp
            else
                tmp = add.apply(tmp, node.data);
            // 当前层已经处理完了,比较 tmp 和 ret,取较大值,进入下一层
            if (i == 0) {
                i = k = k * 2;
                ret = max.apply(ret, tmp);
            }
            if (node.lc != null)
                q.enQueue(node.lc);
            if (node.rc != null)
                q.enQueue(node.rc);
        }
        // 最后一层如果不是满节点,不会在循环中被处理
        ret = max.apply(ret, tmp);
        return ret;
    }

    public E remove(E data) {
        return null;
    }

    public Node<E> find(E data) {
        return null;
    }

    public Node<E> publicAncestor(Node<?>... nodes) {
        return null;
    }

    private boolean equals(Node<E> a, Node<E> b) {
        return a == null && b == null || equals(a.lc, b.lc) || equals(a.rc, b.rc);
    }

    public boolean equals(Tree<E> anotherTree) {
        return equals(root, anotherTree.getRoot());
    }

}